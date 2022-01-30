package com.prueba.tecnica.canvia.admincore.controllers;

import com.prueba.tecnica.canvia.admincore.dtos.FacturaConsolidadoDTO;
import com.prueba.tecnica.canvia.admincore.exceptions.ExceptionResponse;
import com.prueba.tecnica.canvia.admincore.models.Cliente;
import com.prueba.tecnica.canvia.admincore.models.ClienteFactura;
import com.prueba.tecnica.canvia.admincore.models.Factura;
import com.prueba.tecnica.canvia.admincore.models.FacturaDetalle;
import com.prueba.tecnica.canvia.admincore.services.interfaces.IClienteFacturaService;
import com.prueba.tecnica.canvia.admincore.services.interfaces.IClienteService;
import com.prueba.tecnica.canvia.admincore.services.interfaces.IFacturaDetalleService;
import com.prueba.tecnica.canvia.admincore.services.interfaces.IFacturaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;

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

    @GetMapping
    public ResponseEntity<List<Factura>> obtenerFacturas() {
        LOGGER.info("Iniciando busqueda de facturas");
        List<Factura> facturas = facturaService.listar();
        return ResponseEntity.ok().body(facturas);
    }

    @GetMapping("{idFactura}")
    public ResponseEntity<FacturaConsolidadoDTO> buscarPorFactura(@PathVariable Integer idFactura) {
        LOGGER.info("Iniciando busqueda de factura consolidado");

        LOGGER.info("Buscando Cliente asociado");
        ClienteFactura clienteFacturaDB = clienteFacturaService.buscarPorFactura(idFactura);

        if (clienteFacturaDB == null) {
            return ResponseEntity.notFound().build();
        }

        LOGGER.info("Buscando items de factura");
        List<FacturaDetalle> facturaDetalleListBD = facturaDetalleService.obtenerListaItemsPorFactura(idFactura);

        if (facturaDetalleListBD == null) {
            return ResponseEntity.notFound().build();
        }

        LOGGER.info("Consolidando informacion");
        FacturaConsolidadoDTO facturaConsolidadoDTO = new FacturaConsolidadoDTO();
        facturaConsolidadoDTO.setFacturaCabecera(clienteFacturaDB.getFactura());
        facturaConsolidadoDTO.setClienteFactura(clienteFacturaDB);
        facturaConsolidadoDTO.setFacturaDetalleList(facturaDetalleListBD);

        return ResponseEntity.ok().body(facturaConsolidadoDTO);
    }
}
