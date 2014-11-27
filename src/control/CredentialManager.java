/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.util.List;
import model.Credential;
import model.CredentialJpaController;

/**
 *
 * @author Ejer
 */
public interface CredentialManager {
    
List<Credential> findCredentialEntities();

public Credential findCredential(String id);







}
