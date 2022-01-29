package com.prueba.tecnica.canvia.admincore.services.interfaces;

import com.prueba.tecnica.canvia.admincore.models.ItemDetalle;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IItemDetalleService extends ICrudGenericService<ItemDetalle, Integer> {

    Page<ItemDetalle> listarPageable(Pageable pageable);
}
