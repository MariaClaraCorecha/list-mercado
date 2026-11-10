package com.mercado.lista.presentation.request;

import com.mercado.lista.application.dto.ItemMercadoInput;
import com.mercado.lista.domain.enums.Categoria;
import com.mercado.lista.domain.enums.UnidadeMedida;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

/** Corpo da requisição para criar (POST) ou atualizar (PUT) um item. */
public record ItemMercadoRequest(

        @NotBlank(message = "O nome é obrigatório")
        @Size(max = 100, message = "O nome deve ter no máximo 100 caracteres")
        String nome,

        @NotNull(message = "A quantidade é obrigatória")
        @DecimalMin(value = "0.001", message = "A quantidade deve ser maior que zero")
        BigDecimal quantidade,

        @NotNull(message = "A unidade é obrigatória")
        UnidadeMedida unidade,

        @NotNull(message = "A categoria é obrigatória")
        Categoria categoria,

        @Size(max = 255, message = "A observação deve ter no máximo 255 caracteres")
        String observacao
) {

    public ItemMercadoInput toInput() {
        return new ItemMercadoInput(nome, quantidade, unidade, categoria, observacao);
    }
}
