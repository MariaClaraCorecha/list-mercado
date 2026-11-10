package com.mercado.lista.presentation.resource;

import java.time.LocalDateTime;
import java.util.List;

public record ErroResource(
        int status,
        String mensagem,
        List<String> detalhes,
        LocalDateTime timestamp
) {

    public static ErroResource of(int status, String mensagem, List<String> detalhes) {
        return new ErroResource(status, mensagem, detalhes, LocalDateTime.now());
    }
}
