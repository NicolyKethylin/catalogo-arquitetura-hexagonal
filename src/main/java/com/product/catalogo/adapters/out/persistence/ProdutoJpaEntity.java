package com.product.catalogo.adapters.out.persistence;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.math.BigDecimal;

@Entity
@Table(name = "TB_PRODUTO")
public class ProdutoJpaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProduto;
    private String nome;
    private BigDecimal valor;

    protected ProdutoJpaEntity() {
    }

    public ProdutoJpaEntity(Long idProduto, String nome, BigDecimal valor) {
        this.idProduto = idProduto;
        this.nome = nome;
        this.valor = valor;
    }

    public Long getIdProduto() {
        return idProduto;
    }

    public String getNome() {
        return nome;
    }

    public BigDecimal getValor() {
        return valor;
    }
}
