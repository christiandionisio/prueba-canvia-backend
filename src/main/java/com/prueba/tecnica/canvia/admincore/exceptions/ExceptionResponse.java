package com.prueba.tecnica.canvia.admincore.exceptions;

import java.time.LocalDateTime;
import java.util.Map;

public class ExceptionResponse {

    private LocalDateTime fecha;
    private String mensaje;
    private String detalles;
    private Map<String, Object> errorValidaciones;

    public ExceptionResponse() {
    }

    public ExceptionResponse(LocalDateTime fecha, String mensaje, String detalles, Map<String, Object> errorValidaciones) {
        super();
        this.fecha = fecha;
        this.mensaje = mensaje;
        this.detalles = detalles;
        this.errorValidaciones = errorValidaciones;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getDetalles() {
        return detalles;
    }

    public void setDetalles(String detalles) {
        this.detalles = detalles;
    }

    public Map<String, Object> getErrorValidaciones() {
        return errorValidaciones;
    }

    public void setErrorValidaciones(Map<String, Object> errorValidaciones) {
        this.errorValidaciones = errorValidaciones;
    }
}
