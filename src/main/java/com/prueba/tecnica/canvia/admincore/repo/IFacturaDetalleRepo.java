package com.prueba.tecnica.canvia.admincore.repo;

import com.prueba.tecnica.canvia.admincore.models.FacturaDetalle;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface IFacturaDetalleRepo extends IGenericRepo<FacturaDetalle, Integer>{

    @Query(value = "CALL insertar_factura_detalle(:id_factura, :id_item, :cantidad);", nativeQuery = true)
    Integer registrar(@Param("id_factura") Integer id_factura, @Param("id_item") Integer id_item,
                      @Param("cantidad") Integer cantidad );
}
