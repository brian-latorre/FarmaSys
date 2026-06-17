package isw.farmasysbackend.repository;

import isw.farmasysbackend.model.OrdenVenta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdenVentaRepository extends JpaRepository<OrdenVenta, Long> {
}