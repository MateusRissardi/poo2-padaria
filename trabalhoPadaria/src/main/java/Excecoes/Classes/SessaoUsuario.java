/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Excecoes.Classes;

import entidades.Usuario;

/**
 *
 * @author Dell
 */
public class SessaoUsuario {
    private static Usuario usuario;
    
    public static Usuario getUsuario(){
        return usuario;
    }
    
    public static void setUsuario(Usuario u){
        usuario = u;
    }
}
