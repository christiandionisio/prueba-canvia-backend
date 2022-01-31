package com.prueba.tecnica.canvia.admincore;

import com.prueba.tecnica.canvia.admincore.models.Cliente;
import com.prueba.tecnica.canvia.admincore.models.ItemDetalle;

import java.util.ArrayList;
import java.util.List;

public class Datos {

    public static List<Cliente> getClienteList() {

        Cliente cliente1 = new Cliente();
        cliente1.setIdCliente(1);
        cliente1.setNombres("Christian");
        cliente1.setApellidos("Dionisio");
        cliente1.setCorreo("christian@gmail.com");
        cliente1.setDni("12345678");

        Cliente cliente2 = new Cliente();
        cliente2.setIdCliente(1);
        cliente2.setNombres("Carlos");
        cliente2.setApellidos("Martinez");
        cliente2.setCorreo("carlos@gmail.com");
        cliente2.setDni("87654321");

        List<Cliente> clienteList = new ArrayList<>();
        clienteList.add(cliente1);
        clienteList.add(cliente2);
        return clienteList;
    }

    public static Cliente getCliente() {

        Cliente cliente1 = new Cliente();
        cliente1.setIdCliente(1);
        cliente1.setNombres("Christian");
        cliente1.setApellidos("Dionisio");
        cliente1.setCorreo("christian@gmail.com");
        cliente1.setDni("12345678");

        return cliente1;
    }

    public static List<ItemDetalle> getItemList() {

        ItemDetalle item1 = new ItemDetalle();
        item1.setIdItem(1);
        item1.setNombre("Macbook");
        item1.setTipo("laptop");
        item1.setPrecio(3500.99);

        ItemDetalle item2 = new ItemDetalle();
        item2.setIdItem(2);
        item2.setNombre("Mando Xbox");
        item2.setTipo("laptop");
        item2.setPrecio(200.99);

        List<ItemDetalle> itemDetalleList = new ArrayList<>();
        itemDetalleList.add(item1);
        itemDetalleList.add(item2);
        return itemDetalleList;
    }

    public static ItemDetalle getItem() {

        ItemDetalle item1 = new ItemDetalle();
        item1.setIdItem(1);
        item1.setNombre("Macbook");
        item1.setTipo("laptop");
        item1.setPrecio(3500.99);

        return item1;
    }

}
