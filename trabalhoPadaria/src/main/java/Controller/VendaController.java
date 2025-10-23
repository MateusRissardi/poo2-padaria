package Controller;

import entidades.Admin;
import entidades.Carrinho;
import entidades.Cliente;
import entidades.Funcionario;
import entidades.Produto;
import entidades.Usuario;
import entidades.Venda;
import excecoes.produtoInvalido;
import excecoes.vendaInvalida;
import excecoes.vendaPontoInvalida;
import java.time.LocalDate;
import java.util.List;
import trabalhopadaria.ProdutoDAO;
import trabalhopadaria.UsuarioDAO;
import trabalhopadaria.VendaDAO; // Importe seu DAO

public class VendaController {

    private VendaDAO vendaDAO;
    private ProdutoDAO produtoDAO;
    private UsuarioDAO usuarioDAO;
    private Usuario funcionario;
    
    private Carrinho carrinhoAtual; 

    public VendaController() {
        this.vendaDAO = new VendaDAO();
        this.produtoDAO = new ProdutoDAO();
        this.usuarioDAO = new UsuarioDAO();
        iniciarNovaVenda(); 
    }
    
    //Retorna o carrinho atual 
    public Carrinho getCarrinho(){
        return this.carrinhoAtual;
    }

    //Limpa o carrinho atual para iniciar uma nova venda.
    public void iniciarNovaVenda() {
        this.carrinhoAtual = new Carrinho();
    }

    // Adiciona um produto ao carrinho atual.
    public void adicionarProdutoAoCarrinho(Long produtoId, int quantidade) 
            throws produtoInvalido, IllegalArgumentException {
        if (quantidade <= 0) {
            throw new IllegalArgumentException("Quantidade deve ser maior que zero.");
        }
        Produto produto = produtoDAO.encontrarPorID(produtoId);
        if (produto == null) {
            throw new produtoInvalido("Produto com ID " + produtoId + " não encontrado.");
        }
        int i = 0;
        while(i<quantidade){
          carrinhoAtual.setProdutos(produto);
          i++;
        }
        
    }
    
     //Remove um produto do carrinho atual.
    public void removerProdutoDoCarrinho(Long produtoId) throws produtoInvalido{
        Produto produto = produtoDAO.encontrarPorID(produtoId);
         if (produto == null) {
            throw new produtoInvalido("Produto com ID " + produtoId + " não encontrado.");
        }
        carrinhoAtual.excluirProduto(produto);
    }

    
     // Retorna o valor total do carrinho atual.
    public double getValorTotalCarrinho() {
        return carrinhoAtual.getValorCarrinho();
    }

    // Retorna o valor total em pontos do carrinho atual.
    public int getValorPontosCarrinho() {
        return carrinhoAtual.calcularPrecoPonto();
    }
    
    // Retorna todas as vendas realizadas
    public List<Venda> getVendas(){
        return vendaDAO.findAll();
    }


    // Finaliza a venda, persistindo a Venda e o Carrinho associado.
    public Venda finalizarVenda(String nomeClienteSelecionado, String formaPagamento, String descricao, String nomeFuncionario) throws Exception {
        
        Usuario usuarioCliente = usuarioDAO.encontrarPorNome(nomeClienteSelecionado.trim());
        if (usuarioCliente == null || !(usuarioCliente instanceof Cliente)) {
             throw new vendaInvalida("Cliente '" + nomeClienteSelecionado + "' não encontrado ou não é um cliente válido.");
        }
        Cliente cliente = (Cliente) usuarioCliente;
        Usuario usuarioFuncionario = usuarioDAO.encontrarPorNome(nomeFuncionario);
        if (usuarioFuncionario == null || (!(usuarioFuncionario instanceof Funcionario) && !(usuarioFuncionario instanceof Admin))) {
             throw new vendaInvalida("Cliente '" + nomeClienteSelecionado + "' não encontrado ou não é um cliente válido.");
        }
        carrinhoAtual.setCliente(cliente);
        
        Venda novaVenda = new Venda();
        novaVenda.setFuncionario(funcionario);
        novaVenda.setCarrinho(this.carrinhoAtual); 
        novaVenda.setDataCompra(); // Define a data atual
        novaVenda.setFormaPagamento(formaPagamento);
        novaVenda.setDescricao(descricao != null ? descricao.trim() : "");
        
        if (carrinhoAtual.getProdutos().isEmpty()) {
            throw new vendaInvalida("O carrinho está vazio!");
        }
        if (nomeClienteSelecionado == null || nomeClienteSelecionado.trim().isEmpty()) {
             throw new vendaInvalida("Nenhum cliente selecionado!");
        }
        if (formaPagamento == null || formaPagamento.trim().isEmpty()) {
            throw new vendaInvalida("Forma de pagamento inválida!");
        }

        int pontosNecessarios = carrinhoAtual.calcularPrecoPonto();
        
        if ("Ponto".equalsIgnoreCase(formaPagamento)) {
            if (pontosNecessarios > cliente.getQuantidadePontos()) {
                throw new vendaPontoInvalida("Pontos insuficientes! Necessários: " + pontosNecessarios + ", Saldo: " + cliente.getQuantidadePontos());
            }
            cliente.setQuantidadePontos(pontosNecessarios * -1); 
        } else {
            cliente.setQuantidadePontos(pontosNecessarios); 
        }
        
        Venda vendaSalva = vendaDAO.salvar(novaVenda);

        iniciarNovaVenda(); 
        
        return vendaSalva;
    }
}