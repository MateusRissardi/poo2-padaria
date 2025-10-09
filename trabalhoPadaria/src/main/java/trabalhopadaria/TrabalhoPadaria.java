package trabalhopadaria;/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

import excecoes.cpfInvalido;
import excecoes.nomeInvalido;
import excecoes.telefoneInvalido;
import entidades.Carrinho;
import entidades.Cliente;
import entidades.Produto;
import entidades.Venda;

/**
 *
 * @author Dell
 */
public class TrabalhoPadaria {

    public static void main(String[] args) throws nomeInvalido, cpfInvalido, telefoneInvalido {
    Cliente cli1 = new Cliente("Marcelo", "123.456.789-10", "47 99999-9999");
    Cliente cli2 = new Cliente("João Vitor Boff", "027.378.150-29", "54 98409-9890");

    Produto prod1 = new Produto("Pão brioche", "Pães", 10, 1);
    Produto prod2 = new Produto("Sonho de creme", "Doces Pequenos", 1, 3);
    Produto prod3 = new Produto("Bolo de cenoura", "Bolos pequenos", 15, 15);

    ClienteDAO cliDao = new ClienteDAO();
    ProdutoDAO proDao = new ProdutoDAO();
    CarrinhoDAO carDao = new CarrinhoDAO();

    cliDao.salvar(cli1);
    cliDao.salvar(cli2);

    proDao.salvar(prod1);
    proDao.salvar(prod2);
    proDao.salvar(prod3);

    Carrinho car1 = new Carrinho();

    car1.setCliente(cli2); 
    car1.setProdutos(prod1);
    car1.setProdutos(prod2);
    car1.setProdutos(prod3);
    
    carDao.salvar(car1);
    
    System.out.println("Buscando cliente salvo: " + cliDao.encontrarPorID(cli2.getId()).getNome());
    System.out.println("Buscando produto salvo: " + proDao.encontrarPorID(prod1.getId()).getNome());
}
}
