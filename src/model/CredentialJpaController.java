/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import control.CredentialManager;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author Ejer
 */
public class CredentialJpaController implements Serializable, CredentialManager{
    
    public CredentialJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
   
  
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    @Override
    public List<Credential> findCredentialEntities() {
        return findCredentialEntities(true, -1, -1);
    }

    public List<Credential> findCredentialEntities(int maxResults, int firstResult) {
        return findCredentialEntities(false, maxResults, firstResult);
    }

    private List<Credential> findCredentialEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Credential.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    @Override
    public String findCredential(String user, String pass){    // throws NotFoundException
         EntityManager em = getEntityManager();
         try{
     Credential user1 = em.find(Credential.class, user);
            if(user1 == null){
                return "fail";                                 //throw new NotFoundException("Fail");
                    } else {   
                    if (!user1.getPassword().equals(pass)) {
                        return "false";                        //throw new NotFoundException("Fail");
                    }
                    return "" + user1.getRole();
                    } 
            }finally{
                    em.close();
                    }
    }


    public int getCredentialCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Credential> rt = cq.from(Credential.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }  
}
