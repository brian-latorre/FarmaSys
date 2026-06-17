package isw.farmasysbackend.service;

import isw.farmasysbackend.dto.OrdenVentaResponse;
import isw.farmasysbackend.repository.OrdenVentaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrdenVentaService {

    private final OrdenVentaRepository ordenVentaRepository;

    @Transactional(readOnly = true)
    public List<OrdenVentaResponse> getOrdenesVenta() {
        return OrdenVentaResponse.fromEntities(ordenVentaRepository.findAll());
    }
}
