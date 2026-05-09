package com.lista.lista_compras.services;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.lista.lista_compras.dto.ItemDTO;
import com.lista.lista_compras.dto.ItemResponseDTO;
import com.lista.lista_compras.entities.Item;
import com.lista.lista_compras.entities.ShoppingList;
import com.lista.lista_compras.repositories.ItemRepository;
import com.lista.lista_compras.repositories.ShoppingListRepository;

@Service
public class ItemService {

    private final ItemRepository itemRepository;
    private final ShoppingListRepository shoppingListRepository;
    
    public ItemService(
            ItemRepository itemRepository,
            ShoppingListRepository shoppingListRepository) {

            this.itemRepository = itemRepository;
            this.shoppingListRepository = shoppingListRepository;
    }

    public ItemResponseDTO createItem(ItemDTO itemDTO) {

        Item item = Item.builder()
                .name(itemDTO.getName())
                .quantity(itemDTO.getQuantity())
                .checked(false)
                .build();

        Item savedItem = itemRepository.save(item);

        return convertToDTO(savedItem);
    }

    public Page<ItemResponseDTO> getAllItems(Pageable pageable) {

        List<Item> items = itemRepository.findAll();

            Page<Item> itemsPage = itemRepository.findAll(pageable);

            return itemsPage.map(this::convertToDTO);
    }

    public Optional<ItemResponseDTO> getItemById(Long id) {

        return itemRepository.findById(id)
                .map(this::convertToDTO);
    }

    public Optional<ItemResponseDTO> updateItem(Long id, ItemDTO itemDTO) {

        Optional<Item> itemOptional = itemRepository.findById(id);

        if (itemOptional.isEmpty()) {
            return Optional.empty();
        }

        Item item = itemOptional.get();

        item.setName(itemDTO.getName());
        item.setQuantity(itemDTO.getQuantity());

        Item updatedItem = itemRepository.save(item);

        return Optional.of(convertToDTO(updatedItem));
    }

    public boolean deleteItem(Long id) {

        Optional<Item> itemOptional = itemRepository.findById(id);

        if (itemOptional.isEmpty()) {
            return false;
        }

        itemRepository.deleteById(id);

        return true;
    }

    private ItemResponseDTO convertToDTO(Item item) {

        return new ItemResponseDTO(
                item.getId(),
                item.getName(),
                item.getQuantity()
        );
    }

    public ItemResponseDTO createItemInList(
        Long shoppingListId,
        ItemDTO itemDTO) {

    ShoppingList shoppingList = shoppingListRepository
            .findById(shoppingListId)
            .orElseThrow(() ->
                    new RuntimeException("Lista não encontrada"));

    Item item = Item.builder()
            .name(itemDTO.getName())
            .quantity(itemDTO.getQuantity())
            .checked(false)
            .shoppingList(shoppingList)
            .build();

    Item savedItem = itemRepository.save(item);

    return convertToDTO(savedItem);
    }
}