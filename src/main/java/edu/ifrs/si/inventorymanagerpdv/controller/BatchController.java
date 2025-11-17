package edu.ifrs.si.inventorymanagerpdv.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import edu.ifrs.si.inventorymanagerpdv.model.Batch;
import edu.ifrs.si.inventorymanagerpdv.repository.BatchRepository;

@RestController
@RequestMapping("/batches")
public class BatchController {

    private final BatchRepository batchRepository;

    public BatchController(BatchRepository batchRepository) {
        this.batchRepository = batchRepository;
    }


    @GetMapping()
    public ResponseEntity<List<Batch>> getBatchesForProductId(@RequestParam(required = true) Long product){
        List<Batch> response = batchRepository.findAllByProductId(product);

        return ResponseEntity.ok().body(response);
        
    }

}
