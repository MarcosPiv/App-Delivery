package com.mycompany.tpintegrador.accesodatos.impl.jdbc;

import com.mycompany.tpintegrador.accesodatos.ItemsMenuDao;
import com.mycompany.tpintegrador.logica.models.Bebida;
import com.mycompany.tpintegrador.logica.models.Comida;
import com.mycompany.tpintegrador.logica.models.ItemMenu;
import com.mycompany.tpintegrador.logica.models.Categoria;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ItemsMenuJDBC implements ItemsMenuDao {

    private Connection connection;

    public ItemsMenuJDBC(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<ItemMenu> listarItems() {
        List<ItemMenu> itemsMenu = new ArrayList<>();
        String sql = "SELECT i.*, c.descripcion AS categoria_descripcion, c.tipo_item AS tipo_item "
                + "FROM items_menu i "
                + "JOIN categoria c ON i.categoria_id = c.id";

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                String tipo = rs.getString("tipo");
                ItemMenu item;

                Categoria categoria = new Categoria(
                        rs.getInt("categoria_id"),
                        rs.getString("categoria_descripcion"),
                        rs.getString("tipo_item")
                );

                if ("bebida".equals(tipo)) {
                    item = new Bebida(
                            rs.getInt("id"),
                            rs.getDouble("graduacion_alcoholica"),
                            rs.getDouble("tamanio"),
                            rs.getString("nombre"),
                            rs.getString("descripcion"),
                            rs.getDouble("precio"),
                            categoria,  // Usamos la categoría recuperada
                            rs.getDouble("peso")
                    );
                } else {
                    item = new Comida(
                            rs.getInt("id"),
                            rs.getInt("calorias"),
                            rs.getBoolean("apto_vegano"),
                            rs.getBoolean("apto_celiaco"),
                            rs.getDouble("peso_sin_envase"),
                            rs.getString("nombre"),
                            rs.getString("descripcion"),
                            rs.getDouble("precio"),
                            categoria,  // Usamos la categoría recuperada
                            rs.getDouble("peso")
                    );
                }

                itemsMenu.add(item);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return itemsMenu;
    }

    @Override
    public void crearItem(ItemMenu itemMenu) {
        String tipo = (itemMenu instanceof Bebida) ? "bebida" : "comida";
        String sql = "INSERT INTO items_menu (nombre, descripcion, precio, categoria_id, peso, tipo, graduacion_alcoholica, tamanio, calorias, apto_vegano, apto_celiaco, peso_sin_envase) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, itemMenu.getNombre());
            pstmt.setString(2, itemMenu.getDescripcion());
            pstmt.setDouble(3, itemMenu.getPrecio());
            pstmt.setInt(4, itemMenu.getCategoria().getId());  // Usar el ID de la categoría
            pstmt.setDouble(5, itemMenu.getPeso());
            pstmt.setString(6, tipo);

            if (itemMenu instanceof Bebida) {
                Bebida bebida = (Bebida) itemMenu;
                pstmt.setDouble(7, bebida.getGraduacionAlcoholica());
                pstmt.setDouble(8, bebida.getTamanio());
                pstmt.setNull(9, Types.INTEGER);
                pstmt.setBoolean(10, false);
                pstmt.setNull(11, Types.BOOLEAN);
                pstmt.setNull(12, Types.DOUBLE);
            } else if (itemMenu instanceof Comida) {
                Comida comida = (Comida) itemMenu;
                pstmt.setNull(7, Types.DOUBLE);
                pstmt.setNull(8, Types.DOUBLE);
                pstmt.setInt(9, comida.getCalorias());
                pstmt.setBoolean(10, comida.isAptoVegano());
                pstmt.setBoolean(11, comida.isAptoCeliaco());
                pstmt.setDouble(12, comida.getPesoSinEnvase());
            }

            pstmt.executeUpdate();

            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    itemMenu.setId(generatedKeys.getInt(1)); // Asigna el ID generado
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void actualizarItem(ItemMenu itemMenu) {
        String tipo = (itemMenu instanceof Bebida) ? "bebida" : "comida";
        String sql = "UPDATE items_menu SET nombre = ?, descripcion = ?, precio = ?, categoria_id = ?, peso = ?, tipo = ?, graduacion_alcoholica = ?, tamanio = ?, calorias = ?, apto_vegano = ?, apto_celiaco = ?, peso_sin_envase = ? WHERE id = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, itemMenu.getNombre());
            pstmt.setString(2, itemMenu.getDescripcion());
            pstmt.setDouble(3, itemMenu.getPrecio());
            pstmt.setInt(4, itemMenu.getCategoria().getId());
            pstmt.setDouble(5, itemMenu.getPeso());
            pstmt.setString(6, tipo);

            if (itemMenu instanceof Bebida) {
                Bebida bebida = (Bebida) itemMenu;
                pstmt.setDouble(7, bebida.getGraduacionAlcoholica());
                pstmt.setDouble(8, bebida.getTamanio());
                pstmt.setNull(9, Types.INTEGER);
                pstmt.setNull(10, Types.BOOLEAN);
                pstmt.setNull(11, Types.BOOLEAN);
                pstmt.setNull(12, Types.DOUBLE);
            } else if (itemMenu instanceof Comida) {
                Comida comida = (Comida) itemMenu;
                pstmt.setNull(7, Types.DOUBLE);
                pstmt.setNull(8, Types.DOUBLE);
                pstmt.setInt(9, comida.getCalorias());
                pstmt.setBoolean(10, comida.isAptoVegano());
                pstmt.setBoolean(11, comida.isAptoCeliaco());
                pstmt.setDouble(12, comida.getPesoSinEnvase());
            }

            pstmt.setInt(13, itemMenu.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void eliminarItem(int id) {
        String sql = "DELETE FROM items_menu WHERE id = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ItemMenu buscarItem(int id) {
        String sql = "SELECT i.*, c.descripcion AS categoria_descripcion, c.tipo_item AS tipo_item "
                + "FROM items_menu i "
                + "JOIN categoria c ON i.categoria_id = c.id WHERE i.id = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    String tipo = rs.getString("tipo");
                    ItemMenu item;

                    Categoria categoria = new Categoria(
                            rs.getInt("categoria_id"),
                            rs.getString("categoria_descripcion"),
                            rs.getString("tipo_item")
                    );

                    if ("bebida".equals(tipo)) {
                        item = new Bebida(
                                rs.getInt("id"),
                                rs.getDouble("graduacion_alcoholica"),
                                rs.getDouble("tamanio"),
                                rs.getString("nombre"),
                                rs.getString("descripcion"),
                                rs.getDouble("precio"),
                                categoria,
                                rs.getDouble("peso")
                        );
                    } else {
                        item = new Comida(
                                rs.getInt("id"),
                                rs.getInt("calorias"),
                                rs.getBoolean("apto_vegano"),
                                rs.getBoolean("apto_celiaco"),
                                rs.getDouble("peso_sin_envase"),
                                rs.getString("nombre"),
                                rs.getString("descripcion"),
                                rs.getDouble("precio"),
                                categoria,
                                rs.getDouble("peso")
                        );
                    }

                    return item;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}
