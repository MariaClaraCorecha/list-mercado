package com.mercado.lista.domain.repository;

import com.mercado.lista.domain.enums.Categoria;
import com.mercado.lista.domain.enums.StatusItem;
import com.mercado.lista.domain.model.ItemMercado;

import java.util.List;
import java.util.Optional;

/**
 * Porta do domínio. A camada de infraestrutura fornece a implementação,
 * mantendo o domínio independente de JPA/Spring.
 */
public interface ItemMercadoRepository {

    ItemMercado salvar(ItemMercado item);

    Optional<ItemMercado> buscarPorId(Long id);

    /** Filtros opcionais: passe null para ignorar. */
    List<ItemMercado> listar(StatusItem status, Categoria categoria);

    boolean existePorId(Long id);

    void deletarPorId(Long id);
}
