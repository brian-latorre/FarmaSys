package isw.farmasysbackend.repository;

import isw.farmasysbackend.model.OrdenVenta;
import isw.farmasysbackend.model.Cliente;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;
import org.springframework.test.context.TestPropertySource;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@TestPropertySource(properties = {
    "spring.flyway.enabled=false",
    "spring.jpa.hibernate.ddl-auto=create-drop"
})
class OrdenVentaRepositoryTest {
    @Autowired
    OrdenVentaRepository ordenVentaRepository;

    @Autowired
    ClienteRepository clienteRepository;

    private OrdenVenta ordenVenta;
    private Cliente cliente;

    @BeforeEach
    void setUp() {
        cliente = Cliente.builder()
                .dniRuc("87654321")
                .nombresRazonSocial("Empresa SAC")
                .telefono("111222333")
                .direccion("Calle Falsa 123")
                .build();
        cliente = clienteRepository.save(cliente);
    }

    @Test
    public void testFindAll(){
        ordenVenta = OrdenVenta.builder()
                .nroOrden("ORD-001")
                .fechaHora(LocalDateTime.now())
                .canalOrigen("WEB")
                .estado("PAGADO")
                .montoTotal(new BigDecimal("150.50"))
                .cliente(cliente)
                .build();

        ordenVenta = ordenVentaRepository.save(ordenVenta);

        List<OrdenVenta> ordenVentaList = ordenVentaRepository.findAll();

        Assertions.assertThat(ordenVentaList).isNotNull();
        Assertions.assertThat(ordenVentaList.size()).isEqualTo(1);
    }
}
