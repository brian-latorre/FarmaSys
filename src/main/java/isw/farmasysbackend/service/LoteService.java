package isw.farmasysbackend.service;

import isw.farmasysbackend.dto.LoteRequest;
import isw.farmasysbackend.dto.LoteResponse;
import isw.farmasysbackend.model.Lote;
import isw.farmasysbackend.model.Producto;
import isw.farmasysbackend.model.Sede;
import isw.farmasysbackend.repository.LoteRepository;
import isw.farmasysbackend.repository.ProductoRepository;
import isw.farmasysbackend.repository.SedeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoteService {

    @Autowired
    private LoteRepository loteRepository;

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private SedeRepository sedeRepository;

    public LoteResponse registrarIngresoMercaderia(LoteRequest request) {
        if (request.getCodBarrasProducto() == null || request.getCodBarrasProducto().isEmpty()) {
            throw new IllegalArgumentException("Debe ingresar el código de barras del producto");
        }
        if (request.getStockFisico() == null || request.getStockFisico() <= 0) {
            throw new IllegalArgumentException("La cantidad ingresada debe ser mayor a cero");
        }
        if (request.getIdSede() == null) {
            request.setIdSede(1);
        }

        Producto producto = productoRepository.findByCodBarras(request.getCodBarrasProducto())
                .orElseThrow(() -> new IllegalArgumentException("No se encontró un producto con el código de barras: " + request.getCodBarrasProducto()));

        Sede sede = sedeRepository.findById(request.getIdSede())
                .orElseThrow(() -> new IllegalArgumentException("Sede no encontrada"));

        Optional<Lote> loteExistente = loteRepository.findByNroLoteAndSedeIdAndProductoId(
                request.getNroLote(), sede.getId(), producto.getId());

        Lote lote;
        if (loteExistente.isPresent()) {
            lote = loteExistente.get();
            lote.setStockFisico(lote.getStockFisico() + request.getStockFisico());
        } else {
            lote = Lote.builder()
                    .nroLote(request.getNroLote())
                    .fechaVencimiento(request.getFechaVencimiento())
                    .stockFisico(request.getStockFisico())
                    .sede(sede)
                    .producto(producto)
                    .build();
        }

        Lote loteGuardado = loteRepository.save(lote);
        return LoteResponse.fromEntity(loteGuardado);
    }
}
