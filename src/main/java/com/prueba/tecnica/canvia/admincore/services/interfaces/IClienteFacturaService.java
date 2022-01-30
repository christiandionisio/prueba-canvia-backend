package com.prueba.tecnica.canvia.admincore.services.interfaces;

import com.prueba.tecnica.canvia.admincore.models.ClienteFactura;

public interface IClienteFacturaService {

    Integer registrar(Integer idCliente, Integer idFactura);
    ClienteFactura buscarPorFactura(Integer idFactura);
}
