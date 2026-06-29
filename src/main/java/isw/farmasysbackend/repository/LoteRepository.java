package isw.farmasysbackend.repository;

import isw.farmasysbackend.model.Lote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LoteRepository extends JpaRepository<Lote, Long> {
    Optional<Lote> findByNroLoteAndSedeIdAndProductoId(String nroLote, Integer sedeId, Long productoId);
}
