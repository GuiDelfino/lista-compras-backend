package com.lista.lista_compras.dto;

import java.time.LocalDateTime;
import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "DTO de resposta da lista de compras")
public class ShoppingListResponseDTO {

    @Schema(example = "1")
    private Long id;

    @Schema(example = "Compras do mês")
    private String title;

    private LocalDateTime createdAt;

    private List<ItemResponseDTO> items;

    public ShoppingListResponseDTO(
            Long id,
            String title,
            LocalDateTime createdAt,
            List<ItemResponseDTO> items) {

        this.id = id;
        this.title = title;
        this.createdAt = createdAt;
        this.items = items;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public List<ItemResponseDTO> getItems() {
        return items;
    }
}