package views.TableModels; // Ou o pacote onde seus outros TableModels estão

import entidades.Produto;
import entidades.Venda; // Importe sua entidade Venda
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class VendaTableModel extends AbstractTableModel {

    // 1. A lista de dados que vem do banco
    private List<Venda> vendas;
    
    // 2. Os nomes das colunas que você quer exibir na tabela
    private final String[] colunas = {"ID Venda", "Data", "Cliente", "Funcionário", "Valor Total", "Forma Pagto.", "Produtos"};

    /**
     * Construtor que recebe a lista de vendas.
     */
    public VendaTableModel(List<Venda> vendas) {
        this.vendas = vendas;
    }

    // --- Métodos Obrigatórios do AbstractTableModel ---

    @Override
    public int getRowCount() {
        // Quantas linhas? O tamanho da lista.
        return vendas.size();
    }

    @Override
    public int getColumnCount() {
        // Quantas colunas? O tamanho do array de nomes.
        return colunas.length;
    }

    @Override
    public String getColumnName(int columnIndex) {
        // Qual o nome da coluna X?
        return colunas[columnIndex];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        // O que exibir na linha X, coluna Y?
        
        // Pega a Venda da linha específica
        Venda venda = vendas.get(rowIndex);

        // Retorna o atributo baseado no índice da coluna
        switch (columnIndex) {
            case 0:
                return venda.getId();
            case 1:
                return venda.getDataCompra(); // Já retorna a String formatada
            case 2:
                // Tratamento de Nulos: Verifica se o carrinho e o cliente existem
                if (venda.getCarrinho() != null && venda.getCarrinho().getCliente() != null) {
                    return venda.getCarrinho().getCliente().getNome(); // Pega o nome do cliente de dentro do carrinho
                }
                return "Cliente N/A"; // Valor padrão se algo for nulo
            case 3:
                // Tratamento de Nulos: Verifica se o funcionário existe
                if (venda.getFuncionario() != null) {
                    return venda.getFuncionario().getNome(); // Pega o nome do funcionário
                }
                return "Funcionário N/A";
            case 4:
                // Tratamento de Nulos: Verifica se o carrinho existe
                if (venda.getCarrinho() != null) {
                    return venda.getCarrinho().getValorCarrinho(); // Pega o valor de dentro do carrinho
                }
                return 0.0; // Valor padrão
            case 5:
                return venda.getFormaPagamento();
            case 6:
                List<String> produtos = new ArrayList<String>();
                if(venda.getCarrinho().getProdutos() != null){
                   for(Produto umProd : venda.getCarrinho().getProdutos()){
                    produtos.add(umProd.getNome() + "\n");
                    } 
                }
                return produtos;
            default:
                // Se algo der errado
                return null;
        }
    }
    
    // --- Métodos Opcionais (mas úteis) ---
    
    /**
     * Define uma nova lista de dados para a tabela e notifica a View sobre a mudança.
     */
    public void setVendas(List<Venda> vendas) {
        this.vendas = vendas;
        // Avisa a JTable que os dados mudaram e ela precisa se redesenhar
        fireTableDataChanged();
    }
    
    /**
     * Retorna a Venda completa da linha selecionada.
     * Útil se você tiver um botão "Ver Detalhes".
     */
    public Venda getVendaAt(int rowIndex) {
        return vendas.get(rowIndex);
    }
}