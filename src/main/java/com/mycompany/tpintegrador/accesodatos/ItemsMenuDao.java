package com.mycompany.tpintegrador.accesodatos;

import com.mycompany.tpintegrador.logica.models.ItemMenu;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ItemsMenuDao extends CrudRepository<ItemMenu, Integer> {
}
