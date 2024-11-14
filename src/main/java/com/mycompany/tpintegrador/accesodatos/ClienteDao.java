package com.mycompany.tpintegrador.accesodatos;

import com.mycompany.tpintegrador.logica.models.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClienteDao extends JpaRepository<Cliente, Integer> {
    List<Cliente> findClienteByNombre(String nombre);
}
