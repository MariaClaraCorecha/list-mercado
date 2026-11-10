package com.mercado.lista.application.service;

import com.mercado.lista.domain.enums.StatusItem;
import com.mercado.lista.domain.model.ItemMercado;
import com.mercado.lista.domain.repository.ItemMercadoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/** Marca um item como COMPRADO ou volta para PENDENTE (o "check" do to-do). */
@Service
public class AlterarStatusItemService {

    private final ItemMercadoRepository repository;
    private final BuscarItemPorIdService buscarItemPorIdService;

    public AlterarStatusItemService(ItemMercadoRepository repository,
                                    BuscarItemPorIdService buscarItemPorIdService) {
        this.repository = repository;
        this.buscarItemPorIdService = buscarItemPorIdService;
    }

    @Transactional
    public ItemMercado executar(Long id, StatusItem status) {
        ItemMercado item = buscarItemPorIdService.executar(id);
        item.alterarStatus(status);
        return repository.salvar(item);
    }
}
