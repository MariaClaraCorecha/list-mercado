package com.mercado.lista.presentation.resource;

import com.mercado.lista.domain.enums.Categoria;
import com.mercado.lista.domain.enums.StatusItem;
import com.mercado.lista.domain.enums.UnidadeMedida;
import com.mercado.lista.domain.model.ItemMercado;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/** Representação do item devolvida pela API. */
public record ItemMercadoResource(
        Long id,
        String nome,
        BigDecimal quantidade,
        UnidadeMedida unidade,
        Categoria categoria,
        StatusItem status,
        String observacao,
        LocalDateTime criadoEm,
        LocalDateTime atualizadoEm
) {

    public static ItemMercadoResource from(ItemMercado item) {
        return new ItemMercadoResource(
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
}
