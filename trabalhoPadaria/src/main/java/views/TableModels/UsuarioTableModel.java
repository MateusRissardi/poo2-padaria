package views.TableModels;

import entidades.Admin;
import entidades.Cliente;
import entidades.Funcionario;
import entidades.Usuario;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class UsuarioTableModel extends AbstractTableModel {

    private List<Usuario> usuarios;
    private Object tela;
    private int quantidade;
    private final String[] colunas = {"ID", "Nome", "CPF", "Telefone", "Pontos", "Tipo"};

    public UsuarioTableModel(List<Usuario> usuarios, Object tela, int quantidade) {
        this.usuarios = usuarios;
        this.tela = tela;
        this.quantidade = quantidade;
    }

    @Override
    public int getRowCount() {
        return usuarios.size();
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
        Usuario usuario = usuarios.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return usuario.getId();
            case 1:
                return usuario.getNome();
            case 2:
                return usuario.getCpf();
            case 3:
                return usuario.getTelefone();
            case 4:
                if (usuario instanceof Funcionario || usuario instanceof Admin){
                    return "N/A";
                } else {
                    Cliente cliente = (Cliente) usuario;
                    return cliente.getQuantidadePontos();
                }
            case 5:
                if (usuario instanceof Cliente) {
                return "Cliente";
            } else if (usuario instanceof Admin) {
                return "Admin";
            } else if (usuario instanceof Funcionario) {
                return "Funcionário";
            }
            default:
                return null;
        }
    }
    
    
}
