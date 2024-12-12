package com.example.demo.servicios;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.DetallePedido;
import com.example.demo.model.ItemMenu;
import com.example.demo.repositorio.DetallePedidoRepository;
import com.example.demo.repositorio.ItemMenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DetallePedidoService implements IDetallePedidoService {

    private final DetallePedidoRepository detallePedidoRepository;
    private final ItemMenuRepository itemMenuRepository;

    @Autowired
    public DetallePedidoService(DetallePedidoRepository detallePedidoRepository, ItemMenuRepository itemMenuRepository) {
        this.detallePedidoRepository = detallePedidoRepository;
        this.itemMenuRepository = itemMenuRepository;
    }

    public DetallePedido crearDetallePedido(DetallePedido detallePedido) {
        if (detallePedido.getItem() != null) {
            // Verificamos si el ItemMenu existe, si no lo creamos
            ItemMenu itemMenu = detallePedido.getItem();
            if (!itemMenuRepository.existsById(itemMenu.getId())) {
                itemMenuRepository.save(itemMenu);
            }
        }
        // Asignamos el detalle al pedido y lo guardamos
        return detallePedidoRepository.save(detallePedido);
    }

    public DetallePedido actualizarDetallePedido(DetallePedido detallePedido) {
        if (!detallePedidoRepository.existsById(detallePedido.getId())) {
            throw new ResourceNotFoundException("El detalle de pedido con ID " + detallePedido.getId() + " no existe.");
        }

        if (detallePedido.getItem() != null) {
            // Verificamos si el ItemMenu existe, si no lo creamos
            ItemMenu itemMenu = detallePedido.getItem();
            if (!itemMenuRepository.existsById(itemMenu.getId())) {
                itemMenuRepository.save(itemMenu);
            }
        }

        return detallePedidoRepository.save(detallePedido);
    }

    public Optional<DetallePedido> buscarPorCampos(int itemId, int cantidad, double precio) {
        return detallePedidoRepository.findByItemIdAndCantidadAndPrecio(itemId, cantidad, precio);
    }

    public boolean existeDetallePedido(int id) {
        return detallePedidoRepository.existsById(id);
    }

    public List<DetallePedido> buscarPorItemMenu(int itemId) {
        return detallePedidoRepository.findByItemId(itemId);
    }
    public void eliminarDetallePedido(int id) {
        if (!detallePedidoRepository.existsById(id)) {
            throw new ResourceNotFoundException("El detalle de pedido con ID " + id + " no existe.");
        }
        detallePedidoRepository.deleteById(id);
    }
}
