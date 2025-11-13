package edu.ifrs.si.inventorymanagerpdv;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class InventorymanagerpdvApplicationTests {
    @Autowired
    TestRestTemplate restTemplate;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    void checkDataSqlIsLoaded() {
        Integer count = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM product_item", Integer.class);
        System.out.println("Total de registros: " + count);
        assertThat(count).isGreaterThan(0);
    }

    @Test
    void shouldReturnAProductItemWhenDataExists() {
        ResponseEntity<String> response = restTemplate
                .getForEntity("/products/44", String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        DocumentContext documentContext = JsonPath.parse(response.getBody());
        Number id = documentContext.read("$.id");
        String name = documentContext.read("$.name");
        String description = documentContext.read("$.description");
        String gtin = documentContext.read("$.gtin");
        String ncm = documentContext.read("$.ncm");
        Double price = documentContext.read("$.price");
        Double cost = documentContext.read("$.cost");
        LocalDateTime createdAt = documentContext.read("$.createdAt");
        LocalDateTime updatedAt = documentContext.read("$.updatedAt");

        assertThat(id).isEqualTo(44);
        assertThat(name).isEqualTo("Fandangos");
        assertThat(description).isEqualTo("Salgadinho sabor queijo");
        assertThat(gtin).isEqualTo("7891991000137");
        assertThat(ncm).isEqualTo("19059020");
        assertThat(price).isEqualTo(9.5);
        assertThat(cost).isEqualTo(5.0);
        assertThat(createdAt).isEqualTo("2023-10-01 10:00:00");
        assertThat(updatedAt).isEqualTo("2023-10-15 12:00:00");
    }

}
