package isw.farmasysbackend.repository;

import isw.farmasysbackend.model.Cliente;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;
import org.springframework.test.context.TestPropertySource;

import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@TestPropertySource(properties = {
    "spring.flyway.enabled=false",
    "spring.jpa.hibernate.ddl-auto=create-drop"
})
class ClienteRepositoryTest {
    @Autowired
    ClienteRepository clienteRepository;
    private Cliente cliente;

    @BeforeEach
    void setUp() {
    }

    @Test
    public void testFindAll(){
        cliente = Cliente.builder()
                .dniRuc("12345678")
                .nombresRazonSocial("Juan Perez")
                .telefono("999888777")
                .direccion("Av. Siempre Viva 123")
                .build();

        cliente = clienteRepository.save(cliente);

        List<Cliente> clienteList = clienteRepository.findAll();

        Assertions.assertThat(clienteList).isNotNull();
        Assertions.assertThat(clienteList.size()).isEqualTo(1);
    }
}
