package edu.ifrs.si.inventorymanagerpdv.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import edu.ifrs.si.inventorymanagerpdv.model.Batch;
import edu.ifrs.si.inventorymanagerpdv.service.BatchService;

@RestController
@RequestMapping("/batches")
public class BatchController {

    private final BatchService batchService;

    public BatchController(BatchService batchService) {
        this.batchService = batchService;
    }


    @GetMapping()
    public ResponseEntity<List<Batch>> getBatchesForProductId(@RequestParam(required = true) Long product){
        List<Batch> response = batchService.findBatchesByProductId(product);
        return ResponseEntity.ok(response);
    }

}
