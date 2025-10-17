/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

import jakarta.persistence.DiscriminatorValue;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author 04404465033
 */
@Entity
@DiscriminatorValue("FUNCIONARIO")
public class Funcionario extends Usuario implements Serializable {

    private static final long serialVersionUID = 1L;

    public Funcionario() {
    }

    public Funcionario(String nome, String cpf, String telefone, String senha) {
        super(nome, cpf, telefone, senha);
    }
    
    @Override
    public String toString() {
        return "entidades.Funcionario[ id=" + id + " ]";
    }
    
}
