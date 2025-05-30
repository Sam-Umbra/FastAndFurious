/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dev.umbra.FastAndFurious.DTO;

import java.util.List;

/**
 *
 * @author Sam_Umbra
 */
public class PedidoRequest {
    private String telefone;
    private List<Long> produtoId;

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public List<Long> getProdutoId() {
        return produtoId;
    }

    public void setProdutoId(List<Long> produtoId) {
        this.produtoId = produtoId;
    }
}
