package com.mycompany.tpintegrador.accesodatos.impl.jdbc;

import com.mycompany.tpintegrador.accesodatos.CategoriaDao;
import com.mycompany.tpintegrador.logica.models.Categoria;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CategoriaJDBC implements CategoriaDao {

    private Connection connection;

    public CategoriaJDBC(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void crearCategoria(Categoria categoria) {
        String sql = "INSERT INTO categoria (descripcion, tipo_item) VALUES (?, ?)";

        try (PreparedStatement pstmt = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, categoria.getDescripcion());
            pstmt.setString(2, categoria.getTipoItem());
            pstmt.executeUpdate();

            // Obtener el ID generado por la base de datos y asignarlo a la categoría
            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    categoria.setId(generatedKeys.getInt(1));  // Asignar el ID generado
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Categoria buscarCategoria(int id) {
        String sql = "SELECT * FROM categoria WHERE id = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new Categoria(rs.getInt("id"), rs.getString("descripcion"), rs.getString("tipo_item"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void actualizarCategoria(Categoria categoria) {
        String sql = "UPDATE categoria SET descripcion = ?, tipo_item = ? WHERE id = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, categoria.getDescripcion());
            pstmt.setString(2, categoria.getTipoItem());
            pstmt.setInt(3, categoria.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void eliminarCategoria(int id) {
        String sql = "DELETE FROM categoria WHERE id = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
