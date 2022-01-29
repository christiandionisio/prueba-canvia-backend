package com.prueba.tecnica.canvia.admincore.dtos;

import com.prueba.tecnica.canvia.admincore.models.ClienteFactura;
import com.prueba.tecnica.canvia.admincore.models.Factura;
import com.prueba.tecnica.canvia.admincore.models.FacturaDetalle;

import javax.validation.constraints.NotNull;
import java.util.List;

public class FacturaConsolidadoDTO {

    @NotNull(message = "La cabecera de la factura es obligatorio")
    private Factura facturaCabecera;

    @NotNull(message = "El cliente de la factura es obligatorio")
    private ClienteFactura clienteFactura;

    @NotNull(message = "El detalle de la factura es obligatorio")
    private List<FacturaDetalle> facturaDetalleList;

    public Factura getFacturaCabecera() {
        return facturaCabecera;
    }

    public void setFacturaCabecera(Factura facturaCabecera) {
        this.facturaCabecera = facturaCabecera;
    }

    public ClienteFactura getClienteFactura() {
        return clienteFactura;
    }

    public void setClienteFactura(ClienteFactura clienteFactura) {
        this.clienteFactura = clienteFactura;
    }

    public List<FacturaDetalle> getFacturaDetalleList() {
        return facturaDetalleList;
    }

    public void setFacturaDetalleList(List<FacturaDetalle> facturaDetalleList) {
        this.facturaDetalleList = facturaDetalleList;
    }

    @Override
    public String toString() {
        return "FacturaConsolidadoDTO{" +
                "facturaCabecera=" + facturaCabecera +
                ", clienteFactura=" + clienteFactura +
                ", facturaDetalleList=" + facturaDetalleList +
                '}';
    }
}
