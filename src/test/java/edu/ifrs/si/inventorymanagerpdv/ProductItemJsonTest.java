package edu.ifrs.si.inventorymanagerpdv;

import edu.ifrs.si.inventorymanagerpdv.model.ProductItem;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;

import java.io.IOException;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@JsonTest
public class ProductItemJsonTest {

    @Autowired
    private JacksonTester<ProductItem> json;

    @Test
    void cashCardSerializationTest() throws IOException {
        ProductItem productItem = new ProductItem(
                44L,
                "Fandangos",
                "Salgadinho sabor queijo",
                "7891991000137",
                "19059020",
                9.5,
                5.0,
                LocalDateTime.parse("2023-10-01T10:00:00"),
                LocalDateTime.parse("2023-10-15T12:00:00")
        );

        assertThat(json.write(productItem)).isStrictlyEqualToJson("single-product.json");
        assertThat(json.write(productItem)).hasJsonPathNumberValue("@.id");
        assertThat(json.write(productItem)).hasJsonPathStringValue("@.name");
        assertThat(json.write(productItem)).hasJsonPathStringValue("@.description");
        assertThat(json.write(productItem)).hasJsonPathStringValue("@.gtin");
        assertThat(json.write(productItem)).hasJsonPathStringValue("@.ncm");
        assertThat(json.write(productItem)).hasJsonPathNumberValue("@.price");
        assertThat(json.write(productItem)).hasJsonPathNumberValue("@.cost");
        assertThat(json.write(productItem)).hasJsonPathStringValue("@.createdAt");
        assertThat(json.write(productItem)).hasJsonPathStringValue("@.updatedAt");

        assertThat(json.write(productItem)).extractingJsonPathNumberValue("@.id").isEqualTo(44);
        assertThat(json.write(productItem)).extractingJsonPathStringValue("@.name").isEqualTo("Fandangos");
        assertThat(json.write(productItem)).extractingJsonPathStringValue("@.description").isEqualTo("Salgadinho sabor queijo");
        assertThat(json.write(productItem)).extractingJsonPathStringValue("@.gtin").isEqualTo("7891991000137");
        assertThat(json.write(productItem)).extractingJsonPathStringValue("@.ncm").isEqualTo("19059020");
        assertThat(json.write(productItem)).extractingJsonPathNumberValue("@.price").isEqualTo(9.5);
        assertThat(json.write(productItem)).extractingJsonPathNumberValue("@.cost").isEqualTo(5.0);
        assertThat(json.write(productItem)).extractingJsonPathStringValue("@.createdAt").isEqualTo("2023-10-01 10:00:00");
        assertThat(json.write(productItem)).extractingJsonPathStringValue("@.updatedAt").isEqualTo("2023-10-15 12:00:00");
    }
}
