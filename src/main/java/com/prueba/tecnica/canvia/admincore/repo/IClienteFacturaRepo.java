package com.prueba.tecnica.canvia.admincore.repo;

import com.prueba.tecnica.canvia.admincore.models.ClienteFactura;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface IClienteFacturaRepo extends IGenericRepo<ClienteFactura, Integer> {

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO cliente_factura(id_cliente, id_factura) VALUES (:idCliente, :idFactura)", nativeQuery = true)
    Integer registrar(@Param("idCliente") Integer idCliente, @Param("idFactura") Integer idFactura);
}
