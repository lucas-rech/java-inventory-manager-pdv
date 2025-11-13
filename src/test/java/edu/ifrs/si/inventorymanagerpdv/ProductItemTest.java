package edu.ifrs.si.inventorymanagerpdv;

import static org.assertj.core.api.Assertions.assertThat;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;

import edu.ifrs.si.inventorymanagerpdv.model.ProductItem;
import net.minidev.json.JSONArray;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ProductItemTest {
    @Autowired
    TestRestTemplate restTemplate;



    @Test
    void shouldReturnAProductItemWhenDataExists() {
        ResponseEntity<String> response = restTemplate
                .getForEntity("/products/1", String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        DocumentContext documentContext = JsonPath.parse(response.getBody());
        Number id = documentContext.read("$.id");
        String name = documentContext.read("$.name");
        String description = documentContext.read("$.description");
        String gtin = documentContext.read("$.gtin");
        String ncm = documentContext.read("$.ncm");
        Double price = documentContext.read("$.price");
        Double cost = documentContext.read("$.cost");
        LocalDateTime createdAt = LocalDateTime.parse(documentContext.read("$.createdAt"));
        LocalDateTime updatedAt = LocalDateTime.parse(documentContext.read("$.updatedAt"));

        assertThat(id).isEqualTo(1);
        assertThat(name).isEqualTo("Fandangos");
        assertThat(description).isEqualTo("Salgadinho sabor queijo");
        assertThat(gtin).isEqualTo("7891991000137");
        assertThat(ncm).isEqualTo("19059020");
        assertThat(price).isEqualTo(9.5);
        assertThat(cost).isEqualTo(5.0);
        assertThat(createdAt).isEqualTo("2023-01-10T10:00:00");
        assertThat(updatedAt).isEqualTo("2023-01-10T12:00:00");
    }


    @Test
    void shouldNotReturnAProductWithAnUnknownId() {
        ResponseEntity<String> response = restTemplate
                .getForEntity("/products/5923423", String.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        assertThat(response.getBody()).isBlank();
    }


    @Test
    @DirtiesContext
    void shouldCreateANewnProductItem() {
        ProductItem productItem = new ProductItem(null, "Whisky Jack Daniel's Fire 1L",  "O melhor whisky que está tendo por essas bandas", "64689345670789", "12345678", 150.00, 90.89, LocalDateTime.parse("2025-02-26T13:45:24"), LocalDateTime.parse("2025-02-26T13:45:24"));

        ResponseEntity<Void> createResponse = restTemplate
                .postForEntity("/products", productItem, Void.class);
        assertThat(createResponse.getStatusCode()).isEqualTo(HttpStatus.CREATED);


        URI locationOfNewProductItem = createResponse.getHeaders().getLocation();
        ResponseEntity<String> getResponse = restTemplate
                .getForEntity(locationOfNewProductItem, String.class);
        assertThat(getResponse.getStatusCode()).isEqualTo(HttpStatus.OK);

        DocumentContext documentContext = JsonPath.parse((getResponse.getBody()));
        Number id = documentContext.read("$.id");
        assertThat(id).isNotNull();
        String name = documentContext.read("$.name");
        assertThat(name).isEqualTo("Whisky Jack Daniel's Fire 1L");
        String description = documentContext.read("$.description");
        assertThat(description).isEqualTo("O melhor whisky que está tendo por essas bandas");
        String gtin = documentContext.read("$.gtin");
        assertThat(gtin).isEqualTo("64689345670789");
        String ncm = documentContext.read("$.ncm");
        assertThat(ncm).isEqualTo("12345678");
        Double price = documentContext.read("$.price");
        assertThat(price).isEqualTo(150.00);
        Double cost = documentContext.read("$.cost");
        assertThat(cost).isEqualTo(90.89);
        String createdAt = documentContext.read("$.createdAt");
        assertThat(createdAt).isEqualTo("2025-02-26T13:45:24");
        String updatedAt = documentContext.read("$.updatedAt");
        assertThat(updatedAt).isEqualTo("2025-02-26T13:45:24");
        
    }



    @Test
    void shouldReturnAllProductItemsWhenListIsRequired() {
        ResponseEntity<String> response = restTemplate
                .getForEntity("/products", String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        DocumentContext documentContext = JsonPath.parse(response.getBody());
        int productItemsCount = documentContext.read("$.length()");
        assertThat(productItemsCount).isEqualTo(30);

        JSONArray ids = documentContext.read("$..id");
        assertThat(ids).containsExactlyInAnyOrder(IntStream.rangeClosed(1,30).boxed().toArray());
    }
}
