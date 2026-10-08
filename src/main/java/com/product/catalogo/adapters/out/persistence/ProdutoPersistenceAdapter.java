package com.product.catalogo.adapters.out.persistence;

import com.product.catalogo.application.ports.out.ProdutoPersistencePort;
import com.product.catalogo.domain.model.Produto;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ProdutoPersistenceAdapter implements ProdutoPersistencePort {
    private final ProdutoJpaRepository produtoJpaRepository;

    public ProdutoPersistenceAdapter(ProdutoJpaRepository produtoJpaRepository) {
        this.produtoJpaRepository = produtoJpaRepository;
    }

    @Override
    public Produto salvar(Produto produto) {
        ProdutoJpaEntity entity = new ProdutoJpaEntity(produto.id(), produto.nome(), produto.valor());
        return toDomain(produtoJpaRepository.save(entity));
    }

    @Override
    public List<Produto> listarTodos() {
        return produtoJpaRepository.findAll().stream()
                .map(ProdutoPersistenceAdapter::toDomain)
                .toList();
    }

    @Override
    public Optional<Produto> buscarPorId(Long id) {
        return produtoJpaRepository.findById(id).map(ProdutoPersistenceAdapter::toDomain);
    }

    private static Produto toDomain(ProdutoJpaEntity entity) {
        return new Produto(entity.getIdProduto(), entity.getNome(), entity.getValor());
    }
}
