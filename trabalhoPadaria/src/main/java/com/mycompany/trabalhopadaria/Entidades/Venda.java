/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.trabalhopadaria.Entidades;

import Excecoes.dataInvalida;
import Excecoes.vendaInvalida;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author mateu
 */
@Entity
public class Venda implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String formaPagamento;
    private String descricao;
    private Carrinho carrinho;
    private Cliente cliente;
    private Calendar calendario;
    private String dataCompra;

    public Venda() {
        this.calendario = Calendar.getInstance();
    }

    public Long getId() {
        return id;
    }

    public String getDataCompra() {
        return dataCompra;
    }

    public void setDataCompra(int ano, int mes, int dia) {
        try{
            if((ano > 2010 && ano < Calendar.YEAR) || (mes >= 0 && mes < 12)){
                if(mes != 3){
                    if( dia > 0 && dia < 31){
                        this.calendario.set(ano, mes, dia);
                        this.dataCompra = "" + this.calendario.getTime();
                    }
                    else{
                        throw new dataInvalida("Dia Inválido");
                    }
                }
                if( mes == 3 ){
                    if(dia > 0 && dia <= 28){
                        this.calendario.set(ano, mes, dia);
                        this.dataCompra = "" + this.calendario.getTime();
                    }
                    else{
                        throw new dataInvalida("Dia Inválido");
                    }   
                } 
            }
            else{
                throw new dataInvalida("Data Inválida");
            }
        }
        catch(dataInvalida ex){
            System.out.println("Erro: " + ex.getMessage());
        }
        
    }

    public String getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(String formaPagamento) {
        try{
            if(formaPagamento != "Pix" || formaPagamento != "Boleto" || formaPagamento != "Débito" || formaPagamento != "Crédito"){
                this.formaPagamento = formaPagamento;
            }
            else{
                this.formaPagamento = "";
                throw new vendaInvalida("Forma de Pagamento não reconhecida!");
            }
        }
        catch(vendaInvalida ex){
            System.out.println("Erro: " + ex.getMessage());
        }
            
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        if(descricao != null){
            this.descricao = descricao;
        }
        else{
            this.descricao = "";
        }
        
    }

    public Carrinho getCarrinho() {
        return carrinho;
    }
    
    public void setCarrinho (Carrinho carrinho){
        this.carrinho = carrinho;
    }

    public Cliente getCliente() {
        return cliente;
    }
    
    public void setCliente(Cliente cliente){
        this.cliente = cliente;
    }
    
    public void confirmarVenda(){
        try{
            if(this.cliente == null || this.carrinho == null){
                throw new vendaInvalida("Venda não realizada, verifique se o usuário e/ou os produtos estão cadastrados");
            }
            else if(this.dataCompra == null || this.formaPagamento == null){
                throw new vendaInvalida("Venda não realizada, verifique se a data de compra e/ou a forma de pagamento estão cadastrados");
            }
            else{
                System.out.println("Venda realizada \nId da compra : " + id + ", Cliente: " + cliente.getNome() + 
                        ", Descrição: " + descricao + ", Data de Compra: " + dataCompra + ", Forma de Pagamento: " + formaPagamento + ", Produtos: ");
                for(Produto umProd : carrinho.getProdutos()){
                    System.out.println("Nome: " + umProd.getNome() + ", Preço: R$" + umProd.getPreco());
                }
                System.out.println("Preço total: R$" + carrinho.getValorCarrinho());
            }
        }
        catch(vendaInvalida ex){
            System.out.println("Erro: " + ex.getMessage());
        }
    }
  
}
