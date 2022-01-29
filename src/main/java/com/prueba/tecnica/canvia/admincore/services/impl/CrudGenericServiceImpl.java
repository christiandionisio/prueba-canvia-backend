package com.prueba.tecnica.canvia.admincore.services.impl;

import com.prueba.tecnica.canvia.admincore.repo.IGenericRepo;
import com.prueba.tecnica.canvia.admincore.services.interfaces.ICrudGenericService;

import java.util.List;

public abstract class CrudGenericServiceImpl<T, ID> implements ICrudGenericService<T, ID> {

    protected abstract IGenericRepo<T, ID> getRepo();

    @Override
    public List<T> listar() {
        return getRepo().findAll();
    }

    @Override
    public T obtenerPorId(ID id) {
        return getRepo().findById(id).orElse(null);
    }

    @Override
    public T registrar(T t) {
        return getRepo().save(t);
    }

    @Override
    public T modificar(T t) {
        return getRepo().save(t);
    }

    @Override
    public void eliminar(ID id) {
        getRepo().deleteById(id);
    }
}
