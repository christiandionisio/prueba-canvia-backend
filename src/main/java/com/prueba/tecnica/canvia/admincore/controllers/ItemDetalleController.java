package com.prueba.tecnica.canvia.admincore.controllers;

import com.prueba.tecnica.canvia.admincore.dtos.ItemDetalleDTO;
import com.prueba.tecnica.canvia.admincore.models.ItemDetalle;
import com.prueba.tecnica.canvia.admincore.services.interfaces.IItemDetalleService;
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
@RequestMapping("/items-detalles")
public class ItemDetalleController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ItemDetalleController.class);

    @Autowired
    private IItemDetalleService service;

    @GetMapping
    public ResponseEntity<List<ItemDetalle>> listar() {
        LOGGER.info("Inicio de listar items detalle");
        List<ItemDetalle> itemDetalleList = service.listar();
        return ResponseEntity.ok().body(itemDetalleList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ItemDetalle> buscarPorId(@PathVariable Integer id) {
        LOGGER.info("Inicio de buscar items por ID");
        ItemDetalle itemBD = service.obtenerPorId(id);

        if (itemBD == null) {
            LOGGER.info("No se encontro el item");
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().body(itemBD);
    }

    @PostMapping
    public ResponseEntity<ItemDetalle> registrar(@RequestBody @Valid ItemDetalleDTO itemDetalleDTO) {
        LOGGER.info("Inicio de registro de item");

        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        ItemDetalle itemDetalleBD = service.registrar(modelMapper.map(itemDetalleDTO, ItemDetalle.class));
        LOGGER.info("Registro item OK");

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(itemDetalleBD.getIdItem()).toUri();
        LOGGER.info("Setear URI Location {}", location);

        return ResponseEntity.created(location).build();
    }

    @PutMapping
    public ResponseEntity<ItemDetalle> actualizar(@RequestBody @Valid ItemDetalleDTO itemDetalleDTO) {
        LOGGER.info("Inicio de actualizacion de item");

        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        ItemDetalle itemDetalleBD = service.obtenerPorId(itemDetalleDTO.getId());
        if (itemDetalleBD == null) {
            LOGGER.info("No se encontro el item para actualizar");
            return ResponseEntity.notFound().build();
        }

        ItemDetalle itemActualizado = service.modificar(modelMapper.map(itemDetalleDTO, ItemDetalle.class));
        LOGGER.info("Registro item actualizado");

        return ResponseEntity.ok().body(itemActualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        LOGGER.info("Inicio eliminar item");

        ItemDetalle itemDetalleBD = service.obtenerPorId(id);
        if (itemDetalleBD == null) {
            LOGGER.info("No se encontro el item para eliminar");
            return ResponseEntity.notFound().build();
        }

        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/pageable")
    public ResponseEntity<Page<ItemDetalle>> listarPageable(Pageable pageable) {
        LOGGER.info("Inicio listado paginado items");
        Page<ItemDetalle> itemDetalle = service.listarPageable(pageable);
        return new ResponseEntity<>(itemDetalle, HttpStatus.OK);
    }

}
