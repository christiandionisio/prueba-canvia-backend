package com.prueba.tecnica.canvia.admincore;

import com.prueba.tecnica.canvia.admincore.models.Cliente;

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

}
