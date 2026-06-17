package isw.farmasysbackend.service;

import isw.farmasysbackend.dto.ProductoRequest;
import isw.farmasysbackend.dto.ProductoResponse;
import isw.farmasysbackend.model.Producto;
import isw.farmasysbackend.repository.ProductoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class ProductoService {

    private final ProductoRepository productoRepository;

    @Transactional(readOnly = true)
    public List<ProductoResponse> listarTodos() {
        List<Producto> productos = productoRepository.findAll();
        return ProductoResponse.fromEntities(productos);
    }

    @Transactional
    public ProductoResponse guardar(ProductoRequest productoRequest) {
        if (productoRequest == null) {
            throw new IllegalArgumentException("El producto es obligatorio");
        }
        if (productoRequest.getId() != null) {
            throw new IllegalArgumentException("No se debe enviar id al registrar un producto");
        }

        Producto producto = ProductoRequest.toEntity(productoRequest);
        if (producto.getCodBarras() == null || producto.getCodBarras().isBlank()) {
            throw new IllegalArgumentException("El codigo de barras es obligatorio");
        }
        if (producto.getPrecioVenta() == null || producto.getPrecioCosto() == null) {
            throw new IllegalArgumentException("Los precios de venta y costo son obligatorios");
        }

        if (producto.getPrecioVenta().compareTo(producto.getPrecioCosto()) < 0) {
            throw new IllegalArgumentException("El precio de venta no puede ser menor al precio de costo");
        }
        if (productoRepository.existsByCodBarras(producto.getCodBarras())) {
            throw new IllegalArgumentException("Ya existe un producto con el codigo de barras indicado");
        }

        Producto productoGuardado = productoRepository.save(producto);
        return ProductoResponse.fromEntity(productoGuardado);
    }

    @Transactional
    public void eliminar(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("El id del producto es obligatorio");
        }
        if (!productoRepository.existsById(id)) {
            throw new NoSuchElementException("No existe un producto con el id indicado");
        }
        productoRepository.deleteById(id);
    }
}
