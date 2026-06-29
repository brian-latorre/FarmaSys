package isw.farmasysbackend.controller;

import isw.farmasysbackend.dto.ProductoRequest;
import isw.farmasysbackend.dto.ProductoResponse;
import isw.farmasysbackend.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/productos")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @GetMapping
    public ResponseEntity<List<ProductoResponse>> obtenerProductos() {
        return ResponseEntity.ok(productoService.listarTodos());
    }

    @PostMapping
    public ResponseEntity<ProductoResponse> registrarProducto(@RequestBody ProductoRequest productoRequest) {
        try {
            ProductoResponse nuevoProducto = productoService.guardar(productoRequest);
            return ResponseEntity.ok(nuevoProducto);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductoResponse> editarProducto(@PathVariable Long id, @RequestBody ProductoRequest productoRequest) {
        try {
            productoRequest.setId(id);
            ProductoResponse actualizado = productoService.guardar(productoRequest);
            return ResponseEntity.ok(actualizado);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarProducto(@PathVariable Long id) {
        try {
            productoService.eliminar(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}