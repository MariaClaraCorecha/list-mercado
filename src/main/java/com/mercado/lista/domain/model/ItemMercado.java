package com.mercado.lista.domain.model;

import com.mercado.lista.domain.enums.Categoria;
import com.mercado.lista.domain.enums.StatusItem;
import com.mercado.lista.domain.enums.UnidadeMedida;
import com.mercado.lista.domain.exception.RegraNegocioException;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Entidade de domínio pura (sem dependência de framework).
 * Concentra as regras de negócio do item da lista de mercado.
 */
public class ItemMercado {

    private final Long id;
    private String nome;
    private BigDecimal quantidade;
    private UnidadeMedida unidade;
    private Categoria categoria;
    private StatusItem status;
    private String observacao;
    private final LocalDateTime criadoEm;
    private LocalDateTime atualizadoEm;

    public ItemMercado(Long id, String nome, BigDecimal quantidade, UnidadeMedida unidade,
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

    /** Fábrica para um novo item: sempre começa como PENDENTE. */
    public static ItemMercado novo(String nome, BigDecimal quantidade, UnidadeMedida unidade,
                                   Categoria categoria, String observacao) {
        validar(nome, quantidade);
        LocalDateTime agora = LocalDateTime.now();
        return new ItemMercado(null, nome.trim(), quantidade, unidade, categoria,
                StatusItem.PENDENTE, observacao, agora, agora);
    }

    public void atualizar(String nome, BigDecimal quantidade, UnidadeMedida unidade,
                          Categoria categoria, String observacao) {
        validar(nome, quantidade);
        this.nome = nome.trim();
        this.quantidade = quantidade;
        this.unidade = unidade;
        this.categoria = categoria;
        this.observacao = observacao;
        this.atualizadoEm = LocalDateTime.now();
    }

    public void alterarStatus(StatusItem novoStatus) {
        if (novoStatus == null) {
            throw new RegraNegocioException("O status é obrigatório");
        }
        this.status = novoStatus;
        this.atualizadoEm = LocalDateTime.now();
    }

    private static void validar(String nome, BigDecimal quantidade) {
        if (nome == null || nome.isBlank()) {
            throw new RegraNegocioException("O nome do item é obrigatório");
        }
        if (quantidade == null || quantidade.signum() <= 0) {
            throw new RegraNegocioException("A quantidade deve ser maior que zero");
        }
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
