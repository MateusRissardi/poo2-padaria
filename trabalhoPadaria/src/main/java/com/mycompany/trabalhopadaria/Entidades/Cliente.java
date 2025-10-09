/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.trabalhopadaria.Entidades;

import Execoes.cpfInvalido;
import Execoes.nomeInvalido;
import Execoes.telefoneInvalido;
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
public class Cliente implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    
    private Long id;
    private String nome;
    private String cpf;
    private String telefone;
    private String quantidadePontos;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
            }
        } catch (telefoneInvalido e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    public String getQuantidadePontos() {
        return quantidadePontos;
    }
    
}
