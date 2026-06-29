package isw.farmasysbackend.controller;

import isw.farmasysbackend.dto.AlertaStockResponse;
import isw.farmasysbackend.service.AlertaStockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/alertas")
public class AlertaStockController {

    @Autowired
    private AlertaStockService alertaStockService;

    @GetMapping
    public ResponseEntity<List<AlertaStockResponse>> obtenerAlertas() {
        return ResponseEntity.ok(alertaStockService.obtenerAlertas());
    }
}
