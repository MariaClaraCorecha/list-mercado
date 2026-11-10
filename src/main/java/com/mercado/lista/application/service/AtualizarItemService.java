package com.mercado.lista.application.service;

import com.mercado.lista.application.dto.ItemMercadoInput;
import com.mercado.lista.domain.model.ItemMercado;
import com.mercado.lista.domain.repository.ItemMercadoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AtualizarItemService {

    private final ItemMercadoRepository repository;
    private final BuscarItemPorIdService buscarItemPorIdService;

    public AtualizarItemService(ItemMercadoRepository repository,
                                BuscarItemPorIdService buscarItemPorIdService) {
        this.repository = repository;
        this.buscarItemPorIdService = buscarItemPorIdService;
    }

    @Transactional
    public ItemMercado executar(Long id, ItemMercadoInput input) {
        ItemMercado item = buscarItemPorIdService.executar(id);
        item.atualizar(
                input.nome(),
                input.quantidade(),
                input.unidade(),
                input.categoria(),
                input.observacao()
        );
        return repository.salvar(item);
    }
}
