package com.mercado.lista.infrastructure.persistence.entity;

import com.mercado.lista.domain.enums.Categoria;
import com.mercado.lista.domain.enums.StatusItem;
import com.mercado.lista.domain.enums.UnidadeMedida;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "item_mercado")
public class ItemMercadoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nome;

    @Column(nullable = false, precision = 10, scale = 3)
    private BigDecimal quantidade;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private UnidadeMedida unidade;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private Categoria categoria;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private StatusItem status;

    @Column(length = 255)
    private String observacao;

    @Column(name = "criado_em", nullable = false)
    private LocalDateTime criadoEm;

    @Column(name = "atualizado_em", nullable = false)
    private LocalDateTime atualizadoEm;

    protected ItemMercadoEntity() {
        // exigido pelo JPA
    }

    public ItemMercadoEntity(Long id, String nome, BigDecimal quantidade, UnidadeMedida unidade,
                             Categoria categoria, StatusItem status, String observacao,
                             LocalDateTime criadoEm, LocalDateTime atualizadoEm) {
        this.id = id;
        this.nome = nome;
        this.quantidade = quantidade;
        this.unidade = unidade;
        this.categoria = categoria;
        this.status = status;
        this.observacao = observacao;
        this.criadoEm = criadoEm;
        this.atualizadoEm = atualizadoEm;
    }

    public Long getId() { return id; }
    public String getNome() { return nome; }
    public BigDecimal getQuantidade() { return quantidade; }
    public UnidadeMedida getUnidade() { return unidade; }
    public Categoria getCategoria() { return categoria; }
    public StatusItem getStatus() { return status; }
    public String getObservacao() { return observacao; }
    public LocalDateTime getCriadoEm() { return criadoEm; }
    public LocalDateTime getAtualizadoEm() { return atualizadoEm; }
}
