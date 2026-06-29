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
public class AlertaStockResponse {
    private String nombreProducto;
    private String nroLote;
    private Integer stockActual;
    private LocalDate fechaVencimiento;
    private String estado;
}
