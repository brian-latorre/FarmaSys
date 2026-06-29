package isw.farmasysbackend.controller;

import isw.farmasysbackend.dto.LoteRequest;
import isw.farmasysbackend.dto.LoteResponse;
import isw.farmasysbackend.service.LoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/lotes")
public class LoteController {

    @Autowired
    private LoteService loteService;

    @PostMapping("/ingreso")
    public ResponseEntity<LoteResponse> registrarIngreso(@RequestBody LoteRequest request) {
        try {
            LoteResponse response = loteService.registrarIngresoMercaderia(request);
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
