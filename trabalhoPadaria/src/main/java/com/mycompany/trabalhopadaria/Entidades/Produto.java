/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.trabalhopadaria.Entidades;

import Execoes.nomeInvalido;
import Execoes.precoInvalido;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author Dell
 */
@Entity
public class Produto implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    
    
    private Long id;

    public Produto(String nome, String tipo, int quantidadeEstoque, double preco) {
        this.nome = nome;
        this.tipo = tipo;
        this.quantidadeEstoque = quantidadeEstoque;
        this.preco = preco;
    }
    
    private String nome;
    private String tipo;
    private int quantidadeEstoque;
    private double preco;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        try {
            if (nome == null || nome.isBlank()){
                throw new nomeInvalido("Nome inválido!");
            } else {
                this.nome = nome;
        } 
        } catch (nomeInvalido e){
            System.out.println("Erro: " + e.getMessage());
        }
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getQuantidadeEstoque() {
        return quantidadeEstoque;
    }
    
    public double getPreco(){
        return preco;
    }
    
    public void setPreco(double preco) throws precoInvalido{
        try{
            if (preco < 0.00){
                throw new precoInvalido("Preço inválido!");
            } else {
                this.preco = preco;
            }
        } catch (precoInvalido e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
    
    
}
