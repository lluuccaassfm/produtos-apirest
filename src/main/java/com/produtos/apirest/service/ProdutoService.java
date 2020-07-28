package com.produtos.apirest.service;

import com.produtos.apirest.models.Produto;

import java.util.List;
import java.util.Optional;

public interface ProdutoService {

    Produto save(Produto produto);

    List<Produto> findAll();

    Optional<Produto> findOne(Long id);

    void delete(Long id);

}
