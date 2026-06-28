package isw.farmasysbackend.repository;

import isw.farmasysbackend.model.Usuario;
import isw.farmasysbackend.model.Rol;
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
class UsuarioRepositoryTest {
    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    RolRepository rolRepository;

    @Autowired
    SedeRepository sedeRepository;

    private Usuario usuario;
    private Rol rol;
    private Sede sede;

    @BeforeEach
    void setUp() {
        rol = Rol.builder().nombreRol("VENDEDOR").build();
        rol = rolRepository.save(rol);

        sede = Sede.builder().nroLocal("L002").direccion("Av. Peru 123").build();
        sede = sedeRepository.save(sede);
    }

    @Test
    public void testFindAll(){
        usuario = Usuario.builder()
                .username("jperez")
                .password("123456")
                .estado("ACTIVO")
                .rol(rol)
                .sede(sede)
                .build();

        usuario = usuarioRepository.save(usuario);

        List<Usuario> usuarioList = usuarioRepository.findAll();

        Assertions.assertThat(usuarioList).isNotNull();
        Assertions.assertThat(usuarioList.size()).isEqualTo(1);
    }
}
