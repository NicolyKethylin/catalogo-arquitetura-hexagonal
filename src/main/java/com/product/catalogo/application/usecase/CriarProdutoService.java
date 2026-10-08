package com.product.catalogo.application.usecase;

import com.product.catalogo.application.ports.in.CriarProdutoUseCase;
import com.product.catalogo.application.ports.out.ProdutoPersistencePort;
import com.product.catalogo.domain.model.Produto;

import java.math.BigDecimal;

public class CriarProdutoService implements CriarProdutoUseCase {
    private final ProdutoPersistencePort produtoPersistencePort;

    public CriarProdutoService(ProdutoPersistencePort produtoPersistencePort) {
        this.produtoPersistencePort = produtoPersistencePort;
    }

    @Override
    public Produto criar(String nome, BigDecimal valor) {
        return produtoPersistencePort.salvar(new Produto(null, nome, valor));
    }
}
