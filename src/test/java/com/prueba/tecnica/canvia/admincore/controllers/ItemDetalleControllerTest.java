package com.prueba.tecnica.canvia.admincore.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.prueba.tecnica.canvia.admincore.Datos;
import com.prueba.tecnica.canvia.admincore.dtos.ClienteDTO;
import com.prueba.tecnica.canvia.admincore.models.Cliente;
import com.prueba.tecnica.canvia.admincore.models.ItemDetalle;
import com.prueba.tecnica.canvia.admincore.services.interfaces.IItemDetalleService;
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

@WebMvcTest(ItemDetalleController.class)
class ItemDetalleControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private IItemDetalleService itemDetalleService;

    ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper();
    }

    @Test
    void testListarOk() throws Exception {

        Mockito.when(itemDetalleService.listar()).thenReturn(Datos.getItemList());

        mvc.perform(MockMvcRequestBuilders.get("/items-detalles").contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].nombre").value("Macbook"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].precio").value(3500.99))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].nombre").value("Mando Xbox"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].precio").value(200.99));

        Mockito.verify(itemDetalleService).listar();
    }

    @Test
    void testListarPorIdOk() throws Exception {

        Mockito.when(itemDetalleService.obtenerPorId(1)).thenReturn(Datos.getItem());

        mvc.perform(MockMvcRequestBuilders.get("/items-detalles/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.nombre").value("Macbook"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.precio").value(3500.99));

        Mockito.verify(itemDetalleService).obtenerPorId(1);
    }

    @Test
    void testListarPorIdNotFound() throws Exception {

        Mockito.when(itemDetalleService.obtenerPorId(1)).thenReturn(null);

        mvc.perform(MockMvcRequestBuilders.get("/items-detalles/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNotFound());

        Mockito.verify(itemDetalleService).obtenerPorId(1);
    }

    @Test
    void testBuscarPorNombre() throws Exception {

        Mockito.when(itemDetalleService.buscarPorNombre("Macbook")).thenReturn(Datos.getItemList());

        mvc.perform(MockMvcRequestBuilders.get("/items-detalles/buscarPorNombre/Macbook").contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].nombre").value("Macbook"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].precio").value(3500.99));

        Mockito.verify(itemDetalleService).buscarPorNombre("Macbook");
    }

    @Test
    void testBuscarPorNombreNotFound() throws Exception {

        Mockito.when(itemDetalleService.buscarPorNombre("Macbook")).thenReturn(null);

        mvc.perform(MockMvcRequestBuilders.get("/items-detalles/buscarPorNombre/Macbook").contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNotFound());

        Mockito.verify(itemDetalleService).buscarPorNombre("Macbook");
    }

    @Test
    void testRegistrarOk() throws Exception {
        ItemDetalle item = new ItemDetalle();
        item.setNombre("Macbook");
        item.setTipo("laptop");
        item.setPrecio(3500.99);

        Mockito.when(itemDetalleService.registrar(Mockito.any())).then(invocation -> {
            ItemDetalle it = invocation.getArgument(0);
            it.setIdItem(3);
            return it;
        });

        mvc.perform(MockMvcRequestBuilders.post("/items-detalles").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(item)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.redirectedUrl("http://localhost/items-detalles/3"));

        Mockito.verify(itemDetalleService).registrar(Mockito.any());
    }

    @Test
    void testActualizarOk() throws Exception {
        ItemDetalle item = new ItemDetalle();
        item.setIdItem(1);
        item.setNombre("Macbook");
        item.setTipo("laptop");
        item.setPrecio(3500.99);

        Mockito.when(itemDetalleService.obtenerPorId(1)).thenReturn(Datos.getItem());
        Mockito.when(itemDetalleService.modificar(Mockito.any())).then(invocation -> {
            ItemDetalle c = invocation.getArgument(0);
            c.setIdItem(item.getIdItem());
            c.setNombre(item.getNombre());
            c.setTipo(item.getTipo());
            c.setPrecio(item.getPrecio());
            return c;
        });

        mvc.perform(MockMvcRequestBuilders.put("/items-detalles").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(item)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.idItem", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.nombre", Matchers.is("Macbook")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.precio", Matchers.is(3500.99)));


        Mockito.verify(itemDetalleService).obtenerPorId(1);
        Mockito.verify(itemDetalleService).modificar(Mockito.any());
    }

    @Test
    void testActualizarNotFound() throws Exception {
        ItemDetalle item = new ItemDetalle();
        item.setIdItem(1);
        item.setNombre("Macbook");
        item.setTipo("laptop");
        item.setPrecio(3500.99);

        Mockito.when(itemDetalleService.obtenerPorId(1)).thenReturn(null);

        mvc.perform(MockMvcRequestBuilders.put("/items-detalles").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(item)))
                .andExpect(MockMvcResultMatchers.status().isNotFound());


        Mockito.verify(itemDetalleService).obtenerPorId(1);
    }

    @Test
    void testEliminarOk() throws Exception {

        Mockito.when(itemDetalleService.obtenerPorId(1)).thenReturn(Datos.getItem());

        mvc.perform(MockMvcRequestBuilders.delete("/items-detalles/1").contentType(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNoContent());

        Mockito.verify(itemDetalleService).obtenerPorId(1);
    }

    @Test
    void testEliminarNotFound() throws Exception {

        Mockito.when(itemDetalleService.obtenerPorId(1)).thenReturn(null);

        mvc.perform(MockMvcRequestBuilders.delete("/items-detalles/1").contentType(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNotFound());

        Mockito.verify(itemDetalleService).obtenerPorId(1);
    }

    @Test
    void testListarPageable() throws Exception {

        Mockito.when(itemDetalleService.listarPageable(Mockito.any())).thenReturn(
                new PageImpl<ItemDetalle>(Datos.getItemList(), PageRequest.of(0, 20), 400L)
        );

        mvc.perform(MockMvcRequestBuilders.get("/items-detalles/pageable")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("page", String.valueOf(0))
                        .param("size", String.valueOf(20)))
                .andExpect(MockMvcResultMatchers.status().isOk());

        Mockito.verify(itemDetalleService).listarPageable(Mockito.any());
    }

}