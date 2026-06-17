package isw.farmasysbackend.controller;

import isw.farmasysbackend.dto.OrdenVentaResponse;
import isw.farmasysbackend.service.OrdenVentaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1/ordenventa")
public class OrdenVentaController {

    private final OrdenVentaService ordenVentaService;

    @GetMapping
    public ResponseEntity<List<OrdenVentaResponse>> getOrdenesVenta() {
        return ResponseEntity.ok(ordenVentaService.getOrdenesVenta());
    }
}
