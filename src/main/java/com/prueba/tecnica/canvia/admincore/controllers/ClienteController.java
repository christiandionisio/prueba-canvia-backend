package com.prueba.tecnica.canvia.admincore.controllers;

import com.prueba.tecnica.canvia.admincore.models.Cliente;
import com.prueba.tecnica.canvia.admincore.services.interfaces.IClienteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ClienteController.class);

    @Autowired
    private IClienteService service;

    @PostMapping
    public ResponseEntity<Cliente> registrar(@RequestBody Cliente cliente) {
        LOGGER.info("Inicio de registro de cliente");
        return ResponseEntity.ok().body(service.registrar(cliente));
    }

}
