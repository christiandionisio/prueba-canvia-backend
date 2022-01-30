package com.prueba.tecnica.canvia.admincore.repo;

import com.prueba.tecnica.canvia.admincore.models.ItemDetalle;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IItemDetalleRepo extends IGenericRepo<ItemDetalle, Integer>{

    @Query("FROM ItemDetalle i WHERE i.nombre LIKE %:nombre%")
    List<ItemDetalle> buscarPorNombre(@Param("nombre") String nombre);
}
