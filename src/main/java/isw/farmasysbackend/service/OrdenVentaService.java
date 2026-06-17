package isw.farmasysbackend.service;

import isw.farmasysbackend.model.OrdenVenta;
import isw.farmasysbackend.repository.OrdenVentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class OrdenVentaService {

    @Autowired
    OrdenVentaRepository ordenVentaRepository;

    public List<OrdenVenta> getOrdenesVenta() {
        return ordenVentaRepository.findAll();
    }
}
