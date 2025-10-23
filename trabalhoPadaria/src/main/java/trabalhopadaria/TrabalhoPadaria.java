package trabalhopadaria;/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

import entidades.Admin;
import excecoes.cpfInvalido;
import excecoes.nomeInvalido;
import excecoes.telefoneInvalido;
import entidades.Produto;

/**
 *
 * @author Dell
 */
public class TrabalhoPadaria {

    public static void main(String[] args) throws nomeInvalido, cpfInvalido, telefoneInvalido {
    Admin adm1 = new Admin("Roberto", "027.375.150-29", "54 98409-9890", "oijsdi123");
    
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
    
    adm1.setId(cliDao.salvar(adm1).getId());
    
    prod1.setId(proDao.salvar(prod1).getId());
    prod2.setId(proDao.salvar(prod2).getId());
    prod3.setId(proDao.salvar(prod3).getId());
    
    }
}
