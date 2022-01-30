package com.prueba.tecnica.canvia.admincore.services.interfaces;

import com.prueba.tecnica.canvia.admincore.models.FacturaDetalle;

import java.util.List;

public interface IFacturaDetalleService {
    Integer registrar(Integer id_factura, Integer id_item, Integer cantidad );
    List<FacturaDetalle> obtenerListaItemsPorFactura(Integer idFactura);
}
