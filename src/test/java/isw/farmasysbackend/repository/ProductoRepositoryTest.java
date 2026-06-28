package isw.farmasysbackend.repository;

import isw.farmasysbackend.model.Producto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;

import java.math.BigDecimal;
import org.springframework.test.context.TestPropertySource;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@TestPropertySource(properties = {
    "spring.flyway.enabled=false",
    "spring.jpa.hibernate.ddl-auto=create-drop"
})
class ProductoRepositoryTest {
    @Autowired
    ProductoRepository productoRepository;
    private Producto producto;


    @BeforeEach
    void setUp() {
    }

    @Test
    public void testFindAll(){
        producto = Producto.builder()
                .codBarras("7751234567890")
                .descripcion("Paracetamol 500mg")
                .precioVenta(new BigDecimal("5.50"))
                .precioCosto(new BigDecimal("2.00"))
                .laboratorio("Genfar")
                .condicionVenta("Sin receta")
                .build();

        producto = productoRepository.save(producto);

        List<Producto> productoList = productoRepository.findAll();

        Assertions.assertThat(productoList).isNotNull();
        Assertions.assertThat(productoList.size()).isEqualTo(1);
    }

    @Test
    public void testSaveProducto() {
        Producto newProducto = Producto.builder()
                .codBarras("1234567890123")
                .descripcion("Ibuprofeno 400mg")
                .precioVenta(new BigDecimal("4.50"))
                .precioCosto(new BigDecimal("1.50"))
                .laboratorio("Bayer")
                .condicionVenta("Sin receta")
                .build();

        Producto savedProducto = productoRepository.save(newProducto);

        Assertions.assertThat(savedProducto).isNotNull();
        Assertions.assertThat(savedProducto.getId()).isGreaterThan(0);
    }

    @Test
    public void testFindById() {
        producto = Producto.builder()
                .codBarras("7751234567892")
                .descripcion("Amoxicilina 500mg")
                .precioVenta(new BigDecimal("8.50"))
                .precioCosto(new BigDecimal("3.00"))
                .laboratorio("Teva")
                .condicionVenta("Con receta")
                .build();
        productoRepository.save(producto);

        Producto foundProducto = productoRepository.findById(producto.getId()).orElse(null);

        Assertions.assertThat(foundProducto).isNotNull();
        Assertions.assertThat(foundProducto.getCodBarras()).isEqualTo("7751234567892");
    }

    @Test
    public void testFindByCodBarras() {
        producto = Producto.builder()
                .codBarras("7751234567893")
                .descripcion("Loratadina 10mg")
                .precioVenta(new BigDecimal("2.50"))
                .precioCosto(new BigDecimal("0.80"))
                .laboratorio("Medifarma")
                .condicionVenta("Sin receta")
                .build();
        productoRepository.save(producto);

        Producto foundProducto = productoRepository.findByCodBarras("7751234567893").orElse(null);

        Assertions.assertThat(foundProducto).isNotNull();
        Assertions.assertThat(foundProducto.getDescripcion()).isEqualTo("Loratadina 10mg");
    }

    @Test
    public void testUpdateProducto() {
        producto = Producto.builder()
                .codBarras("7751234567894")
                .descripcion("Diclofenaco 50mg")
                .precioVenta(new BigDecimal("3.50"))
                .precioCosto(new BigDecimal("1.00"))
                .laboratorio("Genfar")
                .condicionVenta("Sin receta")
                .build();
        productoRepository.save(producto);

        Producto savedProducto = productoRepository.findById(producto.getId()).orElse(null);
        savedProducto.setPrecioVenta(new BigDecimal("4.00"));
        savedProducto.setDescripcion("Diclofenaco 100mg");
        
        Producto updatedProducto = productoRepository.save(savedProducto);

        Assertions.assertThat(updatedProducto.getPrecioVenta()).isEqualTo(new BigDecimal("4.00"));
        Assertions.assertThat(updatedProducto.getDescripcion()).isEqualTo("Diclofenaco 100mg");
    }

    @Test
    public void testDeleteProducto() {
        producto = Producto.builder()
                .codBarras("7751234567895")
                .descripcion("Vitamina C 1g")
                .precioVenta(new BigDecimal("15.00"))
                .precioCosto(new BigDecimal("8.00"))
                .laboratorio("Cevite")
                .condicionVenta("Sin receta")
                .build();
        productoRepository.save(producto);

        productoRepository.deleteById(producto.getId());

        Producto deletedProducto = productoRepository.findById(producto.getId()).orElse(null);

        Assertions.assertThat(deletedProducto).isNull();
    }
}