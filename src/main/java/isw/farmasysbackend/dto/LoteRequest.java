package isw.farmasysbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoteRequest {

    private String nroLote;
    private LocalDate fechaVencimiento;
    private Integer stockFisico;
    private Integer idSede;
    private String codBarrasProducto;
}
