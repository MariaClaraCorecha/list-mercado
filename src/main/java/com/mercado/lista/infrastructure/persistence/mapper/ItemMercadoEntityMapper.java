package com.mercado.lista.infrastructure.persistence.mapper;

import com.mercado.lista.domain.model.ItemMercado;
import com.mercado.lista.infrastructure.persistence.entity.ItemMercadoEntity;

public final class ItemMercadoEntityMapper {

    private ItemMercadoEntityMapper() {
    }

    public static ItemMercadoEntity toEntity(ItemMercado item) {
        return new ItemMercadoEntity(
                item.getId(),
                item.getNome(),
                item.getQuantidade(),
                item.getUnidade(),
                item.getCategoria(),
                item.getStatus(),
                item.getObservacao(),
                item.getCriadoEm(),
                item.getAtualizadoEm()
        );
    }

    public static ItemMercado toDomain(ItemMercadoEntity entity) {
        return new ItemMercado(
                entity.getId(),
                entity.getNome(),
                entity.getQuantidade(),
                entity.getUnidade(),
                entity.getCategoria(),
                entity.getStatus(),
                entity.getObservacao(),
                entity.getCriadoEm(),
                entity.getAtualizadoEm()
        );
    }
}
