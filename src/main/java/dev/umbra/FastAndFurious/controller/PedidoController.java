/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/RestController.java to edit this template
 */
package dev.umbra.FastAndFurious.controller;

import dev.umbra.FastAndFurious.DTO.PedidoRequest;
import dev.umbra.FastAndFurious.DTO.StatusRequest;
import dev.umbra.FastAndFurious.entities.Pedido;
import dev.umbra.FastAndFurious.entities.Produto;
import dev.umbra.FastAndFurious.service.PedidoService;
import dev.umbra.FastAndFurious.service.ProdutoService;
import jakarta.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * @author Sam_Umbra
 */
@RestController
@RequestMapping("/pedido")
public class PedidoController {
    
    @Autowired
    PedidoService pedidoService;
    
    @Autowired
    ProdutoService produtoService;
    
    @GetMapping("{id}")
    public ResponseEntity<Pedido> findById(@PathVariable Long id) {
        if (pedidoService.existsById(id)) {
            return ResponseEntity.ok(pedidoService.findById(id).get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @GetMapping
    public ResponseEntity<List<Pedido>> listarPedidos() {
        if (pedidoService.listarPedidos().isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(pedidoService.listarPedidos());
        }
    }
    
    @GetMapping("/status/{status}")
    public ResponseEntity<List<Pedido>> listarPorStatus
        (@PathVariable Pedido.Status status) {
        
        if (pedidoService.listarPorStatus(status).isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(pedidoService.listarPorStatus(status));
        }
    }
        
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Pedido adicionarPedido(@Valid @RequestBody PedidoRequest pedidoRequest) {
        
        Pedido pedido = new Pedido();
        
        pedido.setDataAbertura(LocalDateTime.now());
        pedido.setStatus(Pedido.Status.ABERTO);
        pedido.setPrazo(pedido.getDataAbertura().plusMinutes(14));
        pedido.setTelefone(pedidoRequest.getTelefone());
        
        List<Produto> produtos = pedidoRequest.getProdutoId().stream()
        .map(id -> produtoService.findById(id)
        .orElseThrow(() -> new RuntimeException("Erro ao encontrar Produto com id: " + id)))
        .toList();
        
        pedido.setProdutos(produtos);
        
        return pedidoService.salvarPedido(pedido);
    }
    
    @DeleteMapping("{id}")
    public ResponseEntity<Void> deletarPedido(@PathVariable Long id) {
        if (pedidoService.existsById(id)) {
            pedidoService.deletarPedido(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @PutMapping("{id}")
    public ResponseEntity<Pedido> updatePedido(@Valid @PathVariable Long id, 
            @RequestBody Pedido pedido) {
        
        if (pedidoService.existsById(id)) {
            pedido.setId(id);
            pedido = pedidoService.salvarPedido(pedido);
            return ResponseEntity.ok(pedido);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @PutMapping("/status/{id}")
    public ResponseEntity<Pedido> alterarStatus(@PathVariable Long id, 
                                                @RequestBody StatusRequest statusRequest) {
        
        if (pedidoService.existsById(id)) {
            Pedido pedido = pedidoService.findById(id).get();
            pedido = pedidoService.alterarStatus(id, statusRequest.getStatus());
            return ResponseEntity.ok(pedido);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
