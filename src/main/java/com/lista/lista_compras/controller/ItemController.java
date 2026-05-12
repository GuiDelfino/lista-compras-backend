package com.lista.lista_compras.controller;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lista.lista_compras.dto.ItemDTO;
import com.lista.lista_compras.dto.ItemResponseDTO;
import com.lista.lista_compras.services.ItemService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/items")
@Tag(name = "Items", description = "Endpoints para gerenciamento de itens da lista de compras")
public class ItemController {

    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @PostMapping
    @Operation(summary = "Criar item", description = "Cria um novo item")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Item criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos")
    })
    public ResponseEntity<ItemResponseDTO> createItem(
            @RequestBody @Valid ItemDTO itemDTO) {

        ItemResponseDTO createdItem = itemService.createItem(itemDTO);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(createdItem);
    }

    @GetMapping
    @Operation(
            summary = "Listar itens paginados",
            description = "Retorna uma lista paginada de itens"
    )
    @ApiResponse(responseCode = "200", description = "Itens encontrados")
        public ResponseEntity<Page<ItemResponseDTO>> getAllItems(
                @PageableDefault(size = 15, sort = "id")
                Pageable pageable) {

            Page<ItemResponseDTO> items =
                itemService.getAllItems(pageable);

    return ResponseEntity.ok(items);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar item por ID", description = "Retorna um item específico")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Item encontrado"),
            @ApiResponse(responseCode = "404", description = "Item não encontrado")
    })
    public ResponseEntity<ItemResponseDTO> getItemById(
            @PathVariable Long id) {

        Optional<ItemResponseDTO> item = itemService.getItemById(id);

        return item
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar item", description = "Atualiza um item existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Item atualizado"),
            @ApiResponse(responseCode = "404", description = "Item não encontrado"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos")
    })
    public ResponseEntity<ItemResponseDTO> updateItem(
            @PathVariable Long id,
            @RequestBody @Valid ItemDTO itemDTO) {

        Optional<ItemResponseDTO> updatedItem =
                itemService.updateItem(id, itemDTO);

        return updatedItem
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar item", description = "Remove um item da lista")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Item removido"),
            @ApiResponse(responseCode = "404", description = "Item não encontrado")
    })
    public ResponseEntity<Void> deleteItem(@PathVariable Long id) {

        boolean deleted = itemService.deleteItem(id);

        if (!deleted) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.noContent().build();
    }
}