package com.prueba.tecnica.canvia.admincore.services.impl;

import com.prueba.tecnica.canvia.admincore.models.ItemDetalle;
import com.prueba.tecnica.canvia.admincore.repo.IGenericRepo;
import com.prueba.tecnica.canvia.admincore.repo.IItemDetalleRepo;
import com.prueba.tecnica.canvia.admincore.services.interfaces.IItemDetalleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemDetalleServiceImpl extends CrudGenericServiceImpl<ItemDetalle, Integer> implements IItemDetalleService {

    @Autowired
    private IItemDetalleRepo repo;

    @Override
    protected IGenericRepo<ItemDetalle, Integer> getRepo() {
        return repo;
    }

    @Override
    public Page<ItemDetalle> listarPageable(Pageable pageable) {
        return repo.findAll(pageable);
    }

    @Override
    public List<ItemDetalle> buscarPorNombre(String nombre) {
        return repo.buscarPorNombre(nombre);
    }
}
