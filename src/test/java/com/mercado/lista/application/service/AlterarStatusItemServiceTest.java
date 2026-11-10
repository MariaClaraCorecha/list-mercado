package com.mercado.lista.application.service;

import com.mercado.lista.domain.enums.Categoria;
import com.mercado.lista.domain.enums.StatusItem;
import com.mercado.lista.domain.enums.UnidadeMedida;
import com.mercado.lista.domain.exception.ItemNaoEncontradoException;
import com.mercado.lista.domain.model.ItemMercado;
import com.mercado.lista.domain.repository.ItemMercadoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class AlterarStatusItemServiceTest {

    private ItemMercadoRepository repository;
    private AlterarStatusItemService service;

    @BeforeEach
    void setUp() {
        repository = mock(ItemMercadoRepository.class);
        service = new AlterarStatusItemService(repository, new BuscarItemPorIdService(repository));
        when(repository.salvar(any())).thenAnswer(inv -> inv.getArgument(0));
    }

    @Test
    void deveMarcarItemComoComprado() {
        ItemMercado item = ItemMercado.novo("Arroz", new BigDecimal("5"),
                UnidadeMedida.KG, Categoria.MERCEARIA, null);
        when(repository.buscarPorId(1L)).thenReturn(Optional.of(item));

        ItemMercado resultado = service.executar(1L, StatusItem.COMPRADO);

        assertThat(resultado.getStatus()).isEqualTo(StatusItem.COMPRADO);
    }

    @Test
    void deveLancarErroQuandoItemNaoExiste() {
        when(repository.buscarPorId(99L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> service.executar(99L, StatusItem.COMPRADO))
                .isInstanceOf(ItemNaoEncontradoException.class);
    }
}
