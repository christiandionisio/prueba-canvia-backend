package com.prueba.tecnica.canvia.admincore.repo;

import com.prueba.tecnica.canvia.admincore.models.Cliente;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IClienteRepo extends IGenericRepo<Cliente, Integer> {

    @Query("FROM Cliente c WHERE c.dni LIKE %:dni%")
    List<Cliente> buscarPorDni(@Param("dni") String dni);

}
