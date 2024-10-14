package com.mycompany.tpintegrador.logica.observer;


import com.mycompany.tpintegrador.logica.models.Pedido;

public interface Observer {
    void update(Pedido pedido);
}
