package com.mercado.lista.presentation.handler;

import com.mercado.lista.domain.exception.ItemNaoEncontradoException;
import com.mercado.lista.domain.exception.RegraNegocioException;
import com.mercado.lista.presentation.resource.ErroResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ItemNaoEncontradoException.class)
    public ResponseEntity<ErroResource> naoEncontrado(ItemNaoEncontradoException ex) {
        return erro(HttpStatus.NOT_FOUND, ex.getMessage(), List.of());
    }

    @ExceptionHandler(RegraNegocioException.class)
    public ResponseEntity<ErroResource> regraNegocio(RegraNegocioException ex) {
        return erro(HttpStatus.UNPROCESSABLE_ENTITY, ex.getMessage(), List.of());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErroResource> validacao(MethodArgumentNotValidException ex) {
        List<String> detalhes = ex.getBindingResult().getFieldErrors().stream()
                .map(e -> e.getField() + ": " + e.getDefaultMessage())
                .toList();
        return erro(HttpStatus.BAD_REQUEST, "Dados inválidos", detalhes);
    }

    @ExceptionHandler({HttpMessageNotReadableException.class, MethodArgumentTypeMismatchException.class})
    public ResponseEntity<ErroResource> requisicaoInvalida(Exception ex) {
        return erro(HttpStatus.BAD_REQUEST,
                "Requisição inválida (verifique o JSON e os valores dos enums)", List.of());
    }

    private ResponseEntity<ErroResource> erro(HttpStatus status, String mensagem, List<String> detalhes) {
        return ResponseEntity.status(status).body(ErroResource.of(status.value(), mensagem, detalhes));
    }
}
