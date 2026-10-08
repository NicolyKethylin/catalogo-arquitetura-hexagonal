package com.product.catalogo.application.ports.in;

import com.product.catalogo.domain.model.Produto;

import java.math.BigDecimal;

public interface CriarProdutoUseCase {
    Produto criar(String nome, BigDecimal valor);
}
