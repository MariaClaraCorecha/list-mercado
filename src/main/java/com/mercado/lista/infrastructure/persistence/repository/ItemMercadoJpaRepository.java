package com.mercado.lista.infrastructure.persistence.repository;

import com.mercado.lista.domain.enums.Categoria;
import com.mercado.lista.domain.enums.StatusItem;
import com.mercado.lista.infrastructure.persistence.entity.ItemMercadoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ItemMercadoJpaRepository extends JpaRepository<ItemMercadoEntity, Long> {

    @Query("""
            SELECT i FROM ItemMercadoEntity i
            WHERE (:status IS NULL OR i.status = :status)
              AND (:categoria IS NULL OR i.categoria = :categoria)
            ORDER BY i.status ASC, i.categoria ASC, i.nome ASC
            """)
    List<ItemMercadoEntity> filtrar(@Param("status") StatusItem status,
                                    @Param("categoria") Categoria categoria);
}
