/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trabalhopadaria;

import Excecoes.usuarioInvalido;
import excecoes.nomeInvalido;
import entidades.Cliente;
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
    
    public void salvar(Usuario usuario){
        EntityManager em = emf.createEntityManager();
        try{
          List<Usuario> usuarios = findAll();
          for( Usuario umUsuario : usuarios ){
              if(umUsuario.getCpf().equals(usuario.getCpf())){
                  throw new usuarioInvalido("O usuário já existe!");
              }
          }
          em.getTransaction().begin();
          em.persist(usuario);
          em.getTransaction().commit();
        }
        catch(Exception ex){
            System.out.println("Erro: " + ex.getMessage());
            ex.printStackTrace();
            em.getTransaction().rollback();
        }
        finally{
            em.close();
        }
    }
    
    public void update(Usuario umUsuario) {
    EntityManager em = emf.createEntityManager();
    try {
        em.getTransaction().begin();

        em.merge(umUsuario);
        
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
}
    
    public void excluir(Long id){
        EntityManager em = emf.createEntityManager();
        try{
            Cliente p = em.find(Cliente.class, id);
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
    
    
    public void excluirTodos(){
        EntityManager em = emf.createEntityManager();
        try{
            String jpql = "DELETE FROM Cliente c";
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
    
    public Cliente encontrarPorID(Long id){
        EntityManager em = emf.createEntityManager();
        try{
            return(em.find(Cliente.class, id));
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
