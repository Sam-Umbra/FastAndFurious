/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Service.java to edit this template
 */
package dev.umbra.FastAndFurious.service;

import dev.umbra.FastAndFurious.repositories.ProdutoRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import dev.umbra.FastAndFurious.entities.Produto;
import java.util.List;

/**
 *
 * @author Sam_Umbra
 */
@Service
public class ProdutoService {

    @Autowired
    ProdutoRepository produtoRepo;

    public boolean existsById(Long id) {
        return produtoRepo.existsById(id);
    }

    public Optional<Produto> findById(Long id) {
        return produtoRepo.findById(id);
    }

    public List<Produto> listarProdutos() {
        return (List<Produto>) produtoRepo.findAll();
    }

    public List<Produto> listByCategoria(String categoria) {
        return produtoRepo.findByCategoria(categoria);
    }

    public Produto salvarProduto(Produto produto) {
        return produtoRepo.save(produto);
    }

    public void deletarProduto(Long id) {
        produtoRepo.deleteById(id);
    }

}