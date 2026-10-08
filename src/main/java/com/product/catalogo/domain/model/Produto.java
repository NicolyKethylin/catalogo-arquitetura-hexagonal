package com.product.catalogo.domain.model;

import java.math.BigDecimal;

public record Produto(Long id, String nome, BigDecimal valor) {
    public Produto {
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("Nome do produto é obrigatório.");
        }
        if (valor == null || valor.signum() <= 0) {
            throw new IllegalArgumentException("O valor do produto deve ser maior que zero.");
        }
    }
}
