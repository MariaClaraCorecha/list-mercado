package com.mercado.lista.application.service;

import com.mercado.lista.domain.exception.ItemNaoEncontradoException;
import com.mercado.lista.domain.model.ItemMercado;
import com.mercado.lista.domain.repository.ItemMercadoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BuscarItemPorIdService {

    private final ItemMercadoRepository repository;

    public BuscarItemPorIdService(ItemMercadoRepository repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    public ItemMercado executar(Long id) {
        return repository.buscarPorId(id)
                .orElseThrow(() -> new ItemNaoEncontradoException(id));
    }
}
