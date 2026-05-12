package com.lista.lista_compras.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

@Schema(description = "DTO para criação de lista de compras")
public class ShoppingListDTO {

    @Schema(
        description = "Título da lista",
        example = "Compras do mês"
    )
    @NotBlank(message = "O título é obrigatório")
    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}