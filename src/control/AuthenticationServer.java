/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import model.Credential;
import model.CredentialJpaController;

/**
 *
 * @author Ejer
 */
public class AuthenticationServer {
 
    
    /**
     * @param args the command line arguments
     */
    private static EntityManagerFactory emf = 
        Persistence.createEntityManagerFactory("FinalDb"); 
    
    public static void main(String[] args) {
        
        System.out.println("starting...");  // DB opstartet
        CredentialManager manager = new CredentialJpaController(emf);
        for (Credential credential : manager.findCredentialEntities()) //  Find Users!
        {
            System.out.println("cred: "+credential.getUsername());   // og print dem ud!
        }
        System.out.println("ended!");  
    }

    
}
