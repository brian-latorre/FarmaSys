package isw.farmasysbackend.repository;

import isw.farmasysbackend.model.Sede;
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
class SedeRepositoryTest {
    @Autowired
    SedeRepository sedeRepository;
    private Sede sede;

    @BeforeEach
    void setUp() {
    }

    @Test
    public void testFindAll(){
        sede = Sede.builder()
                .nroLocal("L001")
                .direccion("Av. Central 456")
                .build();

        sede = sedeRepository.save(sede);

        List<Sede> sedeList = sedeRepository.findAll();

        Assertions.assertThat(sedeList).isNotNull();
        Assertions.assertThat(sedeList.size()).isEqualTo(1);
    }
}
