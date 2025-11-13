package edu.ifrs.si.inventorymanagerpdv.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

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

    @GetMapping
    private ResponseEntity<List<ProductItem>> getAll(@PageableDefault(size = 100) Pageable pageable) {
        Page<ProductItem> page = productItemRepository.findAll(
            PageRequest.of(
                pageable.getPageNumber(),
                pageable.getPageSize(),
                pageable.getSortOr(Sort.by(Sort.Direction.ASC, "id"))
        ));

        return ResponseEntity.ok(page.getContent());
    }

    @PostMapping
    private ResponseEntity<Void> createProductItem(@RequestBody ProductItem requestProduct, UriComponentsBuilder ucb) {
        ProductItem newItem = new ProductItem(null, requestProduct.name(), requestProduct.description(), requestProduct.gtin(), requestProduct.ncm(), requestProduct.price(), requestProduct.cost(), requestProduct.createdAt(), requestProduct.updatedAt());

        ProductItem createdProductItem = productItemRepository.save(newItem);

        URI locationOfNewCashCard = ucb
                .path("/products/{id}")
                .buildAndExpand(createdProductItem.id())
                .toUri();

        return ResponseEntity.created(locationOfNewCashCard).build();
    }

}
