package entidades;/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


import java.io.Serializable;
import java.util.ArrayList;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import entidades.Produto; 
import excecoes.produtoInvalido; 
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import java.util.List;

/**
 *
 * @author mateu
 */
@Entity
public class Carrinho implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToMany
    @JoinTable(name = "carrinho-produto", joinColumns = @JoinColumn(name = "carrinho-id"), inverseJoinColumns = @JoinColumn(name = "produto-id"))
    private List<Produto> produtos;
    private double valorCarrinho;
    private int valorPonto;
    @ManyToOne
    @JoinColumn(name = "cliente-id")
    private Cliente cliente;
    
    public Carrinho(){
        produtos = new ArrayList();
        this.valorCarrinho = 0;
    }

    public Long getId() {
        return id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public double getValorCarrinho() {
        return valorCarrinho;
    }

    public void setValorCarrinho(double valor) {
        if(valor >= 0){
           valorCarrinho += valor; 
        }
    }

    public void setProdutos(Produto umProd){
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

    public List<Produto> getProdutos() {
        return produtos;
    }
    
    public void excluirProduto(Produto umProd){
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
    
    public int calcularPrecoPonto(){
        for (Produto prod : this.getProdutos()){
            valorPonto += prod.getPrecoPonto();
        }
        return valorPonto;
    } 
}
