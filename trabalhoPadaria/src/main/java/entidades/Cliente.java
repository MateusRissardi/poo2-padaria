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
public class Cliente implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    
    private Long id;
    private String nome;
    private String cpf;
    private String telefone;
    private int quantidadePontos;
    
    public Cliente(String nome, String cpf, String telefone) {
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
    }

    public Cliente() {
    }
    
    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) throws nomeInvalido {
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

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) throws cpfInvalido {
        try{
          if (cpf == null || cpf.isBlank()){
              throw new cpfInvalido("CPF inválido!");
          } else {
              this.cpf = cpf;
          }
        } catch(cpfInvalido e){
            System.out.println("Erro: " + e.getMessage());
        }
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) throws telefoneInvalido{
        try{
            if(telefone == null || telefone.isBlank()){
                throw new telefoneInvalido("Telefone inválido!");
            }else{
                this.telefone = telefone;
            }
        } catch (telefoneInvalido e) {
            System.out.println("Erro: " + e.getMessage());
        }
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
