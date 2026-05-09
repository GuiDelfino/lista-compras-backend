package com.lista.lista_compras.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Schema(description = "DTO para criação e atualização de itens")
public class ItemDTO {

    @Schema(
        description = "Nome do produto",
        example = "Arroz"
    )
    @NotBlank(message = "O nome do produto é obrigatório.")
    private String name;

    @Schema(
        description = "Quantidade do produto",
        example = "2"
    )
    @NotNull(message = "A quantidade é obrigatória.")
    @Positive(message = "A quantidade deve ser maior que zero.")
    private Integer quantity;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}