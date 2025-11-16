package edu.ifrs.si.inventorymanagerpdv.model;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;

public record Batch(
    @Id Long id,
    String batchId,
    ProductBatchDTO product,
    Double cost,
    Integer quantity,
    LocalDateTime validationDate,
    LocalDateTime createdAt,
    LocalDateTime updatedAt
) {
}
