package com.mercado.lista.domain.exception;

public class ItemNaoEncontradoException extends RuntimeException {

    public ItemNaoEncontradoException(Long id) {
        super("Item de mercado não encontrado: id=" + id);
    }
}
