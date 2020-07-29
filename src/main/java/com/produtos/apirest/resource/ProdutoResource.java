package com.produtos.apirest.resource;

import com.produtos.apirest.models.Produto;
import com.produtos.apirest.service.ProdutoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api")
@Api(value = "API REST Produtos")
@CrossOrigin(origins = "*")
public class ProdutoResource {

    final ProdutoService produtoService;

    public ProdutoResource(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @GetMapping("/produtos")
    @ApiOperation(value = "Retorna uma lista de produtos")
    public ResponseEntity<List<Produto>> listaProdutos(){
        return new ResponseEntity<>(this.produtoService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/produtos/{id}")
    @ApiOperation(value = "Retorna um único produto")
    public ResponseEntity<Produto> listaProdutounico(@PathVariable(value = "id") Long id){
        Optional<Produto> produto = produtoService.findOne(id);
        return produto
                .map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/produtos")
    @ApiOperation(value = "Salva um produto")
    public ResponseEntity<Produto> salvaProduto(@RequestBody Produto produto){
        return new ResponseEntity<>(produtoService.save(produto), HttpStatus.OK);
    }

    @DeleteMapping("/produtos/{id}")
    @ApiOperation(value = "Deleta um único produto")
    public ResponseEntity<Object> deletaProduto(@PathVariable(value = "id") Long id){
        Optional<Produto> produto = produtoService.findOne(id);
        if(produto.isPresent()){
            produtoService.delete(produto.get());
            return new ResponseEntity<>(HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/produtos")
    @ApiOperation(value = "Atualiza um produto")
    public ResponseEntity<Produto> atualizaProduto(@RequestBody Produto produto) {
        return new ResponseEntity<>(produtoService.save(produto), HttpStatus.OK);
    }
}
