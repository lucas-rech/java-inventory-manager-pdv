package edu.ifrs.si.inventorymanagerpdv.model;

import java.time.LocalDateTime;

public record ProductItem(
        String name,
        String description,
        String gtin,
        String ncm,
        Double price,
        Double cost,
        LocalDateTime createdAt,
        LocalDateTime updatedAt) {
}
