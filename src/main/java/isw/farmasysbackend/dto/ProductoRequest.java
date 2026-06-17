package isw.farmasysbackend.dto;

import isw.farmasysbackend.model.Producto;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductoRequest {

    private Long id;

    @NotBlank(message = "El codigo de barras es obligatorio")
    @Size(max = 50, message = "El codigo de barras no debe superar 50 caracteres")
    private String codBarras;

    @NotBlank(message = "La descripcion es obligatoria")
    @Size(max = 255, message = "La descripcion no debe superar 255 caracteres")
    private String descripcion;

    @NotNull(message = "El precio de venta es obligatorio")
    @DecimalMin(value = "0.00", message = "El precio de venta no puede ser negativo")
    private BigDecimal precioVenta;

    @NotNull(message = "El precio de costo es obligatorio")
    @DecimalMin(value = "0.00", message = "El precio de costo no puede ser negativo")
    private BigDecimal precioCosto;

    @NotBlank(message = "El laboratorio es obligatorio")
    @Size(max = 120, message = "El laboratorio no debe superar 120 caracteres")
    private String laboratorio;

    @Size(max = 60, message = "El registro sanitario no debe superar 60 caracteres")
    private String registroSanitario;

    @NotBlank(message = "La condicion de venta es obligatoria")
    @Size(max = 30, message = "La condicion de venta no debe superar 30 caracteres")
    @Pattern(
            regexp = "LIBRE|RECETA_SIMPLE|RECETA_RETENIDA",
            message = "La condicion de venta debe ser LIBRE, RECETA_SIMPLE o RECETA_RETENIDA"
    )
    private String condicionVenta;

    public static Producto toEntity(ProductoRequest request) {
        Producto producto = new Producto();

        producto.setCodBarras(normalize(request.getCodBarras()));
        producto.setDescripcion(normalize(request.getDescripcion()));
        producto.setPrecioVenta(request.getPrecioVenta());
        producto.setPrecioCosto(request.getPrecioCosto());
        producto.setLaboratorio(normalize(request.getLaboratorio()));
        producto.setRegistroSanitario(normalize(request.getRegistroSanitario()));
        producto.setCondicionVenta(normalize(request.getCondicionVenta()));

        return producto;
    }

    private static String normalize(String value) {
        return value == null ? null : value.trim();
    }
}
