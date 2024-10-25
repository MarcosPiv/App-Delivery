package com.mycompany.tpintegrador.accesodatos;

import com.mycompany.tpintegrador.logica.models.ItemMenu;
import java.util.List;

public interface ItemsMenuDao {
    List<ItemMenu> listarItems();
    void crearItem(ItemMenu itemMenu);
    void actualizarItem(ItemMenu itemMenu);
    void eliminarItem(int id);
    ItemMenu buscarItem(int id);
}
