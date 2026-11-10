package com.mercado.lista.application.service;

import com.mercado.lista.domain.enums.Categoria;
import com.mercado.lista.domain.enums.StatusItem;
import com.mercado.lista.domain.model.ItemMercado;
import com.mercado.lista.domain.repository.ItemMercadoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ListarItensService {

    private final ItemMercadoRepository repository;

    public ListarItensService(ItemMercadoRepository repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    public List<ItemMercado> executar(StatusItem status, Categoria categoria) {
        return repository.listar(status, categoria);
    }
}
