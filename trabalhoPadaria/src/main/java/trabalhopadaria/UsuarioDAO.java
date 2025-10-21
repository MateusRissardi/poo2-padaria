/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trabalhopadaria;

import Excecoes.usuarioInvalido;
import entidades.Usuario;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;
import jakarta.persistence.EntityManagerFactory;
import java.util.List;

/**
 *
 * @author mateu
 */
public class UsuarioDAO {
    private EntityManagerFactory emf;
    
    public UsuarioDAO(){
        this.emf = Persistence.createEntityManagerFactory("ConexaoJPA");
    }
    
    private boolean nomeJaExiste(String nome, EntityManager em) {
        String jpql = "SELECT COUNT(p) FROM Usuario p WHERE p.nome = :nome";
        Long count = em.createQuery(jpql, Long.class)
                       .setParameter("nome", nome)
                       .getSingleResult();
        return count > 0;
    }
    public Usuario salvar(Usuario usuario){
        EntityManager em = emf.createEntityManager();
        Usuario usuarioSalvo = null;
        try{
          if (nomeJaExiste(usuario.getNome(), em)) {
                throw new usuarioInvalido("O usuário com nome '" + usuario.getNome() + "' já existe!");
            }
          em.getTransaction().begin();
          usuarioSalvo = em.merge(usuario);
          em.getTransaction().commit();
        }
        catch(Exception ex){
            System.out.println("Erro: " + ex.getMessage());
            if (em.getTransaction().isActive()) {
            em.getTransaction().rollback();
            }
        }
        finally{
            em.close();
        }
        
          return usuarioSalvo;
    }
    
    public Usuario update(Usuario umUsuario) {
    EntityManager em = emf.createEntityManager();
    Usuario usuarioAtualizado = null;
    try {
        em.getTransaction().begin();

        usuarioAtualizado = em.merge(umUsuario);
        
        em.getTransaction().commit();
    } catch (Exception ex) {
        System.err.println("Erro ao atualizar cliente: " + ex.getMessage());
        ex.printStackTrace();
        if (em.getTransaction().isActive()) {
            em.getTransaction().rollback();
        }
    } finally {
        em.close();
    }
    return usuarioAtualizado;
}
    
    public void excluir(Long id){
        EntityManager em = emf.createEntityManager();
        try{
            Usuario p = em.find(Usuario.class, id);
            if(p != null){
                em.remove(p);
                System.out.println("Excluido com sucesso!");
            }
            em.getTransaction().commit();
        }
        catch(Exception ex){
            System.out.println("Erro: " + ex.getMessage());
        }
        finally{
            em.close();
        }
        
    }
    
    public void excluir(String cpf){
        EntityManager em = emf.createEntityManager();
        try{
            em.getTransaction().begin();
            
            Usuario usuario = em.createQuery(
            "SELECT u FROM Usuario u WHERE u.cpf = :cpf", Usuario.class)
            .setParameter("cpf", cpf)
            .getSingleResult();
            
            if (usuario != null){
                em.remove(usuario);
            }
            
            em.getTransaction().commit();
        } catch (Exception ex) {
            System.out.println("Erro: " + ex.getMessage());
        } finally {
            em.close();
        }
    }
    
    
    public void excluirTodos(){
        EntityManager em = emf.createEntityManager();
        try{
            String jpql = "DELETE FROM Usuario c";
            em.getTransaction().begin();
            em.createQuery(jpql).executeUpdate();
            em.getTransaction().commit();
        }
        catch(Exception ex){
            System.out.println("Erro: " + ex.getMessage());
        }
        finally{
            em.close();
        }
    }
    
    public Usuario encontrarPorID(Long id){
        EntityManager em = emf.createEntityManager();
        try{
            return(em.find(Usuario.class, id));
        }
        finally{
            em.close();
        }
    }
    
    public List<Usuario> findAll() { 
        EntityManager em = emf.createEntityManager();
        try{
           String jpql = "SELECT c FROM Usuario c";
           return em.createQuery(jpql, Usuario.class).getResultList();
        }
        catch(Exception ex){
            System.out.println("Erro: " + ex.getMessage());
            return java.util.Collections.emptyList();
        }
        finally{
            em.close();
        }
    } 
    
}
