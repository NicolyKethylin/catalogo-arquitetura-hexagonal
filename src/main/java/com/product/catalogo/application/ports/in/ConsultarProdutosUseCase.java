package com.product.catalogo.application.ports.in;

import com.product.catalogo.domain.model.Produto;

import java.util.List;
import java.util.Optional;

public interface ConsultarProdutosUseCase {
    List<Produto> listarTodos();

    Optional<Produto> buscarPorId(Long id);
}
