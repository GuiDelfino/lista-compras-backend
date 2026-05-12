package com.lista.lista_compras.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lista.lista_compras.entities.Item;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long>{
    
}
