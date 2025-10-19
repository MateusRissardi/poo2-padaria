/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

import excecoes.nomeInvalido;
import excecoes.precoInvalido;
import java.io.Serializable;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

/**
 *
 * @author Dell
 */
@Entity
public class Produto implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String nome;
    private String tipo;
    private int quantidadeEstoque;
    private double preco;
    private int precoPonto;
    
    public Produto() {
    }
    
    public Produto(String nome, String tipo, int quantidadeEstoque, double preco) {
        this.nome = nome;
        this.tipo = tipo;
        this.quantidadeEstoque = quantidadeEstoque;
        this.preco = preco;
        this.precoPonto = calcPontos();
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public int getPrecoPonto(){
        return this.precoPonto;
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

    public void setQuantidadeEstoque(int quantidadeEstoque) {
        this.quantidadeEstoque = quantidadeEstoque;
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
    
    public int calcPontos(){
        if (this.preco <= 5){
        return 1;
    } else{
            return (int) this.getPreco() / 5;
        }
    }
    @Override
    public String toString() {
        return "Produto{" + "id=" + id + ", nome=" + nome + ", tipo=" + tipo + ", quantidadeEstoque=" + quantidadeEstoque + ", preco=" + preco + '}';
    }
    
    
}
