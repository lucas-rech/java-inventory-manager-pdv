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
        assertThat(json.write(productItem)).extractingJsonPathStringValue("@.createdAt").isEqualTo("2023-10-01T10:00:00");
        assertThat(json.write(productItem)).extractingJsonPathStringValue("@.updatedAt").isEqualTo("2023-10-15T12:00:00");
    }

    @Test
    void cashCardDeserializationTest() throws IOException {
        String expected = """
                {
                    "id": 44,
                    "name": "Fandangos",
                    "description": "Salgadinho sabor queijo",
                    "gtin": "7891991000137",
                    "ncm": "19059020",
                    "price": 9.5,
                    "cost": 5.0,
                    "createdAt": "2023-10-01T10:00:00",
                    "updatedAt": "2023-10-15T12:00:00"
                }
                """;
        
        assertThat(json.parse(expected))
                .isEqualTo(new ProductItem(
                    44L,
                    "Fandangos",
                    "Salgadinho sabor queijo", 
                    "7891991000137", 
                    "19059020", 
                    9.5, 
                    5.0, 
                    LocalDateTime.parse("2023-10-01T10:00:00"), 
                    LocalDateTime.parse("2023-10-15T12:00:00")
                ));
        assertThat(json.parseObject(expected).id()).isEqualTo(44L);
        assertThat(json.parseObject(expected).name()).isEqualTo("Fandangos");
        assertThat(json.parseObject(expected).description()).isEqualTo("Salgadinho sabor queijo");
        assertThat(json.parseObject(expected).gtin()).isEqualTo("7891991000137");
        assertThat(json.parseObject(expected).ncm()).isEqualTo("19059020");
        assertThat(json.parseObject(expected).price()).isEqualTo(9.5);
        assertThat(json.parseObject(expected).cost()).isEqualTo(5.0);
        assertThat(json.parseObject(expected).createdAt()).isEqualTo("2023-10-01T10:00:00");
        assertThat(json.parseObject(expected).updatedAt()).isEqualTo("2023-10-15T12:00:00");
    }
}
