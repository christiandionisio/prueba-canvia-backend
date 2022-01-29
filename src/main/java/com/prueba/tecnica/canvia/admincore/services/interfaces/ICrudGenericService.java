package com.prueba.tecnica.canvia.admincore.services.interfaces;

import java.util.List;

public interface ICrudGenericService<T, ID> {

    List<T> listar();

    T obtenerPorId(ID id);

    T registrar(T t);

    T modificar(T t);

    void eliminar(ID id);

}
