package edu.ifrs.si.inventorymanagerpdv.model;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;

public record Batch(
    @Id Long id,
    String batchId,
    Long productId,
    Double cost,
    Integer quantity,
    LocalDateTime validationDate,
    LocalDateTime createdAt,
    LocalDateTime updatedAt
) {
}
