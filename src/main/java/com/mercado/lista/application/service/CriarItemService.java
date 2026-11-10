package com.mercado.lista.application.service;

import com.mercado.lista.application.dto.ItemMercadoInput;
import com.mercado.lista.domain.model.ItemMercado;
import com.mercado.lista.domain.repository.ItemMercadoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CriarItemService {

    private final ItemMercadoRepository repository;

    public CriarItemService(ItemMercadoRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public ItemMercado executar(ItemMercadoInput input) {
        ItemMercado item = ItemMercado.novo(
                input.nome(),
                input.quantidade(),
                input.unidade(),
                input.categoria(),
                input.observacao()
        );
        return repository.salvar(item);
    }
}
