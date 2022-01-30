package com.prueba.tecnica.canvia.admincore.services.interfaces;

import com.prueba.tecnica.canvia.admincore.models.ItemDetalle;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IItemDetalleService extends ICrudGenericService<ItemDetalle, Integer> {

    Page<ItemDetalle> listarPageable(Pageable pageable);
    List<ItemDetalle> buscarPorNombre(String nombre);
}
