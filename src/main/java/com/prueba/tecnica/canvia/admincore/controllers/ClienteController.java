package com.prueba.tecnica.canvia.admincore.controllers;

import com.prueba.tecnica.canvia.admincore.dtos.ClienteDTO;
import com.prueba.tecnica.canvia.admincore.models.Cliente;
import com.prueba.tecnica.canvia.admincore.services.interfaces.IClienteService;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ClienteController.class);

    @Autowired
    private IClienteService service;

    @GetMapping
    public ResponseEntity<List<Cliente>> listar() {
        LOGGER.info("Inicio de listar clientes");
        List<Cliente> clienteList = service.listar();
        return ResponseEntity.ok().body(clienteList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> buscarPorId(@PathVariable Integer id) {
        LOGGER.info("Inicio de buscar cliente por ID");
        Cliente clienteBD = service.obtenerPorId(id);

        if (clienteBD == null) {
            LOGGER.info("No se encontro el cliente");
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().body(clienteBD);
    }

    @GetMapping("/buscarPorDni/{dni}")
    public ResponseEntity<List<Cliente>> buscarPorDni(@PathVariable String dni) {
        LOGGER.info("Inicio de buscar cliente por ID");
        List<Cliente> clienteListBD = service.buscarPorDni(dni);

        if (clienteListBD == null) {
            LOGGER.info("No se encontro clientes");
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().body(clienteListBD);
    }

    @PostMapping
    public ResponseEntity<Cliente> registrar(@RequestBody @Valid ClienteDTO clienteDTO) {
        LOGGER.info("Inicio de registro de cliente");

        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        Cliente clienteBD = service.registrar(modelMapper.map(clienteDTO, Cliente.class));
        LOGGER.info("Registro cliente OK");

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(clienteBD.getIdCliente()).toUri();
        LOGGER.info("Setear URI Location {}", location);

        return ResponseEntity.created(location).build();
    }

    @PutMapping
    public ResponseEntity<Cliente> actualizar(@RequestBody @Valid ClienteDTO clienteDTO) {
        LOGGER.info("Inicio de actualizacion de cliente");

        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        Cliente clienteBD = service.obtenerPorId(clienteDTO.getIdCliente());
        if (clienteBD == null) {
            LOGGER.info("No se encontro el cliente para actualizar");
            return ResponseEntity.notFound().build();
        }

        Cliente clienteActualizado = service.modificar(modelMapper.map(clienteDTO, Cliente.class));
        LOGGER.info("Registro cliente actualizado");

        return ResponseEntity.ok().body(clienteActualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        LOGGER.info("Inicio eliminar cliente");

        Cliente clienteBD = service.obtenerPorId(id);
        if (clienteBD == null) {
            LOGGER.info("No se encontro el cliente para eliminar");
            return ResponseEntity.notFound().build();
        }

        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/pageable")
    public ResponseEntity<Page<Cliente>> listarPageable(Pageable pageable) {
        LOGGER.info("Inicio listado paginado clientes");
        Page<Cliente> clientes = service.listarPageable(pageable);
        return new ResponseEntity<>(clientes, HttpStatus.OK);
    }

}
