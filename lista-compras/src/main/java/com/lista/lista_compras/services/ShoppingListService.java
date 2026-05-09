package com.lista.lista_compras.services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.lista.lista_compras.dto.ItemResponseDTO;
import com.lista.lista_compras.dto.ShoppingListDTO;
import com.lista.lista_compras.dto.ShoppingListResponseDTO;
import com.lista.lista_compras.entities.Item;
import com.lista.lista_compras.entities.ShoppingList;
import com.lista.lista_compras.repositories.ShoppingListRepository;

@Service
public class ShoppingListService {

    private final ShoppingListRepository shoppingListRepository;

    public ShoppingListService(ShoppingListRepository shoppingListRepository) {
        this.shoppingListRepository = shoppingListRepository;
    }

    public ShoppingListResponseDTO createList(
            ShoppingListDTO shoppingListDTO) {

        ShoppingList shoppingList = ShoppingList.builder()
                .title(shoppingListDTO.getTitle())
                .createdAt(LocalDateTime.now())
                .build();

        ShoppingList savedList =
                shoppingListRepository.save(shoppingList);

        return convertToDTO(savedList);
    }

        public Page<ShoppingListResponseDTO> getAllLists(Pageable pageable) {

                Page<ShoppingList> shoppingLists =
                        shoppingListRepository.findAll(pageable);

                return shoppingLists.map(this::convertToDTO);
        }

    private ShoppingListResponseDTO convertToDTO(
            ShoppingList shoppingList) {

        List<ItemResponseDTO> items =
                shoppingList.getItems() == null
                ? List.of()
                : shoppingList.getItems()
                    .stream()
                    .map(this::convertItemToDTO)
                    .toList();

        return new ShoppingListResponseDTO(
                shoppingList.getId(),
                shoppingList.getTitle(),
                shoppingList.getCreatedAt(),
                items
        );
    }

    private ItemResponseDTO convertItemToDTO(Item item) {

        return new ItemResponseDTO(
                item.getId(),
                item.getName(),
                item.getQuantity()
        );
    }
}