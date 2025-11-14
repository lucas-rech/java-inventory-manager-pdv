package edu.ifrs.si.inventorymanagerpdv.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;

import java.time.LocalDateTime;

public record User(
        @Id Long id,
        String name,
        String nickname,
        String phoneNumber,
        Role role,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        boolean deleted
) {
}
