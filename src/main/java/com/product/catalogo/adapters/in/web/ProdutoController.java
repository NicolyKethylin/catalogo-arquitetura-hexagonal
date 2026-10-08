package com.product.catalogo.adapters.in.web;

import com.product.catalogo.application.ports.in.ConsultarProdutosUseCase;
import com.product.catalogo.application.ports.in.CriarProdutoUseCase;
import com.product.catalogo.domain.model.Produto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProdutoController {

    private final CriarProdutoUseCase criarProdutoUseCase;
    private final ConsultarProdutosUseCase consultarProdutosUseCase;

    public ProdutoController(
            CriarProdutoUseCase criarProdutoUseCase,
            ConsultarProdutosUseCase consultarProdutosUseCase) {
        this.criarProdutoUseCase = criarProdutoUseCase;
        this.consultarProdutosUseCase = consultarProdutosUseCase;
    }

    @PostMapping("/produtos")
    public ResponseEntity<ProdutoResponseDto> saveProduto(@RequestBody ProdutoRecordDto produtoDto){
        Produto produtoSalvo = criarProdutoUseCase.criar(produtoDto.nome(), produtoDto.valor());
        return new ResponseEntity<>(toResponse(produtoSalvo), HttpStatus.CREATED);
    }


    @GetMapping("/produtos")
    public ResponseEntity<List<ProdutoResponseDto>> getAllProdutos(){
        List<ProdutoResponseDto> produtosList = consultarProdutosUseCase.listarTodos()
                .stream()
                .map(ProdutoController::toResponse)
                .toList();

        if(produtosList.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else {
            return new ResponseEntity<>(produtosList,HttpStatus.OK);
        }
    }

    @GetMapping("/produtos/{id}")
    public ResponseEntity<ProdutoResponseDto> getOneProduto(@PathVariable(value = "id")long id){
        return consultarProdutosUseCase.buscarPorId(id)
                .map(ProdutoController::toResponse)
                .map(produto -> new ResponseEntity<>(produto, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    private static ProdutoResponseDto toResponse(Produto produto) {
        return new ProdutoResponseDto(produto.id(), produto.nome(), produto.valor());
    }
}
