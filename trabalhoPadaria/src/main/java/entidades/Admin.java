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
@DiscriminatorValue("ADMIN")
public class Admin extends Usuario implements Serializable {

    private static final long serialVersionUID = 1L;

    public Admin(String nome, String cpf, String telefone, String senha) {
        super(nome, cpf, telefone, senha);
    }

    public Admin() {
    }

    @Override
    public String toString() {
        return "entidades.Admin[ id=" + id + " ]";
    }
    
}
