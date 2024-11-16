package com.mycompany.tpintegrador.accesodatos;

import com.mycompany.tpintegrador.logica.models.Pedido;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PedidoDao extends CrudRepository<Pedido, Integer> {
}
