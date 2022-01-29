package com.prueba.tecnica.canvia.admincore.services.interfaces;

import com.prueba.tecnica.canvia.admincore.models.Factura;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IFacturaService extends ICrudGenericService<Factura, Integer> {

    Page<Factura> listarPageable(Pageable pageable);
}
