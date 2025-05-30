/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Repository.java to edit this template
 */
package dev.umbra.FastAndFurious.repositories;

import dev.umbra.FastAndFurious.entities.Pedido;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author Sam_Umbra
 */
public interface PedidoRepository extends CrudRepository<Pedido, Long> {
    List<Pedido> findByStatus(Pedido.Status status);
}
