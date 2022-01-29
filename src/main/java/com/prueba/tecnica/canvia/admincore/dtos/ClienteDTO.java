package com.prueba.tecnica.canvia.admincore.dtos;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class ClienteDTO {

    private Integer id;

    @NotEmpty(message = "El nombre no debe ir vacio")
    private String nombres;

    private String apellidos;

    @Size(min = 8, max = 8, message = "DNI debe tener 8 caracteres")
    private String dni;

    @Email(message = "Formato email invalido")
    private String correo;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }
}
