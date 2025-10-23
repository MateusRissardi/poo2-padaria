/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import entidades.Venda;
import entidades.Carrinho;
import entidades.Produto;
import entidades.Usuario;
import trabalhopadaria.VendaDAO;

import java.util.List;

public class VendaController {
    
    private VendaDAO vendaDAO;
    
    public VendaController() {
        this.vendaDAO = new VendaDAO();
    }

    /**
     * Registra uma nova venda no banco de dados.
     */
    public Venda registrarVenda(Usuario funcionario, Usuario cliente, Carrinho carrinho,
                                String formaPagamento, String descricao) throws Exception {

        if (carrinho == null || carrinho.getProdutos() == null || carrinho.getProdutos().isEmpty()) {
            throw new Exception("Carrinho vazio! Adicione produtos antes de registrar a venda.");
        }

        Venda venda = new Venda();
        venda.setFuncionario(funcionario);
        venda.setCliente(cliente);
        venda.setFormaPagamento(formaPagamento);
        venda.setDescricao(descricao);
        venda.setProdutos(carrinho.getProdutos());
        venda.setValorTotal(carrinho.getValorCarrinho());
        venda.setDataCompra();

        // Salvar no banco
        return vendaDAO.salvar(venda);
    }

    /**
     * Lista todas as vendas registradas.
     */
    public List<Venda> listarTodas() {
        return vendaDAO.findAll();
    }

    /**
     * Busca uma venda pelo ID.
     */
    public Venda buscarPorId(Long id) {
        return vendaDAO.encontrarPorID(id);
    }

    /**
     * Exclui uma venda pelo ID.
     */
    public void excluir(Long id) {
        vendaDAO.excluir(id);
    }
}
