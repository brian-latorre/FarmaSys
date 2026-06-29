package isw.farmasysbackend.service;

import isw.farmasysbackend.dto.AlertaStockResponse;
import isw.farmasysbackend.model.Lote;
import isw.farmasysbackend.model.Producto;
import isw.farmasysbackend.repository.LoteRepository;
import isw.farmasysbackend.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@Service
public class AlertaStockService {

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private LoteRepository loteRepository;

    public List<AlertaStockResponse> obtenerAlertas() {
        List<AlertaStockResponse> alertas = new ArrayList<>();
        List<Producto> productos = productoRepository.findAll();
        List<Lote> lotes = loteRepository.findAll();
        LocalDate hoy = LocalDate.now();

        for (Producto prod : productos) {
            List<Lote> lotesProducto = lotes.stream()
                    .filter(l -> l.getProducto().getId().equals(prod.getId()))
                    .toList();

            int stockTotal = lotesProducto.stream().mapToInt(Lote::getStockFisico).sum();
            boolean esStockBajo = stockTotal < 15;

            for (Lote lote : lotesProducto) {
                long diasParaVencer = ChronoUnit.DAYS.between(hoy, lote.getFechaVencimiento());
                boolean esPorVencer = diasParaVencer >= 0 && diasParaVencer <= 30;

                if (esStockBajo || esPorVencer) {
                    String estado = "";
                    if (esStockBajo && esPorVencer) {
                        estado = "Stock Bajo y Por Vencer";
                    } else if (esStockBajo) {
                        estado = "Stock Bajo";
                    } else {
                        estado = "Por Vencer";
                    }

                    alertas.add(AlertaStockResponse.builder()
                            .nombreProducto(prod.getDescripcion())
                            .nroLote(lote.getNroLote())
                            .stockActual(lote.getStockFisico())
                            .fechaVencimiento(lote.getFechaVencimiento())
                            .estado(estado)
                            .build());
                }
            }

            if (lotesProducto.isEmpty() && esStockBajo) {
                alertas.add(AlertaStockResponse.builder()
                        .nombreProducto(prod.getDescripcion())
                        .nroLote("SIN LOTE")
                        .stockActual(0)
                        .fechaVencimiento(null)
                        .estado("Stock Bajo")
                        .build());
            }
        }

        return alertas;
    }
}
