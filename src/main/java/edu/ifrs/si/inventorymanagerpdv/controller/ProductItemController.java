package edu.ifrs.si.inventorymanagerpdv.controller;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.ifrs.si.inventorymanagerpdv.model.ProductItem;
import edu.ifrs.si.inventorymanagerpdv.repository.ProductItemRepository;

@RestController
@RequestMapping("/products")
public class ProductItemController {

    private final ProductItemRepository productItemRepository;

    public ProductItemController(ProductItemRepository repository) {
        this.productItemRepository = repository;
    }


    @GetMapping("/{requestedId}")
    private ResponseEntity<ProductItem> findById(@PathVariable Long requestedId) {
        Optional<ProductItem> productItem = productItemRepository.findById(requestedId);

        if (productItem.isPresent()) {
            return ResponseEntity.ok(productItem.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
