package com.example.demo.repositorio;

import com.example.demo.model.DetallePedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DetallePedidoRepository extends JpaRepository<DetallePedido, Integer> {
    Optional<DetallePedido> findByItemIdAndCantidadAndPrecio(int itemId, int cantidad, double precio);
    List<DetallePedido> findByItemId(int itemId);
}
