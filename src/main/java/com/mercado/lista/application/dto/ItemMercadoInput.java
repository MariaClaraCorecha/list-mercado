package com.mercado.lista.application.dto;

import com.mercado.lista.domain.enums.Categoria;
import com.mercado.lista.domain.enums.UnidadeMedida;

import java.math.BigDecimal;

/**
 * Dados de entrada dos casos de uso de criação/atualização.
 * Desacopla a camada de aplicação dos Requests HTTP.
 */
public record ItemMercadoInput(
        String nome,
        BigDecimal quantidade,
        UnidadeMedida unidade,
        Categoria categoria,
        String observacao
) {
}
