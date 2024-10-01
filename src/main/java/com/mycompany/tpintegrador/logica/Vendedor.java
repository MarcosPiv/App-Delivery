package com.mycompany.tpintegrador.logica;

import java.util.ArrayList;

public class Vendedor {
    private int id;
    private String nombre;
    private String direccion;
    private Coordenada coordenada;
    private ArrayList<ItemMenu> itemsMenu = new ArrayList<>();
    
    public Vendedor(int id, String nombre, String direccion, Coordenada coordenada) {
        this.id = id;
        this.nombre = nombre;
        this.direccion = direccion;
        this.coordenada = coordenada;
    }

    public ArrayList<Comida> getItemComida() {
        ArrayList<Comida> comidas = new ArrayList<>();
        for (ItemMenu item : itemsMenu) {
            if (item.esComida()) {
                comidas.add((Comida) item);
            }
        }
        return comidas;
    }

    public ArrayList<Bebida> getItemBebida() {
        ArrayList<Bebida> bebidas = new ArrayList<>();
        for (ItemMenu item : itemsMenu) {
            if (item.esBebida()) {
                bebidas.add((Bebida) item);
            }
        }
        return bebidas;
    }

    public ArrayList<Comida> getItemComidaVegana(){
        ArrayList<Comida> comidasVeganas = new ArrayList<>();
        for (ItemMenu item : itemsMenu) {
            if (item.esComida() && item.aptoVegano()) {
                comidasVeganas.add((Comida) item);
            }
        }
        return comidasVeganas;
    }

    public ArrayList<Bebida> getItemBebidaSinAlcohol(){
        ArrayList<Bebida> bebidasSinAlcohol = new ArrayList<>();
        for (ItemMenu item : itemsMenu) {
            if (item.esBebida() && ((Bebida) item).getGraduacionAlcoholica() == 0) {
                bebidasSinAlcohol.add((Bebida) item);
            }
        }
        return bebidasSinAlcohol;
    }

    public static Vendedor buscarVendedorPorNombre(ArrayList<Vendedor> vendedores, String nombre) {
        for (Vendedor vendedor : vendedores) {
            if (vendedor.getNombre().equalsIgnoreCase(nombre)) {
                return vendedor;
            }
        }
        return null;
    }

    public static Vendedor buscarVendedorPorId(ArrayList<Vendedor> vendedores, int id) {
        for (Vendedor vendedor : vendedores) {
            if (vendedor.getId() == id) {
                return vendedor;
            }
        }
        return null;
    }

    public double distancia(Cliente cliente) {
        //la idea salio de https://gist.github.com/vananth22/888ed9a22105670e7a4092bdcf0d72e4

        final int R = 6371; // Radio de la Tierra en kilómetros

        double lat1 = this.coordenada.getLat();
        double lon1 = this.coordenada.getLat();
        double lat2 = cliente.getCoordenada().getLat();
        double lon2 = cliente.getCoordenada().getLat();
        
        double latDistance = Math.toRadians(lat2 - lat1);
        double lonDistance = Math.toRadians(lon2 - lon1);
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = R * c; // Convertir a kilómetros

        return distance;
    }

    public void addItemMenu(ItemMenu unItemMenu){
        itemsMenu.add(unItemMenu);
    }
    
    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public Coordenada getCoordenada() {
        return coordenada;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setCoordenada(Coordenada coordenada) {
        this.coordenada = coordenada;
    }

    public ArrayList<ItemMenu> getItemsMenu() {
        return itemsMenu;
    }

    public void setItemsMenu(ArrayList<ItemMenu> itemsMenu) {
        this.itemsMenu = itemsMenu;
    }

    public boolean perteneceId(Vendedor unVendedor, int unId){
        boolean pertenece = false;
        for (ItemMenu unItemMenu: unVendedor.getItemsMenu()){
            if(unItemMenu.getId() == unId){
                pertenece=true;
            }
        }
        return pertenece;
    }

    public ItemMenu getItemById(Vendedor unVendedor, int unId){
        for (ItemMenu unItemMenu: unVendedor.getItemsMenu()){
            if(unItemMenu.getId() == unId){
                return unItemMenu;
            }
        }
        return null;
    }

    public Pedido generarPedido(int[][] itemsyCant , Vendedor vendedor,Cliente unCliente){
        Pedido nuevoPedido = new Pedido();
        for (int i = 0; i < itemsyCant.length; i++) {
            if(perteneceId(vendedor, itemsyCant[i][0])){
               ItemMenu nuevoItemMenu = getItemById(vendedor, itemsyCant[i][0]);
               DetallePedido nuevoDetalle = new DetallePedido(nuevoItemMenu.getId(), nuevoItemMenu, itemsyCant[i][1], nuevoItemMenu.getPrecio(), nuevoPedido);
               nuevoPedido.agregarDetalle(nuevoDetalle);
               nuevoPedido.setEstado(Estado.ENTREGADO);
               nuevoPedido.setCliente(unCliente);
            }
        }
        nuevoPedido.setPrecioTotal(nuevoPedido.calcularPrecioTotal());



        return nuevoPedido;
    }
    
}
