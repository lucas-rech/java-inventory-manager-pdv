package edu.ifrs.si.inventorymanagerpdv.model;

import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

public record Consumer(
        @Id Long id,
        String name,
        String lastName,
        String email,
        String document,
        String phoneNumber,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
