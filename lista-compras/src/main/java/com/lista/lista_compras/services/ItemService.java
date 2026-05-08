package com.lista.lista_compras.services;
import java.util.List;

import org.springframework.stereotype.Service;

import com.lista.lista_compras.dto.ItemDTO;
import com.lista.lista_compras.entities.Item;
import com.lista.lista_compras.repositories.ItemRepository;

@Service
public class ItemService {

    private final ItemRepository itemRepository;

    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public Item createItem(ItemDTO itemDTO) {
        Item item = Item.builder()
                .name(itemDTO.getName())
                .quantity(itemDTO.getQuantity())
                .checked(false)
                .build();

        return itemRepository.save(item);
    }

    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }

}
