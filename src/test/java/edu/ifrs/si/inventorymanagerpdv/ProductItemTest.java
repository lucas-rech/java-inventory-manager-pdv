package edu.ifrs.si.inventorymanagerpdv;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;

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

}
