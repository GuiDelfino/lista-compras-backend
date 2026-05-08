package com.lista.lista_compras.services;
import java.util.List;

import org.springframework.stereotype.Service;

import com.lista.lista_compras.entities.ShoppingList;
import com.lista.lista_compras.repositories.ShoppingListRepository;

@Service
public class ShoppingListService {
    
    private final ShoppingListRepository shoppingListRepository;

    public ShoppingListService(ShoppingListRepository shoppingListRepository) {
        this.shoppingListRepository = shoppingListRepository;
    }

    public ShoppingList createList(ShoppingList shoppingList) {
        return shoppingListRepository.save(shoppingList);
    }

    public List<ShoppingList> getAllLists() {
        return shoppingListRepository.findAll();
    }


}
