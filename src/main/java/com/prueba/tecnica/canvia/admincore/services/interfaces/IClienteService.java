package com.prueba.tecnica.canvia.admincore.services.interfaces;

import com.prueba.tecnica.canvia.admincore.models.Cliente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IClienteService extends ICrudGenericService<Cliente, Integer> {

    Page<Cliente> listarPageable(Pageable pageable);
}
