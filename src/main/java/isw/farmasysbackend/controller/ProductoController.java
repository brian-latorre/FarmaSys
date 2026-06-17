package isw.farmasysbackend.controller;

import isw.farmasysbackend.dto.ProductoRequest;
import isw.farmasysbackend.dto.ProductoResponse;
import isw.farmasysbackend.service.ProductoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/productos")
public class ProductoController {

    private final ProductoService productoService;

    @GetMapping
    public ResponseEntity<List<ProductoResponse>> obtenerProductos() {
        return ResponseEntity.ok(productoService.listarTodos());
    }

    @PostMapping
    public ResponseEntity<ProductoResponse> registrarProducto(@Valid @RequestBody ProductoRequest productoRequest) {
        ProductoResponse nuevoProducto = productoService.guardar(productoRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoProducto);
    }
}
