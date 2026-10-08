package com.product.catalogo.application.usecase;

import com.product.catalogo.application.ports.out.ProdutoPersistencePort;
import com.product.catalogo.domain.model.Produto;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ProdutoUseCasesTest {
    @Test
    void criarProdutoPersisteProdutoSemIdEDevolveProdutoSalvo() {
        Produto esperado = new Produto(1L, "Teclado", new BigDecimal("120.00"));
        InMemoryProdutoPersistencePort persistencePort = new InMemoryProdutoPersistencePort(esperado);
        CriarProdutoService useCase = new CriarProdutoService(persistencePort);

        Produto resultado = useCase.criar("Teclado", new BigDecimal("120.00"));

        assertEquals(esperado, resultado);
        assertEquals(new Produto(null, "Teclado", new BigDecimal("120.00")), persistencePort.salvo);
    }

    @Test
    void consultarProdutosUsaPortaDePersistencia() {
        Produto produto = new Produto(1L, "Teclado", new BigDecimal("120.00"));
        ConsultarProdutosService useCase =
                new ConsultarProdutosService(new InMemoryProdutoPersistencePort(produto));

        assertEquals(List.of(produto), useCase.listarTodos());
        assertEquals(Optional.of(produto), useCase.buscarPorId(1L));
        assertTrue(useCase.buscarPorId(2L).isEmpty());
    }

    @Test
    void produtoRejeitaNomeEmBrancoEValorNaoPositivo() {
        assertThrows(IllegalArgumentException.class,
                () -> new Produto(null, " ", new BigDecimal("10.00")));
        assertThrows(IllegalArgumentException.class,
                () -> new Produto(null, "Teclado", BigDecimal.ZERO));
    }

    private static class InMemoryProdutoPersistencePort implements ProdutoPersistencePort {
        private final Produto produto;
        private Produto salvo;

        private InMemoryProdutoPersistencePort(Produto produto) {
            this.produto = produto;
        }

        @Override
        public Produto salvar(Produto produto) {
            this.salvo = produto;
            return this.produto;
        }

        @Override
        public List<Produto> listarTodos() {
            return List.of(produto);
        }

        @Override
        public Optional<Produto> buscarPorId(Long id) {
            return produto.id().equals(id) ? Optional.of(produto) : Optional.empty();
        }
    }
}
