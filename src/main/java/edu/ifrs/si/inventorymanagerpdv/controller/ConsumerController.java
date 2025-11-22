package edu.ifrs.si.inventorymanagerpdv.controller;

import edu.ifrs.si.inventorymanagerpdv.model.Consumer;
import edu.ifrs.si.inventorymanagerpdv.service.ConsumerService;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/consumers")
public class ConsumerController {

    private final ConsumerService consumerService;

    public ConsumerController(ConsumerService consumerService) {
        this.consumerService = consumerService;
    }



    @GetMapping
    public ResponseEntity<List<Consumer>> getAllConsumers(Pageable pageable) {
        List<Consumer> response = consumerService.getAllConsumers(pageable);

        return ResponseEntity.ok(response);
    }


    @GetMapping("/{requestedId}")
    public ResponseEntity<Consumer> getConsumerById(@PathVariable  Long requestedId) {
        Consumer response = consumerService.getConsumerById(requestedId);

        if (response == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(response);
    }
}
