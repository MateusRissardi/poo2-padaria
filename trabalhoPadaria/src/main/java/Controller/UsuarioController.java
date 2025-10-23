/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Excecoes.usuarioInvalido;
import entidades.Cliente;
import entidades.Funcionario;
import entidades.Usuario;
import jakarta.persistence.NoResultException;
import java.util.List;
import trabalhopadaria.UsuarioDAO;

public class UsuarioController {

    private UsuarioDAO usuarioDAO;

    public UsuarioController() {
        this.usuarioDAO = new UsuarioDAO();
    }

    //Tenta autenticar um usuário pelo CPF e senha.
    public Usuario autenticarUsuario(String cpf, String senha) {
        if (cpf == null || cpf.trim().isEmpty() || senha == null || senha.isEmpty()) {
            return null;
        }
        try {
            Usuario usuario = usuarioDAO.encontrarPorCPF(cpf.trim()); 
            if (usuario != null && usuario.getSenha().equals(senha)) {
                return usuario; // Autenticação bem-sucedida
            } else {
                return null; // Senha incorreta ou usuário não encontrado
            }
        } catch (NoResultException e) {
            return null;
        } catch (Exception e) {
            System.err.println("Erro ao autenticar usuário: " + e.getMessage());
            return null;
        }
    }

    // Salva um novo Cliente no banco de dados.
    public Cliente salvarNovoCliente(String nome, String cpf, String telefone, String senha) throws usuarioInvalido {
                
        if (nome == null || nome.trim().isEmpty() || 
            cpf == null || cpf.trim().isEmpty() || 
            telefone == null || telefone.trim().isEmpty() || 
            senha == null || senha.isEmpty()) {
            throw new usuarioInvalido("Todos os campos são obrigatórios.");
        }
        if(cpf.length() != 14){
            throw new usuarioInvalido("CPF é inválido.");
        }
        Cliente novoCliente = new Cliente(nome.trim(), cpf.trim(), telefone.trim(), senha);
        return (Cliente) usuarioDAO.salvar(novoCliente); 
    }
    
    // Salva um novo Funcionario no banco de dados.
    public Funcionario salvarNovoFuncionario(String nome, String cpf, String telefone, String senha) throws usuarioInvalido{
                
        if (nome == null || nome.trim().isEmpty() || 
            cpf == null || cpf.trim().isEmpty() || 
            telefone == null || telefone.trim().isEmpty() || 
            senha == null || senha.isEmpty()) {
            throw new usuarioInvalido("Todos os campos são obrigatórios.");
        }
        
        if(cpf.length() != 14){
            throw new usuarioInvalido("CPF é inválido.");
        }

        Funcionario novoFuncionario = new Funcionario(nome.trim(), cpf.trim(), telefone.trim(), senha);
        return (Funcionario) usuarioDAO.salvar(novoFuncionario); 
    }
    // Lista todos os usuarios salvos no banco
    public List<Usuario> listarTodos() {
        return usuarioDAO.findAll(); 
    }
    
    // Atualiza um usuario específico no banco
    public void atualizarUsuario(Usuario usuario){
        usuarioDAO.update(usuario);
    }
    
    // Lista todos os clientes salvos no banco
    public List<Usuario> listarTodosClientes() {
        return usuarioDAO.findAllClientes(); 
    }
    
    // Lista todos os funcionarios salvos no banco
    public List<Usuario> listarTodosFuncionarios() {
        return usuarioDAO.findAllFuncionarios(); 
    }
    
    // Lista todos os admins salvos no banco
    public List<Usuario> listarTodosAdmins() {
        return usuarioDAO.findAllAdmins(); 
    }

    // Busca um usuarios via ID no banco
    public Usuario buscarUsuarioPorId(Long id) {
        return usuarioDAO.encontrarPorID(id);
    }
    
    // Apaga um usuario por meio do CPF cadastrado
    public void apagarUsuario(String cpf) throws usuarioInvalido{
        List<Usuario> usuarios = listarTodos();
        int contador = 0;
        for(Usuario umUsu : usuarios){
            if(umUsu.getCpf().equals(cpf)){
                usuarioDAO.excluir(cpf);
            }
            else{
                contador++;
            }
        }
        if(usuarios.size() == contador){
            throw new usuarioInvalido("Usuário não encontrado.");
        }
        
    }
    
    // Apaga um usuario por meio do Id cadastrado
    public void apagarUsuario(Long id) throws usuarioInvalido{
        List<Usuario> usuarios = listarTodos();
        int contador = 0;
        for(Usuario umUsu : usuarios){
            if(umUsu.getId().equals(id)){
                usuarioDAO.excluir(id);
            }
            else{
                contador++;
            }
        }
        if(usuarios.size() == contador){
            throw new usuarioInvalido("Usuário não encontrado.");
        }
    }
    
    public Usuario buscarPorNome(String nome) {
    for (Usuario u : listarTodosClientes()) {
        if (u.getNome().equalsIgnoreCase(nome)) {
            return u;
        }
    }
    return null;
}
}