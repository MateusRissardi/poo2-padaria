/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import entidades.Produto;
import excecoes.produtoInvalido;
import java.util.List;
import trabalhopadaria.ProdutoDAO;

/**
 *
 * @author mateu
 */
public class ProdutoController {
    private ProdutoDAO produtoDAO;

    public ProdutoController() {
        this.produtoDAO = new ProdutoDAO();
    }

    // Salva um novo produto.
    public Produto salvarNovoProduto(String nome, String tipo, int quantidadeEstoque, double preco) throws produtoInvalido{
                
        if (nome == null || nome.trim().isEmpty() || tipo == null || tipo.trim().isEmpty() || quantidadeEstoque < 0 || preco < 0) {
            throw new produtoInvalido("Dados do produto inválidos.");
        }
        
        Produto novoProduto = new Produto(nome.trim(), tipo.trim(), quantidadeEstoque, preco);
        return produtoDAO.salvar(novoProduto);
    }

    // Atualiza um produto existente.
    public Produto atualizarProduto(Long id, String nome, String tipo, int quantidadeEstoque, double preco) throws Exception{
                
        Produto produtoExistente = produtoDAO.encontrarPorID(id);
        if (produtoExistente == null) {
            throw new produtoInvalido("Produto com ID " + id + " não encontrado para atualização.");
        }
        if (nome == null || nome.trim().isEmpty() || tipo == null || tipo.trim().isEmpty() || quantidadeEstoque < 0 || preco < 0) {
            throw new produtoInvalido("Dados do produto inválidos.");
        }

        // Atualiza os dados do objeto encontrado
        produtoExistente.setNome(nome.trim());
        produtoExistente.setTipo(tipo.trim());
        produtoExistente.setQuantidadeEstoque(quantidadeEstoque);
        produtoExistente.setPreco(preco);
        produtoExistente.calcPontos(); 

        return produtoDAO.update(produtoExistente); 
    }
    
    public void excluirProdutoPorId(Long Id){
        try{
            produtoDAO.excluir(Id);
        } catch(Exception e ){
        }
    }

    //Busca um produto pelo ID.
    public Produto buscarProdutoPorId(Long id) {
        return produtoDAO.encontrarPorID(id);
    }

    //Lista todos os produtos cadastrados.
    public List<Produto> listarTodosProdutos() {
        return produtoDAO.findAll();
    }
}
