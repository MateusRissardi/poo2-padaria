package views.TableModels;

import entidades.Produto; 
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class ProdutoTableModel extends AbstractTableModel {

    private List<Produto> produtos;
    private final String[] colunas = {"ID", "Nome", "Categoria", "Preço", "Preço (Pontos)", "Estoque"};

    public ProdutoTableModel(List<Produto> produtos) {
        this.produtos = produtos;
    }

    @Override
    public int getRowCount() {
        return produtos.size();
    }
    
    public void setProdutos(List<Produto> produtos) { 
        this.produtos = produtos;
    }

    @Override
    public int getColumnCount() {
        return colunas.length;
    }

    @Override
    public String getColumnName(int columnIndex) {
        return colunas[columnIndex];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Produto produto = produtos.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return produto.getId();
            case 1:
                return produto.getNome();
            case 2:
                return produto.getTipo();
            case 3:
                return produto.getPreco();
            case 4:
                return produto.calcPontos();
            case 5:
                return produto.getQuantidadeEstoque();
            default:
                return null;
        }
    }
    
    
}