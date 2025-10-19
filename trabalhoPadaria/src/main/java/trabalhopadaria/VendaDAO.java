/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trabalhopadaria;

import entidades.Usuario;
import entidades.Venda;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.util.List;

/**
 *
 * @author mateu
 */
public class VendaDAO {
  private EntityManagerFactory emf;
    
    public VendaDAO(){
        this.emf = Persistence.createEntityManagerFactory("ConexaoJPA");
    }
    
    public Venda salvar(Venda umaVen){
        EntityManager em = emf.createEntityManager();
        Venda vendaSalva = null;
        try{
          em.getTransaction().begin();
          vendaSalva = em.merge(umaVen);
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
        return vendaSalva;
    }
    
    public Venda update(Venda venda) {
        EntityManager em = emf.createEntityManager();
        Venda vendaAtualizada = null;
        try {
            em.getTransaction().begin();

            vendaAtualizada = em.merge(venda);

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
        return vendaAtualizada;
    }
    
    public void excluir(Long id){
        EntityManager em = emf.createEntityManager();
        try{
            Venda p = em.find(Venda.class, id);
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
            String jpql = "DELETE FROM Venda c";
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
    
    public Venda encontrarPorID(Long id){
        EntityManager em = emf.createEntityManager();
        try{
            return(em.find(Venda.class, id));
        }
        finally{
            em.close();
        }
    }
    
    public List<Venda> findAll() { 
        EntityManager em = emf.createEntityManager();
        try{
           String jpql = "SELECT c FROM Venda c";
           return em.createQuery(jpql, Venda.class).getResultList();
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
