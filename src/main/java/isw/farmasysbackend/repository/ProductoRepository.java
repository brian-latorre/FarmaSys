package isw.farmasysbackend.repository;

import isw.farmasysbackend.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {

    Optional<Producto> findByCodBarras(String codBarras);

    boolean existsByCodBarras(String codBarras);
}
