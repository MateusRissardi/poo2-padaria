/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trabalhopadaria;

import excecoes.nomeInvalido;
import entidades.Cliente;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;
import jakarta.persistence.EntityManagerFactory;

/**
 *
 * @author mateu
 */
public class ClienteDAO {
    private EntityManagerFactory emf;
    
    public ClienteDAO(){
        this.emf = Persistence.createEntityManagerFactory("ConexaoJPA");
    }
    
    public void salvar(Cliente umCliente){
        EntityManager em = emf.createEntityManager();
        try{
          em.getTransaction().begin();
          em.persist(umCliente);
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
}
