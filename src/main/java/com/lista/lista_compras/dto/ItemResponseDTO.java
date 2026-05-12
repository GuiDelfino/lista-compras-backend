package com.lista.lista_compras.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "DTO de resposta dos itens")
public class ItemResponseDTO {

    @Schema(description = "ID do item", example = "1")
    private Long id;

    @Schema(description = "Nome do produto", example = "Arroz")
    private String name;

    @Schema(description = "Quantidade do produto", example = "2")
    private Integer quantity;

    public ItemResponseDTO() {
    }

    public ItemResponseDTO(Long id, String name, Integer quantity) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getQuantity() {
        return quantity;
    }
}