package isw.farmasysbackend.dto;

import isw.farmasysbackend.model.Cliente;
import isw.farmasysbackend.model.OrdenVenta;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class OrdenVentaResponse {

    private Long id;
    private String nroOrden;
    private LocalDateTime fechaHora;
    private String canalOrigen;
    private String estado;
    private BigDecimal montoTotal;
    private Long clienteId;
    private String clienteDniRuc;
    private String clienteNombresRazonSocial;

    public static OrdenVentaResponse fromEntity(OrdenVenta ordenVenta) {
        Cliente cliente = ordenVenta.getCliente();

        return OrdenVentaResponse.builder()
                .id(ordenVenta.getId())
                .nroOrden(ordenVenta.getNroOrden())
                .fechaHora(ordenVenta.getFechaHora())
                .canalOrigen(ordenVenta.getCanalOrigen())
                .estado(ordenVenta.getEstado())
                .montoTotal(ordenVenta.getMontoTotal())
                .clienteId(cliente != null ? cliente.getId() : null)
                .clienteDniRuc(cliente != null ? cliente.getDniRuc() : null)
                .clienteNombresRazonSocial(cliente != null ? cliente.getNombresRazonSocial() : null)
                .build();
    }

    public static List<OrdenVentaResponse> fromEntities(List<OrdenVenta> ordenesVenta) {
        return ordenesVenta.stream()
                .map(OrdenVentaResponse::fromEntity)
                .toList();
    }
}
