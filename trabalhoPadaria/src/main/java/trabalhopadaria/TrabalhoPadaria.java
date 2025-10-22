package trabalhopadaria;/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

import entidades.Admin;
import excecoes.cpfInvalido;
import excecoes.nomeInvalido;
import excecoes.telefoneInvalido;
import entidades.Carrinho;
import entidades.Cliente;
import entidades.Funcionario;
import entidades.Produto;
import entidades.Venda;

/**
 *
 * @author Dell
 */
public class TrabalhoPadaria {

    public static void main(String[] args) throws nomeInvalido, cpfInvalido, telefoneInvalido {
    Cliente cli1 = new Cliente("Marcelo", "123.456.789-10", "47 99999-9999", "marcekl123");
    Cliente cli2 = new Cliente("João Vitor Boff", "027.378.150-29", "54 98409-9890", "jsfd123");
    Admin adm1 = new Admin("Roberto", "027.375.150-29", "54 98409-9890", "oijsdi123");
    Funcionario func1 = new Funcionario("Carlos", "027.378.150-24", "47 99204-2889", "165");
    
    cli2.setQuantidadePontos(100);

    Produto prod1 = new Produto("Pão brioche", "Pães", 10, 1);
    Produto prod2 = new Produto("Sonho de creme", "Doces Pequenos", 1, 3);
    Produto prod3 = new Produto("Bolo de cenoura", "Bolos pequenos", 15, 15);
    Produto prod4 = new Produto ("Bolo de cenoura", "Bolos pequenos", 10, 20);

    UsuarioDAO cliDao = new UsuarioDAO();
    ProdutoDAO proDao = new ProdutoDAO();
    CarrinhoDAO carDao = new CarrinhoDAO();
    VendaDAO venDao = new VendaDAO();
    
    venDao.excluirTodos();
    carDao.excluirTodos();
    proDao.excluirTodos();
    cliDao.excluirTodos();
    
    cli1.setId(cliDao.salvar(cli1).getId());
    cli2.setId(cliDao.salvar(cli2).getId());
    adm1.setId(cliDao.salvar(adm1).getId());
    func1.setId(cliDao.salvar(func1).getId());
    cli2.setNome("Mateus Rissardi");
    cliDao.update(cli2);
    
    prod1.setId(proDao.salvar(prod1).getId());
    prod2.setId(proDao.salvar(prod2).getId());
    prod3.setId(proDao.salvar(prod3).getId());

    Carrinho car1 = new Carrinho();
    
    car1.setCliente(cli2); 
    car1.setProdutos(prod1);
    car1.setProdutos(prod2);
    car1.setProdutos(prod3);
    
    car1.excluirProduto(prod1);
    
    car1.setId(carDao.salvar(car1).getId());
    
    for(Produto umProd : car1.getProdutos()){
        System.out.println(umProd.getNome());
    }
    System.out.println("" + prod1.getQuantidadeEstoque());
    
    Venda ven1 = new Venda();
    
    ven1.setCarrinho(car1);
    ven1.setDataCompra();
    ven1.setFormaPagamento("Ponto");
    ven1.setFuncionario(func1);
    ven1.confirmarVenda();
    
    ven1.setId(venDao.salvar(ven1).getId());
    
    System.out.println(cli2.getQuantidadePontos());
    System.out.println("Buscando cliente salvo: " + cliDao.encontrarPorID(cli2.getId()).getNome());
    System.out.println("Buscando produto salvo: " + proDao.encontrarPorID(prod1.getId()).getNome());
    
    System.out.println(cli1.getId());
    System.out.println(cli2.getId());
    System.out.println(func1.getId());
    System.out.println(ven1.toString());
    }
}
