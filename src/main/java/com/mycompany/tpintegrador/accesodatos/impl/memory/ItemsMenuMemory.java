package com.mycompany.tpintegrador.accesodatos.impl.memory;

import com.mycompany.tpintegrador.accesodatos.ItemsMenuDao;
import com.mycompany.tpintegrador.logica.models.ItemMenu;

import java.util.ArrayList;
import java.util.List;

public class ItemsMenuMemory implements ItemsMenuDao {

    private List<ItemMenu> itemsMenu = new ArrayList<>();

    @Override
    public List<ItemMenu> listarItems() {
        return new ArrayList<>(itemsMenu); // Retorna una copia para evitar modificaciones externas
    }

    @Override
    public void crearItem(ItemMenu itemMenu) {
        itemMenu.setId(itemsMenu.size() + 1); // Asignar un ID automáticamente
        itemsMenu.add(itemMenu);
    }

    @Override
    public void actualizarItem(ItemMenu itemMenu) {
        for (int i = 0; i < itemsMenu.size(); i++) {
            if (itemsMenu.get(i).getId() == itemMenu.getId()) {
                itemsMenu.set(i, itemMenu);
                break;
            }
        }
    }

    @Override
    public void eliminarItem(int id) {
        itemsMenu.removeIf(item -> item.getId() == id);
    }

    @Override
    public ItemMenu buscarItem(int id) {
        return itemsMenu.stream()
                .filter(item -> item.getId() == id)
                .findFirst()
                .orElse(null);
    }
}
