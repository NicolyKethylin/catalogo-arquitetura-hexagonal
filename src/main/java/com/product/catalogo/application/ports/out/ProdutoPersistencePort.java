package com.product.catalogo.application.ports.out;

import com.product.catalogo.domain.model.Produto;

import java.util.List;
import java.util.Optional;

public interface ProdutoPersistencePort {
    Produto salvar(Produto produto);

    List<Produto> listarTodos();

    Optional<Produto> buscarPorId(Long id);
}
