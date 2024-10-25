package com.mycompany.tpintegrador.accesodatos.impl.memory;

import com.mycompany.tpintegrador.accesodatos.PedidoDao;
import com.mycompany.tpintegrador.logica.models.Pedido;
import java.util.ArrayList;
import java.util.List;

public class PedidoMemory implements PedidoDao {

    private List<Pedido> listaDePedidos = new ArrayList<>();

    @Override
    public List<Pedido> listarPedidos() {
        return new ArrayList<>(listaDePedidos);
    }

    @Override
    public void crearPedido(Pedido pedido) {
        pedido.setId(listaDePedidos.size() + 1);
        listaDePedidos.add(pedido);
    }

    @Override
    public void actualizarPedido(Pedido pedido) {
        for (int i = 0; i < listaDePedidos.size(); i++) {
            if (listaDePedidos.get(i).getId() == pedido.getId()) {
                listaDePedidos.set(i, pedido);
                break;
            }
        }
    }

    @Override
    public void eliminarPedido(int id) {
        listaDePedidos.removeIf(pedido -> pedido.getId() == id);
    }

    @Override
    public Pedido buscarPedido(int id) {
        return listaDePedidos.stream()
                .filter(pedido -> pedido.getId() == id)
                .findFirst()
                .orElse(null);
    }
}
