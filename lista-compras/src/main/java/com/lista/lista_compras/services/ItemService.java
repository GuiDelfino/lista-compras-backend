package com.lista.lista_compras.services;
import java.util.List;

import org.springframework.stereotype.Service;

import com.lista.lista_compras.entities.Item;
import com.lista.lista_compras.repositories.ItemRepository;

@Service
public class ItemService {

    private final ItemRepository itemRepository;

    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public Item creaItem(Item item) {
        return itemRepository.save(item);
    }

    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }

}
