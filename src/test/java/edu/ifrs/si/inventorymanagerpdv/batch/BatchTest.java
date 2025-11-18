package edu.ifrs.si.inventorymanagerpdv.batch;

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
public class BatchTest {

    @Autowired
    TestRestTemplate restTemplate;


    @Test
    void shouldReturnABatchWhenDataExists() {
        ResponseEntity<String> response = restTemplate.getForEntity("/batches?product=1", String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        DocumentContext documentContext = JsonPath.parse(response.getBody());
        System.out.println(response.getBody());

        Number id = documentContext.read("$[0].id");
        String batchId = documentContext.read("$[0].batchId");
        Number productId = documentContext.read("$[0].product.id");
        String productName = documentContext.read("$[0].product.name");
        Double cost = documentContext.read("$[0].cost");
        Number quantity = documentContext.read("$[0].quantity");
        LocalDateTime validationDate = LocalDateTime.parse(documentContext.read("$[0].validationDate"));
        LocalDateTime createdAt = LocalDateTime.parse(documentContext.read("$[0].createdAt"));
        LocalDateTime updatedAt = LocalDateTime.parse(documentContext.read("$[0].updatedAt"));

        assertThat(id).isEqualTo(1);
        assertThat(batchId).isEqualTo("123ABC456");
        assertThat(productId).isEqualTo(1);
        assertThat(productName).isEqualTo("Fandangos");
        assertThat(cost).isEqualTo(200.00);
        assertThat(quantity).isEqualTo(350);
        assertThat(validationDate).isEqualTo("2023-10-01T10:00:00");
        assertThat(createdAt).isEqualTo("2023-10-01T10:00:00");
        assertThat(updatedAt).isEqualTo("2023-10-01T10:00:00");
    }
}
