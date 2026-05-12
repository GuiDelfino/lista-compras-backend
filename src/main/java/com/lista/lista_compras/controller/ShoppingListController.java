package com.lista.lista_compras.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lista.lista_compras.dto.ItemDTO;
import com.lista.lista_compras.dto.ItemResponseDTO;
import com.lista.lista_compras.dto.ShoppingListDTO;
import com.lista.lista_compras.dto.ShoppingListResponseDTO;
import com.lista.lista_compras.services.ItemService;
import com.lista.lista_compras.services.ShoppingListService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/shopping-lists")
@Tag(
    name = "Shopping Lists",
    description = "Endpoints para gerenciamento de listas de compras"
)
public class ShoppingListController {

    private final ItemService itemService;
    private final ShoppingListService shoppingListService;

    public ShoppingListController(
        ShoppingListService shoppingListService,
        ItemService itemService) {

        this.shoppingListService = shoppingListService;
        this.itemService = itemService;
    }

    @PostMapping
    @Operation(
        summary = "Criar lista",
        description = "Cria uma nova lista de compras"
    )
    @ApiResponses(value = {
            @ApiResponse(
                responseCode = "201",
                description = "Lista criada com sucesso"
            ),
            @ApiResponse(
                responseCode = "400",
                description = "Dados inválidos"
            )
    })
    public ResponseEntity<ShoppingListResponseDTO> createList(
            @RequestBody @Valid ShoppingListDTO shoppingListDTO) {

        ShoppingListResponseDTO createdList =
                shoppingListService.createList(shoppingListDTO);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(createdList);
    }

    @PostMapping("/{id}/items")
    @Operation(
        summary = "Adicionar item na lista",
        description = "Cria um item vinculado a uma lista de compras"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "201",
            description = "Item criado com sucesso"
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Lista não encontrada"
        ),
        @ApiResponse(
            responseCode = "400",
            description = "Dados inválidos"
        )
    })
    public ResponseEntity<ItemResponseDTO> addItemToList(
                @PathVariable Long id,
                @RequestBody @Valid ItemDTO itemDTO) {

        ItemResponseDTO createdItem =
                itemService.createItemInList(id, itemDTO);

        return ResponseEntity.status(HttpStatus.CREATED)
                    .body(createdItem);
    }



    @GetMapping
    @Operation(
        summary = "Listar listas paginadas",
        description = "Retorna listas de compras paginadas"
    )
    @ApiResponse(
        responseCode = "200",
        description = "Listas encontradas"
    )

    public ResponseEntity<Page<ShoppingListResponseDTO>> getAllLists(
        @PageableDefault(size = 10, sort = "createdAt")
        Pageable pageable) {

        Page<ShoppingListResponseDTO> shoppingLists =
            shoppingListService.getAllLists(pageable);

        return ResponseEntity.ok(shoppingLists);
    }
}