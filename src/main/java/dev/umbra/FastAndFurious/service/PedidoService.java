/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Service.java to edit this template
 */
package dev.umbra.FastAndFurious.service;

import dev.umbra.FastAndFurious.entities.Pedido;
import dev.umbra.FastAndFurious.repositories.PedidoRepository;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Sam_Umbra
 */
@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepo;

    public boolean existsById(Long id) {
        return pedidoRepo.existsById(id);
    }

    public List<Pedido> listarPedidos() {
        return (List<Pedido>) pedidoRepo.findAll();
    }

    public Optional<Pedido> findById(Long id) {
        return pedidoRepo.findById(id);
    }
    
    public Pedido salvarPedido(Pedido pedido) {
        return pedidoRepo.save(pedido);
    }
    
    public void deletarPedido(Long id) {
        pedidoRepo.deleteById(id);
    }
    
    public List<Pedido> listarPorStatus(Pedido.Status status) {
        return (List<Pedido>) pedidoRepo.findByStatus(status);
    }
    
    public Pedido alterarStatus(Pedido pedido, Pedido.Status status) {
        if (pedido.getStatus() == Pedido.Status.ENTREGUE) {
            throw new IllegalArgumentException
            ("Não é possível alterar o status de um pedido já entregue.");
        }
        
        pedido.setStatus(status);
        return pedidoRepo.save(pedido);
    }
}