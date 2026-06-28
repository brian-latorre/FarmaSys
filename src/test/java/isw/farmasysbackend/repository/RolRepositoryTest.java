package isw.farmasysbackend.repository;

import isw.farmasysbackend.model.Rol;
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
class RolRepositoryTest {
    @Autowired
    RolRepository rolRepository;
    private Rol rol;

    @BeforeEach
    void setUp() {
    }

    @Test
    public void testFindAll(){
        rol = Rol.builder()
                .nombreRol("ADMIN")
                .build();

        rol = rolRepository.save(rol);

        List<Rol> rolList = rolRepository.findAll();

        Assertions.assertThat(rolList).isNotNull();
        Assertions.assertThat(rolList.size()).isEqualTo(1);
    }
}
