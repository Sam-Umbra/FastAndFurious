/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dev.umbra.FastAndFurious.DTO;

import dev.umbra.FastAndFurious.entities.Pedido;

/**
 *
 * @author Aluno
 */
public class StatusRequest {
    private Pedido.Status status;

    public Pedido.Status getStatus() {
        return status;
    }

    public void setStatus(Pedido.Status status) {
        this.status = status;
    }
    
}
