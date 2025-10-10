/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trabalhopadaria;

import entidades.Venda;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

/**
 *
 * @author mateu
 */
public class VendaDAO {
  private EntityManagerFactory emf;
    
    public VendaDAO(){
        this.emf = Persistence.createEntityManagerFactory("ConexaoJPA");
    }
    
    public void salvar(Venda umaVen){
        EntityManager em = emf.createEntityManager();
        try{
          em.getTransaction().begin();
          em.persist(umaVen);
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
    
    public void update(Venda venda) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();

            em.merge(venda);

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
}
