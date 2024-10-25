package com.mycompany.tpintegrador.accesodatos.impl.memory;

import com.mycompany.tpintegrador.accesodatos.VendedorDao;
import com.mycompany.tpintegrador.logica.models.Vendedor;

import java.util.ArrayList;
import java.util.List;

public class VendedorMemory implements VendedorDao {
    private List<Vendedor> listaVendedores = new ArrayList<>();

    @Override
    public List<Vendedor> listarVendedores() {
        return listaVendedores;
    }

    @Override
    public void crearVendedor(Vendedor vendedor) {
        listaVendedores.add(vendedor);
    }

    @Override
    public void actualizarVendedor(Vendedor vendedor) {
        for (int i = 0; i < listaVendedores.size(); i++) {
            if (listaVendedores.get(i).getId() == vendedor.getId()) {
                listaVendedores.set(i, vendedor);
                break;
            }
        }
    }

    @Override
    public void eliminarVendedor(int id) {
        listaVendedores.removeIf(v -> v.getId() == id);
    }

    @Override
    public Vendedor buscarVendedorPorId(int id) {
        return listaVendedores.stream()
                .filter(v -> v.getId() == id)
                .findFirst()
                .orElse(null);
    }
}
