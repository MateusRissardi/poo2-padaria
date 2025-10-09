/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

import excecoes.dataInvalida;
import excecoes.vendaInvalida;
import excecoes.vendaPontoInvalida;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

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
    private String dataCompra;

    public Venda() {

    }

    public Long getId() {
        return id;
    }

    public String getDataCompra() {
        return dataCompra;
    }

    public void setDataCompra() {
        this.dataCompra = "" + LocalDate.now();

    }

    public String getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(String formaPagamento) {
        try{
            if(formaPagamento != "Pix" || formaPagamento != "Boleto" || formaPagamento != "Débito" 
                    || formaPagamento != "Crédito" || formaPagamento != "Ponto"){
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
    
    public void confirmarVenda(){
        try{
            if(this.carrinho == null || this.carrinho.getCliente() == null){
                throw new vendaInvalida("Venda não realizada, verifique se o usuário e/ou os produtos estão cadastrados");
            }
            else if(this.formaPagamento.isBlank()){
                throw new vendaInvalida("Venda não realizada, verifique a forma de pagamento está cadastrada");
            }
            
            else if (this.formaPagamento.equals("Ponto")){
                try{
                    if (this.carrinho.calcularPrecoPonto() > this.carrinho.getCliente().getQuantidadePontos()){
                        throw new vendaPontoInvalida("Quantidade de pontos insuficientes!");
                    } else {
                        System.out.println("Venda realizada \nId da compra : " + id + ", Cliente: " + this.carrinho.getCliente().getNome() + 
                        ", Descrição: " + descricao + ", Data de Compra: " + dataCompra + ", Forma de Pagamento: " + formaPagamento + ", Produtos: ");
                        for(Produto umProd : carrinho.getProdutos()){
                            if (umProd.getPreco() > 5){
                                this.carrinho.getCliente().setQuantidadePontos(umProd.calcPontos());
                            }
                            System.out.println("Nome: " + umProd.getNome() + ", Preço de pontos: " + umProd.getPrecoPonto());
                        }
                    }
                } catch (vendaPontoInvalida e){
                    System.out.println("Erro: " + e.getMessage());
                }
            }
            
            else {
                System.out.println("Venda realizada \nId da compra : " + id + ", Cliente: " + this.carrinho.getCliente().getNome() + 
                        ", Descrição: " + descricao + ", Data de Compra: " + dataCompra + ", Forma de Pagamento: " + formaPagamento + ", Produtos: ");
                for(Produto umProd : carrinho.getProdutos()){
                    this.carrinho.getCliente().setQuantidadePontos(umProd.calcPontos());
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
