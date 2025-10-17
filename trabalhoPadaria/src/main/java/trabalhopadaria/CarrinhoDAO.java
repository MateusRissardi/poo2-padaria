/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trabalhopadaria;

import entidades.Carrinho;
import entidades.Usuario;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.util.List;

/**
 *
 * @author mateu
 */
public class CarrinhoDAO {
  private EntityManagerFactory emf;
    
    public CarrinhoDAO(){
        this.emf = Persistence.createEntityManagerFactory("ConexaoJPA");
    }
    
    public void salvar(Carrinho umCar){
        EntityManager em = emf.createEntityManager();
        try{
          em.getTransaction().begin();
          em.persist(umCar);
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
    
        public void update(Carrinho carrinho) {
    EntityManager em = emf.createEntityManager();
    try {
        em.getTransaction().begin();

        em.merge(carrinho);
        
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
            Carrinho p = em.find(Carrinho.class, id);
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
            String jpql = "DELETE FROM Carrinho c";
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
    
    public Carrinho encontrarPorID(Long id){
        EntityManager em = emf.createEntityManager();
        try{
            return(em.find(Carrinho.class, id));
        }
        finally{
            em.close();
        }
    }
    
    public List<Carrinho> findAll() { 
        EntityManager em = emf.createEntityManager();
        try{
           String jpql = "SELECT c FROM Carrinho c";
           return em.createQuery(jpql, Carrinho.class).getResultList();
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
