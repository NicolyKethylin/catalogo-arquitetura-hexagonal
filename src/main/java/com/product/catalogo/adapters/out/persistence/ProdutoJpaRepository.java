package com.product.catalogo.adapters.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoJpaRepository extends JpaRepository<ProdutoJpaEntity, Long> {
}
