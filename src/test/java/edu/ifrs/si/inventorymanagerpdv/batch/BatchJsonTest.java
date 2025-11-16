package edu.ifrs.si.inventorymanagerpdv.batch;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;

import edu.ifrs.si.inventorymanagerpdv.model.Batch;
import edu.ifrs.si.inventorymanagerpdv.model.ProductBatchDTO;
import edu.ifrs.si.inventorymanagerpdv.model.ProductItem;


@JsonTest
public class BatchJsonTest {

    @Autowired
    private JacksonTester<Batch> json;

    private ProductBatchDTO product;

    @BeforeEach
    void setUp() {
        ProductItem productItem = new ProductItem(1L, "Fandangos", "Salgadinho sabor queijo", "7891991000137", "19059020", 9.50, 5.00, LocalDateTime.parse("2023-01-10T10:00:00"), LocalDateTime.parse("2023-01-10T12:00:00"));

        product = new ProductBatchDTO(
            productItem.id(),
            productItem.name()
        );
    }

    @Test
    void batchSerializationTest() throws IOException {
        Batch batch = new Batch(
            1L, 
            "123ABC456",
            product, 
            200.00,
            350, 
            LocalDateTime.parse("2023-10-01T10:00:00"), 
            LocalDateTime.parse("2023-10-01T10:00:00"), 
            LocalDateTime.parse("2023-10-01T10:00:00")
        );

        assertThat(json.write(batch)).isStrictlyEqualToJson("single-batch.json");
        assertThat(json.write(batch)).extractingJsonPathNumberValue("@.id").isEqualTo(1);
        assertThat(json.write(batch)).extractingJsonPathStringValue("@.batchId").isEqualTo("123ABC456");
        assertThat(json.write(batch)).extractingJsonPathNumberValue("@.product.id").isEqualTo(1);
        assertThat(json.write(batch)).extractingJsonPathStringValue("@.product.name").isEqualTo("Fandangos");
        assertThat(json.write(batch)).extractingJsonPathNumberValue("@.cost").isEqualTo(200.00);
        assertThat(json.write(batch)).extractingJsonPathStringValue("@.validationDate").isEqualTo("2023-10-01T10:00:00");
        assertThat(json.write(batch)).extractingJsonPathStringValue("@.createdAt").isEqualTo("2023-10-01T10:00:00");
        assertThat(json.write(batch)).extractingJsonPathStringValue("@.updatedAt").isEqualTo("2023-10-01T10:00:00");
    }

    @Test
    void batchDeserializationTest() throws IOException {

    }


}
