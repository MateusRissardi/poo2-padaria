/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

import excecoes.cpfInvalido;
import excecoes.nomeInvalido;
import excecoes.telefoneInvalido;
import jakarta.persistence.DiscriminatorColumn;
import java.io.Serializable;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;

/**
 *
 * @author Dell
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "TIPO_USUARIO")
public abstract class Usuario implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    
    protected Long id;
    protected String nome;
    protected String cpf;
    protected String telefone;
    protected String senha;
    
    public Usuario(String nome, String cpf, String telefone, String senha){
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
        this.senha = senha;
    }

    public Usuario() {
    }
    
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
            }else{
                this.telefone = telefone;
            }
        } catch (telefoneInvalido e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    public String getSenha() {
        return senha;
    }
    
    
    public abstract int getQuantidadePontos();

    @Override
    public String toString() {
        return "entidades.Usuario[ id=" + id + " ]";
    }
    
}
