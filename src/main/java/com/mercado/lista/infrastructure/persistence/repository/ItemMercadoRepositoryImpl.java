package com.mercado.lista.infrastructure.persistence.repository;

import com.mercado.lista.domain.enums.Categoria;
import com.mercado.lista.domain.enums.StatusItem;
import com.mercado.lista.domain.model.ItemMercado;
import com.mercado.lista.domain.repository.ItemMercadoRepository;
import com.mercado.lista.infrastructure.persistence.mapper.ItemMercadoEntityMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/** Adaptador: implementa a porta do domínio usando Spring Data JPA. */
@Repository
public class ItemMercadoRepositoryImpl implements ItemMercadoRepository {

    private final ItemMercadoJpaRepository jpaRepository;

    public ItemMercadoRepositoryImpl(ItemMercadoJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public ItemMercado salvar(ItemMercado item) {
        var salvo = jpaRepository.save(ItemMercadoEntityMapper.toEntity(item));
        return ItemMercadoEntityMapper.toDomain(salvo);
    }

    @Override
    public Optional<ItemMercado> buscarPorId(Long id) {
        return jpaRepository.findById(id).map(ItemMercadoEntityMapper::toDomain);
    }

    @Override
    public List<ItemMercado> listar(StatusItem status, Categoria categoria) {
        return jpaRepository.filtrar(status, categoria).stream()
                .map(ItemMercadoEntityMapper::toDomain)
                .toList();
    }

    @Override
    public boolean existePorId(Long id) {
        return jpaRepository.existsById(id);
    }

    @Override
    public void deletarPorId(Long id) {
        jpaRepository.deleteById(id);
    }
}
