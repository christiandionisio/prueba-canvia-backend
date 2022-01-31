package com.prueba.tecnica.canvia.admincore.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.prueba.tecnica.canvia.admincore.Datos;
import com.prueba.tecnica.canvia.admincore.dtos.ClienteDTO;
import com.prueba.tecnica.canvia.admincore.models.Cliente;
import com.prueba.tecnica.canvia.admincore.services.interfaces.IClienteService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(ClienteController.class)
class ClienteControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private IClienteService clienteService;

    ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper();
    }

    @Test
    void testListarOk() throws Exception {

        Mockito.when(clienteService.listar()).thenReturn(Datos.getClienteList());

        mvc.perform(MockMvcRequestBuilders.get("/clientes").contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].correo").value("christian@gmail.com"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].dni").value("12345678"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].correo").value("carlos@gmail.com"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].dni").value("87654321"));

        Mockito.verify(clienteService).listar();
    }

    @Test
    void testListarPorIdOk() throws Exception {

        Mockito.when(clienteService.obtenerPorId(1)).thenReturn(Datos.getCliente());

        mvc.perform(MockMvcRequestBuilders.get("/clientes/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.correo").value("christian@gmail.com"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.dni").value("12345678"));

        Mockito.verify(clienteService).obtenerPorId(1);
    }

    @Test
    void testListarPorIdNotFound() throws Exception {

        Mockito.when(clienteService.obtenerPorId(1)).thenReturn(null);

        mvc.perform(MockMvcRequestBuilders.get("/clientes/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNotFound());

        Mockito.verify(clienteService).obtenerPorId(1);
    }

    @Test
    void testBuscarPorDni() throws Exception {

        Mockito.when(clienteService.buscarPorDni("12345678")).thenReturn(Datos.getClienteList());

        mvc.perform(MockMvcRequestBuilders.get("/clientes/buscarPorDni/12345678").contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].correo").value("christian@gmail.com"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].dni").value("12345678"));

        Mockito.verify(clienteService).buscarPorDni("12345678");
    }

    @Test
    void testBuscarPorDniNotFound() throws Exception {

        Mockito.when(clienteService.buscarPorDni("12345678")).thenReturn(null);

        mvc.perform(MockMvcRequestBuilders.get("/clientes/buscarPorDni/12345678").contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNotFound());

        Mockito.verify(clienteService).buscarPorDni("12345678");
    }

    @Test
    void testRegistrarOk() throws Exception {
        Cliente cliente = new Cliente();
        cliente.setNombres("Christian");
        cliente.setApellidos("Dionisio");
        cliente.setDni("12345678");
        cliente.setCorreo("christian@gmail.com");

        Mockito.when(clienteService.registrar(Mockito.any())).then(invocation -> {
            Cliente c = invocation.getArgument(0);
            c.setIdCliente(3);
            return c;
        });

        mvc.perform(MockMvcRequestBuilders.post("/clientes").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(cliente)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.redirectedUrl("http://localhost/clientes/3"));

        Mockito.verify(clienteService).registrar(Mockito.any());
    }

    @Test
    void testActualizarOk() throws Exception {
        ClienteDTO cliente = new ClienteDTO();
        cliente.setIdCliente(1);
        cliente.setNombres("Christian");
        cliente.setApellidos("Dionisio");
        cliente.setDni("12345678");
        cliente.setCorreo("christian@gmail.com");

        Mockito.when(clienteService.obtenerPorId(1)).thenReturn(Datos.getCliente());
        Mockito.when(clienteService.modificar(Mockito.any())).then(invocation -> {
            Cliente c = invocation.getArgument(0);
            c.setNombres(cliente.getNombres());
            c.setApellidos(cliente.getApellidos());
            c.setCorreo(cliente.getCorreo());
            c.setDni(cliente.getDni());
            return c;
        });

        mvc.perform(MockMvcRequestBuilders.put("/clientes").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(cliente)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.idCliente", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.dni", Matchers.is("12345678")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.correo", Matchers.is("christian@gmail.com")));


        Mockito.verify(clienteService).obtenerPorId(1);
        Mockito.verify(clienteService).modificar(Mockito.any());
    }

    @Test
    void testActualizarNotFound() throws Exception {
        ClienteDTO cliente = new ClienteDTO();
        cliente.setIdCliente(1);
        cliente.setNombres("Christian");
        cliente.setApellidos("Dionisio");
        cliente.setDni("12345678");
        cliente.setCorreo("christian@gmail.com");

        Mockito.when(clienteService.obtenerPorId(1)).thenReturn(null);

        mvc.perform(MockMvcRequestBuilders.put("/clientes").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(cliente)))
                .andExpect(MockMvcResultMatchers.status().isNotFound());


        Mockito.verify(clienteService).obtenerPorId(1);
    }

    @Test
    void testEliminarOk() throws Exception {

        Mockito.when(clienteService.obtenerPorId(1)).thenReturn(Datos.getCliente());

        mvc.perform(MockMvcRequestBuilders.delete("/clientes/1").contentType(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNoContent());

        Mockito.verify(clienteService).obtenerPorId(1);
    }

    @Test
    void testEliminarNotFound() throws Exception {

        Mockito.when(clienteService.obtenerPorId(1)).thenReturn(null);

        mvc.perform(MockMvcRequestBuilders.delete("/clientes/1").contentType(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNotFound());

        Mockito.verify(clienteService).obtenerPorId(1);
    }

    @Test
    void testListarPageable() throws Exception {

        Mockito.when(clienteService.listarPageable(Mockito.any())).thenReturn(
                new PageImpl<Cliente>(Datos.getClienteList(), PageRequest.of(0, 20), 400L)
        );

        mvc.perform(MockMvcRequestBuilders.get("/clientes/pageable")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("page", String.valueOf(0))
                        .param("size", String.valueOf(20)))
                .andExpect(MockMvcResultMatchers.status().isOk());

        Mockito.verify(clienteService).listarPageable(Mockito.any());
    }


}