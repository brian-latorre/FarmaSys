package isw.farmasysbackend.dto;

import isw.farmasysbackend.model.Lote;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoteResponse {

    private Long id;
    private String nroLote;
    private LocalDate fechaVencimiento;
    private Integer stockFisico;
    private Integer idSede;
    private Long idProducto;
    private String descripcionProducto;

    public static LoteResponse fromEntity(Lote lote) {
        return LoteResponse.builder()
                .id(lote.getId())
                .nroLote(lote.getNroLote())
                .fechaVencimiento(lote.getFechaVencimiento())
                .stockFisico(lote.getStockFisico())
                .idSede(lote.getSede().getId())
                .idProducto(lote.getProducto().getId())
                .descripcionProducto(lote.getProducto().getDescripcion())
                .build();
    }
}
