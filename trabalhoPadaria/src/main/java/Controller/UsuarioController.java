/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller; // Crie um novo pacote para os controllers

import Excecoes.usuarioInvalido;
import entidades.Cliente;
import entidades.Funcionario;
import entidades.Usuario;
import jakarta.persistence.NoResultException;
import java.util.List;
import trabalhopadaria.UsuarioDAO; // Importe seu DAO

public class UsuarioController {

    private UsuarioDAO usuarioDAO;

    public UsuarioController() {
        this.usuarioDAO = new UsuarioDAO(); // O Controller instancia o DAO
    }

    /**
     * Tenta autenticar um usuário pelo CPF e senha.
     * @param cpf O CPF digitado.
     * @param senha A senha digitada.
     * @return O objeto Usuario se a autenticação for bem-sucedida, null caso contrário.
     */
    public Usuario autenticarUsuario(String cpf, String senha) {
        if (cpf == null || cpf.trim().isEmpty() || senha == null || senha.isEmpty()) {
            // Validação básica de entrada
            return null;
        }
        try {
            // Tenta encontrar o usuário pelo CPF (você precisará criar este método no DAO)
            Usuario usuario = usuarioDAO.encontrarPorCPF(cpf.trim()); 
            
            if (usuario != null && usuario.getSenha().equals(senha)) {
                return usuario; // Autenticação bem-sucedida
            } else {
                return null; // Senha incorreta ou usuário não encontrado
            }
        } catch (NoResultException e) {
            return null; // Usuário não encontrado no banco
        } catch (Exception e) {
            // Logar o erro real seria bom aqui
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
    public List<Usuario> listarTodos() {
        return usuarioDAO.findAll(); 
    }
    
    public void atualizarUsuario(Usuario usuario){
        usuarioDAO.update(usuario);
    }
    
    public List<Usuario> listarTodosClientes() {
        return usuarioDAO.findAllClientes(); 
    }
    
    public List<Usuario> listarTodosFuncionarios() {
        return usuarioDAO.findAllFuncionarios(); 
    }
    
    public List<Usuario> listarTodosAdmins() {
        return usuarioDAO.findAllAdmins(); 
    }

    public Usuario buscarUsuarioPorId(Long id) {
        return usuarioDAO.encontrarPorID(id);
    }
    
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
}