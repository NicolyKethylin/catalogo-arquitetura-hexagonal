package com.product.catalogo.configuration;

import com.product.catalogo.application.ports.in.ConsultarProdutosUseCase;
import com.product.catalogo.application.ports.in.CriarProdutoUseCase;
import com.product.catalogo.application.ports.out.ProdutoPersistencePort;
import com.product.catalogo.application.usecase.ConsultarProdutosService;
import com.product.catalogo.application.usecase.CriarProdutoService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseConfiguration {
    @Bean
    CriarProdutoUseCase criarProdutoUseCase(ProdutoPersistencePort persistencePort) {
        return new CriarProdutoService(persistencePort);
    }

    @Bean
    ConsultarProdutosUseCase consultarProdutosUseCase(ProdutoPersistencePort persistencePort) {
        return new ConsultarProdutosService(persistencePort);
    }
}
