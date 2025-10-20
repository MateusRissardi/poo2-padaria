/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

import jakarta.persistence.DiscriminatorValue;
import java.io.Serializable;
import jakarta.persistence.Entity;

/**
 *
 * @author Dell
 */
@Entity
@DiscriminatorValue("CLIENTE")
public class Cliente extends Usuario implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private int quantidadePontos;

    public Cliente(String nome, String cpf, String telefone, String senha) {
        super(nome, cpf, telefone, senha);
        this.quantidadePontos = 0;
    }

    public Cliente() {
        
    }
    
    public Long getId() {
        return super.getId();
    }

    public int  getQuantidadePontos() {
        return quantidadePontos;
    }
    
    public void setQuantidadePontos(int pontos){
        this.quantidadePontos += pontos;
    }

    @Override
    public String toString() {
        return "Cliente{" + "id=" + super.getId() + ", nome=" + super.getNome() + ", cpf=" + super.getCpf() + ", telefone=" + super.getTelefone() + ", quantidadePontos=" + quantidadePontos + '}';
    }
}
