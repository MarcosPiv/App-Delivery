package com.mycompany.tpintegrador.accesodatos.impl.jdbc;

import com.mycompany.tpintegrador.accesodatos.ClienteDao;
import com.mycompany.tpintegrador.logica.models.Cliente;
import com.mycompany.tpintegrador.logica.models.Coordenada;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteJDBC implements ClienteDao {

    private Connection connection;

    public ClienteJDBC(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Cliente> listarClientes() {
        List<Cliente> clientes = new ArrayList<>();
        String sql = "SELECT * FROM clientes";

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Cliente cliente = new Cliente(
                        rs.getInt("id"),
                        rs.getString("cuit"),
                        rs.getString("email"),
                        rs.getString("direccion"),
                        new Coordenada(rs.getDouble("latitud"), rs.getDouble("longitud")),
                        rs.getString("nombre")
                );
                clientes.add(cliente);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return clientes;
    }

    @Override
    public void crearCliente(Cliente cliente) {
        String sql = "INSERT INTO clientes (cuit, email, direccion, latitud, longitud, nombre) VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, cliente.getCuit());
            pstmt.setString(2, cliente.getEmail());
            pstmt.setString(3, cliente.getDireccion());
            pstmt.setDouble(4, cliente.getCoordenada().getLat());
            pstmt.setDouble(5, cliente.getCoordenada().getLng());
            pstmt.setString(6, cliente.getNombre());
            pstmt.executeUpdate();

            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    cliente.setId(generatedKeys.getInt(1)); // Asignar el ID generado
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void actualizarCliente(Cliente cliente) {
        String sql = "UPDATE clientes SET cuit = ?, email = ?, direccion = ?, latitud = ?, longitud = ?, nombre = ? WHERE id = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, cliente.getCuit());
            pstmt.setString(2, cliente.getEmail());
            pstmt.setString(3, cliente.getDireccion());
            pstmt.setDouble(4, cliente.getCoordenada().getLat());
            pstmt.setDouble(5, cliente.getCoordenada().getLng());
            pstmt.setString(6, cliente.getNombre());
            pstmt.setInt(7, cliente.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void eliminarCliente(int id) {
        String sql = "DELETE FROM clientes WHERE id = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Cliente buscarCliente(int id) {
        String sql = "SELECT * FROM clientes WHERE id = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, id);
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
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public List<Cliente> buscarClientePorNombre(String nombre) {
        List<Cliente> clientes = new ArrayList<>();
        String sql = "SELECT * FROM clientes WHERE nombre LIKE ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, "%" + nombre + "%");
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Cliente cliente = new Cliente(
                            rs.getInt("id"),
                            rs.getString("cuit"),
                            rs.getString("email"),
                            rs.getString("direccion"),
                            new Coordenada(rs.getDouble("latitud"), rs.getDouble("longitud")),
                            rs.getString("nombre")
                    );
                    clientes.add(cliente);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clientes;
    }
}

