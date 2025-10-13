/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

import excecoes.cpfInvalido;
import excecoes.nomeInvalido;
import excecoes.telefoneInvalido;
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
public class Cliente extends Usuario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    
    private Long id;
    private String nome;
    private String cpf;
    private String telefone;
    private int quantidadePontos;
    boolean funcionario = false;
    
    public Cliente(String nome, String cpf, String telefone) {
        super(nome, cpf, telefone);
    }

    
    public Long getId() {
        return id;
    }

    public int  getQuantidadePontos() {
        return quantidadePontos;
    }
    
    public void setQuantidadePontos(int pontos){
        this.quantidadePontos += pontos;
    }

    @Override
    public String toString() {
        return "Cliente{" + "id=" + id + ", nome=" + nome + ", cpf=" + cpf + ", telefone=" + telefone + ", quantidadePontos=" + quantidadePontos + '}';
    }
    
    
}
