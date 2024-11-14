package com.mycompany.tpintegrador.accesodatos.impl.jdbc;

import com.mycompany.tpintegrador.accesodatos.PedidoDao;
import com.mycompany.tpintegrador.logica.models.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PedidoJDBC implements PedidoDao {

    private Connection connection;

    public PedidoJDBC(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Pedido> listarPedidos() {
        List<Pedido> pedidos = new ArrayList<>();
        String sql = "SELECT * FROM pedidos";

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Pedido pedido = obtenerPedidoDesdeResultSet(rs);
                pedidos.add(pedido);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return pedidos;
    }

    @Override
    public void crearPedido(Pedido pedido) {
        String sql = "INSERT INTO pedidos (cliente_id, vendedor_id, precio_total, estado) VALUES (?, ?, ?, ?)";

        try (PreparedStatement pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setInt(1, pedido.getCliente().getId());
            pstmt.setInt(2, pedido.getRestaurante().getId());
            pstmt.setDouble(3, pedido.getPrecioTotal());
            pstmt.setString(4, pedido.getEstado().toString());
            pstmt.executeUpdate();

            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    pedido.setId(generatedKeys.getInt(1));
                 //   crearDetallesPedido(pedido);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void actualizarPedido(Pedido pedido) {
        String sql = "UPDATE pedidos SET cliente_id = ?, vendedor_id = ?, precio_total = ?, estado = ? WHERE id = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, pedido.getCliente().getId());
            pstmt.setInt(2, pedido.getRestaurante().getId());
            pstmt.setDouble(3, pedido.getPrecioTotal());
            pstmt.setString(4, pedido.getEstado().toString());
            pstmt.setInt(5, pedido.getId());
            pstmt.executeUpdate();


            eliminarDetallesPedido(pedido.getId());
            crearDetallesPedido(pedido);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void eliminarPedido(int id) {
        try {
            eliminarDetallesPedido(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        String sql = "DELETE FROM pedidos WHERE id = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Pedido buscarPedido(int id) {
        String sql = "SELECT * FROM pedidos WHERE id = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return obtenerPedidoDesdeResultSet(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    private Pedido obtenerPedidoDesdeResultSet(ResultSet rs) throws SQLException {
        Pedido pedido = new Pedido();
        pedido.setId(rs.getInt("id"));


        pedido.setCliente(obtenerClientePorId(rs.getInt("cliente_id")));
        pedido.setRestaurante(obtenerVendedorPorId(rs.getInt("vendedor_id")));


        pedido.setEstado(Estado.valueOf(rs.getString("estado")));


        pedido.setDetallesPedido(new ArrayList<>(obtenerDetallesPedido(pedido.getId())));

        pedido.setPrecioTotal(rs.getDouble("precio_total"));

        return pedido;
    }

    private Cliente obtenerClientePorId(int clienteId) throws SQLException {
        String sql = "SELECT * FROM clientes WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, clienteId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new Cliente(
                            rs.getInt("id"),
                            rs.getString("cuit"),
                            rs.getString("email"),
                            rs.getString("direccion"),
                            new Coordenada(rs.getDouble("latitud"), rs.getDouble("longitud")),
                            rs.getString("nombre")
                    );
                }
            }
        }
        return null;
    }

    private Vendedor obtenerVendedorPorId(int vendedorId) throws SQLException {
        String sql = "SELECT * FROM vendedores WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, vendedorId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new Vendedor(
                            rs.getInt("id"),
                            rs.getString("nombre"),
                            rs.getString("direccion"),
                            new Coordenada(rs.getDouble("latitud"), rs.getDouble("longitud"))
                    );
                }
            }
        }
        return null;
    }

    private List<DetallePedido> obtenerDetallesPedido(int pedidoId) throws SQLException {
        List<DetallePedido> detalles = new ArrayList<>();
        String sql = "SELECT * FROM detalles_pedido WHERE pedido_id = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, pedidoId);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    DetallePedido detalle = new DetallePedido(
                            rs.getInt("id"),
                            obtenerItemMenuPorId(rs.getInt("item_menu_id")),
                            rs.getInt("cantidad"),
                            rs.getDouble("precio"),
                            buscarPedido(pedidoId)
                    );
                    detalles.add(detalle);
                }
            }
        }

        return detalles;
    }

    private ItemMenu obtenerItemMenuPorId(int itemMenuId) throws SQLException {
        String sql = "SELECT * FROM items_menu WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, itemMenuId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    String tipo = rs.getString("tipo");
                    Categoria categoria = new Categoria(rs.getInt("categoria_id"), rs.getString("categoria_descripcion"), rs.getString("tipo_item"));
                    if ("bebida".equals(tipo)) {
                        return new Bebida(rs.getInt("id"), rs.getDouble("graduacion_alcoholica"), rs.getDouble("tamanio"),
                                rs.getString("nombre"), rs.getString("descripcion"), rs.getDouble("precio"), categoria, rs.getDouble("peso"));
                    } else {
                        return new Comida(rs.getInt("id"), rs.getInt("calorias"), rs.getBoolean("apto_vegano"), rs.getBoolean("apto_celiaco"),
                                rs.getDouble("peso_sin_envase"), rs.getString("nombre"), rs.getString("descripcion"), rs.getDouble("precio"), categoria, rs.getDouble("peso"));
                    }
                }
            }
        }
        return null;
    }

    private void crearDetallesPedido(Pedido pedido) throws SQLException {
        String sql = "INSERT INTO detalles_pedido (pedido_id, item_menu_id, cantidad, precio) VALUES (?, ?, ?, ?)";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            for (DetallePedido detalle : pedido.getDetallesPedido()) {
                pstmt.setInt(1, pedido.getId());
                pstmt.setInt(2, detalle.getItem().getId());
                pstmt.setInt(3, detalle.getCantidad());
                pstmt.setDouble(4, detalle.getPrecio());
                pstmt.addBatch();
            }
            pstmt.executeBatch();
        }
    }

    private void eliminarDetallesPedido(int pedidoId) throws SQLException {
        String sql = "DELETE FROM detalles_pedido WHERE pedido_id = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, pedidoId);
            pstmt.executeUpdate();
        }
    }
}

