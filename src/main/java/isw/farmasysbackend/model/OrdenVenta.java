package isw.farmasysbackend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "orden_venta")
public class OrdenVenta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nro_orden", nullable = false, unique = true, length = 20)
    private String nroOrden;

    @Builder.Default
    @Column(name = "fecha_hora", nullable = false)
    private LocalDateTime fechaHora = LocalDateTime.now();

    @Column(name = "canal_origen", nullable = false, length = 30)
    private String canalOrigen;

    @Builder.Default
    @Column(name = "estado", nullable = false, length = 20)
    private String estado = "PENDIENTE";

    @Builder.Default
    @Column(name = "monto_total", nullable = false, precision = 10, scale = 2)
    private BigDecimal montoTotal = BigDecimal.ZERO;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_cliente", nullable = false)
    private Cliente cliente;

    @PrePersist
    void prePersist() {
        if (fechaHora == null) {
            fechaHora = LocalDateTime.now();
        }
        if (estado == null || estado.isBlank()) {
            estado = "PENDIENTE";
        }
        if (montoTotal == null) {
            montoTotal = BigDecimal.ZERO;
        }
    }
}
