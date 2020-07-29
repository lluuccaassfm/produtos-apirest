package com.produtos.apirest.service.impl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.produtos.apirest.models.Produto;
import com.produtos.apirest.repository.ProdutoRepository;
import com.produtos.apirest.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class ProdutoServiceImpl implements ProdutoService {

    private final Logger log = LoggerFactory.getLogger(ProdutoServiceImpl.class);

    @Autowired
    ProdutoRepository produtoRepository;

    @Override
    public Produto save(Produto produto){
        log.debug("Request to save Produto: {}", produto);
        return produtoRepository.save(produto);
    }

    @Override
    public List<Produto> findAll(){
        log.debug("Request to get all produtos");
        List<Produto> produtos = produtoRepository.findAll();
        produtos.sort(Comparator.comparing(Produto::getId));
        return produtos;
    }

    @Override
    public Optional<Produto> findOne(Long id){
        log.debug("Request to get Produto: {}", id);
        return produtoRepository.findById(id);
    }

    @Override
    public void delete(Produto produto){
        log.debug("Request to delete produto: {}", produto);
        produtoRepository.delete(produto);
    }
}
