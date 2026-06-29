package isw.farmasysbackend.service;

import isw.farmasysbackend.dto.ProductoRequest;
import isw.farmasysbackend.dto.ProductoResponse;
import isw.farmasysbackend.model.Producto;
import isw.farmasysbackend.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    public List<ProductoResponse> listarTodos() {
        List<Producto> productos = productoRepository.findAll();
        return ProductoResponse.fromEntities(productos);
    }

    public ProductoResponse guardar(ProductoRequest productoRequest) {
        if (productoRequest == null) {
            throw new IllegalArgumentException("El producto es obligatorio");
        }
        if (productoRequest.getPrecioVenta() == null || productoRequest.getPrecioCosto() == null) {
            throw new IllegalArgumentException("Los precios de venta y costo son obligatorios");
        }

        if (productoRequest.getId() == null) {
            if (productoRepository.findByCodBarras(productoRequest.getCodBarras()).isPresent()) {
                throw new IllegalArgumentException("Error: Ya existe un producto con el código de barras " + productoRequest.getCodBarras());
            }
        } else {
            Producto existente = productoRepository.findById(productoRequest.getId())
                    .orElseThrow(() -> new IllegalArgumentException("Error: Producto no encontrado"));
            
            if (!existente.getCodBarras().equals(productoRequest.getCodBarras())) {
                if (productoRepository.findByCodBarras(productoRequest.getCodBarras()).isPresent()) {
                    throw new IllegalArgumentException("Error: Ya existe otro producto con el código de barras " + productoRequest.getCodBarras());
                }
            }
        }

        Producto producto = ProductoRequest.toEntity(productoRequest);

        if (producto.getPrecioVenta().compareTo(producto.getPrecioCosto()) < 0) {
            throw new IllegalArgumentException("Error: El precio de venta es menor al precio de costo");
        }

        Producto productoGuardado = productoRepository.save(producto);
        return ProductoResponse.fromEntity(productoGuardado);
    }

    public void eliminar(Long id) {
        if (!productoRepository.existsById(id)) {
            throw new IllegalArgumentException("Error: Producto no encontrado");
        }
        productoRepository.deleteById(id);
    }
}
