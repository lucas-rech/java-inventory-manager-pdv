package edu.ifrs.si.inventorymanagerpdv.batch;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BatchTest {

    @Autowired
    TestRestTemplate restTemplate;


    @Test
    void shouldReturnABatchWhenDataExists() {
        ResponseEntity<String> response = restTemplate.getForEntity("/batches?product=1", String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }



}
