/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.trabalhopadaria;

import Excecoes.cpfInvalido;
import Excecoes.nomeInvalido;
import Excecoes.telefoneInvalido;
import com.mycompany.trabalhopadaria.Entidades.Carrinho;
import com.mycompany.trabalhopadaria.Entidades.Cliente;
import com.mycompany.trabalhopadaria.Entidades.Produto;
import com.mycompany.trabalhopadaria.Entidades.Venda;

/**
 *
 * @author Dell
 */
public class TrabalhoPadaria {

    public static void main(String[] args) throws nomeInvalido, cpfInvalido, telefoneInvalido {
        Cliente cli1 = new Cliente();
        Venda ven1 = new Venda();
        
        cli1.setNome("Marcelo");
        cli1.setCpf("123.456.789-10");
        cli1.setTelefone("47 99999-9999");
        
        Produto prod1 = new Produto("Pão brioche", "Pães", 10, 1);
        Produto prod2 = new Produto("Sonho de creme", "Doces Pequenos", 1, 3);
        Produto prod3 = new Produto("Bolo de cenoura", "Bolos pequenos", 15, 15);
        
        Carrinho car1 = new Carrinho();
        
        car1.addCarinho(prod3);
        car1.addCarinho(prod2);
        car1.addCarinho(prod2);
        car1.addCarinho(prod1);
        
        ven1.setCarrinho(car1);
        ven1.setCliente(cli1);
        
        ven1.setDataCompra(2024, 1, 28);
        ven1.setFormaPagamento("Pix");
        ven1.setDescricao("Compras do mês");
        
        ven1.confirmarVenda();
        
    }
}
