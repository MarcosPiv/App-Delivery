package com.mycompany.tpintegrador.accesodatos.impl.jdbc;

import com.mycompany.tpintegrador.accesodatos.VendedorDao;
import com.mycompany.tpintegrador.logica.models.Vendedor;
import com.mycompany.tpintegrador.logica.models.Coordenada;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VendedorJDBC implements VendedorDao {
    private Connection connection;

    public VendedorJDBC(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Vendedor> listarVendedores() {
        List<Vendedor> vendedores = new ArrayList<>();
        String sql = "SELECT * FROM vendedores";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                // Crear el objeto Coordenada a partir de las columnas latitud y longitud
                Coordenada coordenada = new Coordenada(rs.getDouble("latitud"), rs.getDouble("longitud"));
                Vendedor vendedor = new Vendedor(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("direccion"),
                        coordenada // Asignar coordenada al vendedor
                );
                vendedores.add(vendedor);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vendedores;
    }

    @Override
    public void crearVendedor(Vendedor vendedor) {
        String sql = "INSERT INTO vendedores (nombre, direccion, latitud, longitud) VALUES (?, ?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, vendedor.getNombre());
            pstmt.setString(2, vendedor.getDireccion());
            pstmt.setDouble(3, vendedor.getCoordenada().getLat());
            pstmt.setDouble(4, vendedor.getCoordenada().getLng());
            pstmt.executeUpdate();

            // Obtener el ID generado y asignarlo al vendedor
            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    vendedor.setId(generatedKeys.getInt(1));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void actualizarVendedor(Vendedor vendedor) {
        String sql = "UPDATE vendedores SET nombre = ?, direccion = ?, latitud = ?, longitud = ? WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, vendedor.getNombre());
            pstmt.setString(2, vendedor.getDireccion());
            pstmt.setDouble(3, vendedor.getCoordenada().getLat());
            pstmt.setDouble(4, vendedor.getCoordenada().getLng());
            pstmt.setInt(5, vendedor.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void eliminarVendedor(int id) {
        String sql = "DELETE FROM vendedores WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Vendedor buscarVendedorPorId(int id) {
        String sql = "SELECT * FROM vendedores WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    Coordenada coordenada = new Coordenada(rs.getDouble("latitud"), rs.getDouble("longitud"));
                    return new Vendedor(
                            rs.getInt("id"),
                            rs.getString("nombre"),
                            rs.getString("direccion"),
                            coordenada
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}

