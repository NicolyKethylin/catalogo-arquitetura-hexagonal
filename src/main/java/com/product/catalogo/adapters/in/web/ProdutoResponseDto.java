package com.product.catalogo.adapters.in.web;

import java.math.BigDecimal;

public record ProdutoResponseDto(Long idProduto, String nome, BigDecimal valor) {
}
