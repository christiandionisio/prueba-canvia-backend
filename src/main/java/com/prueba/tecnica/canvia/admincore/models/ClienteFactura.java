package com.prueba.tecnica.canvia.admincore.models;

import javax.persistence.*;

@Entity
@Table(name = "cliente_factura")
public class ClienteFactura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_cliente", nullable = false, foreignKey = @ForeignKey(name = "FK_CF_cliente"))
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "id_factura", nullable = false, foreignKey = @ForeignKey(name = "FK_CF_factura"))
    private Factura factura;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Factura getFactura() {
        return factura;
    }

    public void setFactura(Factura factura) {
        this.factura = factura;
    }
}
