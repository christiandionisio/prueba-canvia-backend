package com.prueba.tecnica.canvia.admincore.controllers;

import com.prueba.tecnica.canvia.admincore.dtos.FacturaConsolidadoDTO;
import com.prueba.tecnica.canvia.admincore.exceptions.ExceptionResponse;
import com.prueba.tecnica.canvia.admincore.models.Cliente;
import com.prueba.tecnica.canvia.admincore.models.Factura;
import com.prueba.tecnica.canvia.admincore.services.interfaces.IClienteFacturaService;
import com.prueba.tecnica.canvia.admincore.services.interfaces.IClienteService;
import com.prueba.tecnica.canvia.admincore.services.interfaces.IFacturaDetalleService;
import com.prueba.tecnica.canvia.admincore.services.interfaces.IFacturaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/facturas")
public class FacturaController {

    private static final Logger LOGGER = LoggerFactory.getLogger(FacturaController.class);

    @Autowired
    private IFacturaService facturaService;

    @Autowired
    private IClienteFacturaService clienteFacturaService;

    @Autowired
    private IClienteService clienteService;

    @Autowired
    private IFacturaDetalleService facturaDetalleService;

    @PostMapping
    @Transactional
    public ResponseEntity<Object> registrarFactura (@RequestBody @Valid FacturaConsolidadoDTO request) {
        LOGGER.info("Iniciando registro de factura consolidado");

        LOGGER.info("Registra factura");
        Factura facturaCabeceraDB = facturaService.registrar(request.getFacturaCabecera());

        LOGGER.info("Validar cliente");
        Cliente clienteDB = clienteService.obtenerPorId(request.getClienteFactura().getCliente().getIdCliente());
        if (clienteDB == null) {
            return ResponseEntity.badRequest().body(new ExceptionResponse(LocalDateTime.now(), "cliente inexistente",
                    "El cliente no se encuentra registrado", null));
        }

        LOGGER.info("Registra relacion cliente factura");
        Integer responseBD = clienteFacturaService.registrar(request.getClienteFactura().getCliente().getIdCliente(),
                facturaCabeceraDB.getIdFactura());
        LOGGER.debug("responseBD {}", responseBD);

        LOGGER.info("Registra detalles factura");
        request.getFacturaDetalleList().forEach(facturaDetalle ->
                facturaDetalleService.registrar(facturaCabeceraDB.getIdFactura(),
                        facturaDetalle.getItemDetalle().getIdItem(), facturaDetalle.getCantidad())
        );

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(facturaCabeceraDB.getIdFactura()).toUri();
        LOGGER.info("Setear URI Location {}", location);

        return ResponseEntity.created(location).build();
    }
}
