/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trabalhopadaria;

import Excecoes.usuarioInvalido;
import entidades.Produto;
import entidades.Usuario;
import excecoes.produtoInvalido;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.util.List;

/**
 *
 * @author mateu
 */
public class ProdutoDAO {
  private EntityManagerFactory emf;
    
    public ProdutoDAO(){
        this.emf = Persistence.createEntityManagerFactory("ConexaoJPA");
    }
    
   private boolean nomeJaExiste(String nome, EntityManager em) {
        String jpql = "SELECT COUNT(p) FROM Produto p WHERE p.nome = :nome";
        Long count = em.createQuery(jpql, Long.class)
                       .setParameter("nome", nome)
                       .getSingleResult();
        return count > 0;
    }
    
    public Produto salvar(Produto produto){
        EntityManager em = emf.createEntityManager();
        Produto produtoSalvo = null;
        try{
            if (nomeJaExiste(produto.getNome(), em)) {
                throw new produtoInvalido("O produto com nome '" + produto.getNome() + "' já existe!");
            }
            em.getTransaction().begin();
            produtoSalvo = em.merge(produto);
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
        return produtoSalvo;
    }
    
    public Produto update(Produto produto) {
        EntityManager em = emf.createEntityManager();
        Produto produtoAtualizado = null;
        try {
            em.getTransaction().begin();

            produtoAtualizado = em.merge(produto);

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
        return produtoAtualizado;
    }
    
    public void excluir(Long id){
        EntityManager em = emf.createEntityManager();
        try{
            Produto p = em.find(Produto.class, id);
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
            String jpql = "DELETE FROM Produto c";
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
    
    public Produto encontrarPorID(Long id){
        EntityManager em = emf.createEntityManager();
        try{
            return(em.find(Produto.class, id));
        }
        finally{
            em.close();
        }
    }
    
    public List<Produto> findAll() { 
        EntityManager em = emf.createEntityManager();
        try{
           String jpql = "SELECT c FROM Produto c";
           return em.createQuery(jpql, Produto.class).getResultList();
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
