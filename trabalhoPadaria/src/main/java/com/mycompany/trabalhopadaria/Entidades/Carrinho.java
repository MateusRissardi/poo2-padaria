/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.trabalhopadaria.Entidades;

import java.io.Serializable;
import java.util.ArrayList;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import com.mycompany.trabalhopadaria.Entidades.Produto; 
import Excecoes.produtoInvalido; 

/**
 *
 * @author mateu
 */
@Entity
public class Carrinho implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private static ArrayList<Produto> produtos;
    private static double valorCarrinho;
    
    public Carrinho(){
        produtos = new ArrayList<>();
        this.valorCarrinho = 0;
    }

    public Long getId() {
        return id;
    }

    public double getValorCarrinho() {
        return valorCarrinho;
    }

    public void setValorCarrinho(double valor) {
        if(valor >= 0){
           valorCarrinho += valor; 
        }
    }

    public void addCarinho(Produto umProd){
        try{
           if(umProd.getQuantidadeEstoque() <= 0){
            throw new produtoInvalido("Não é possível adicionar o item no carrinho, quantidade de produto excedida!");
            }
            else{
                produtos.add(umProd);
                umProd.setQuantidadeEstoque(umProd.getQuantidadeEstoque() - 1);
                setValorCarrinho(umProd.getPreco());   
            } 
        }
        catch(produtoInvalido ex){
            System.out.println("Erro: " + ex.getMessage());
        }
    }

    public ArrayList<Produto> getProdutos() {
        return produtos;
    }
    
    public void devolverProduto(Produto umProd){
        produtos.remove(umProd);
        umProd.setQuantidadeEstoque(umProd.getQuantidadeEstoque() + 1);
    }
    
    public void removeAllCarrinho(){
        produtos.clear();
    }
    public void removeOneProdutoFromCarrinho(Produto umProd){
        if(produtos.contains(umProd)){
            produtos.remove(umProd);
        }
        else{
            System.out.println("Produto não encontrado no Carrinho");
        }
    }
}
