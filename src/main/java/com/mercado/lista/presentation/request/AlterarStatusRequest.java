package com.mercado.lista.presentation.request;

import com.mercado.lista.domain.enums.StatusItem;
import jakarta.validation.constraints.NotNull;

public record AlterarStatusRequest(

        @NotNull(message = "O status é obrigatório")
        StatusItem status
) {
}
