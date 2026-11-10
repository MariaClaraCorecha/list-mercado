package com.mercado.lista.application.service;

import com.mercado.lista.domain.exception.ItemNaoEncontradoException;
import com.mercado.lista.domain.repository.ItemMercadoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DeletarItemService {

    private final ItemMercadoRepository repository;

    public DeletarItemService(ItemMercadoRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public void executar(Long id) {
        if (!repository.existePorId(id)) {
            throw new ItemNaoEncontradoException(id);
        }
        repository.deletarPorId(id);
    }
}
