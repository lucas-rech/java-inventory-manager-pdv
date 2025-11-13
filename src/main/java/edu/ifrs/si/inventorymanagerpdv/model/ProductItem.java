package edu.ifrs.si.inventorymanagerpdv.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;

public record ProductItem(
        @Id Long id,
        String name,
        String description,
        String gtin,
        String ncm,
        Double price,
        Double cost,

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
        LocalDateTime createdAt,

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
        LocalDateTime updatedAt) {
}
