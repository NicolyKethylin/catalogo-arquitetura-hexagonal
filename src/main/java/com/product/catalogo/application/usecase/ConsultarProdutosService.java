package com.product.catalogo.application.usecase;

import com.product.catalogo.application.ports.in.ConsultarProdutosUseCase;
import com.product.catalogo.application.ports.out.ProdutoPersistencePort;
import com.product.catalogo.domain.model.Produto;

import java.util.List;
import java.util.Optional;

public class ConsultarProdutosService implements ConsultarProdutosUseCase {
    private final ProdutoPersistencePort produtoPersistencePort;

    public ConsultarProdutosService(ProdutoPersistencePort produtoPersistencePort) {
        this.produtoPersistencePort = produtoPersistencePort;
    }

    @Override
    public List<Produto> listarTodos() {
        return produtoPersistencePort.listarTodos();
    }

    @Override
    public Optional<Produto> buscarPorId(Long id) {
        return produtoPersistencePort.buscarPorId(id);
    }
}
