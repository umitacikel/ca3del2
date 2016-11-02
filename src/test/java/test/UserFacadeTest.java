/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import entity.User;
import facades.UserFacade;
import java.util.HashMap;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import security.IUser;
import security.PasswordStorage;

/**
 *
 * @author umita
 */
public class UserFacadeTest {
    @Before
    public void setUpPerson() throws PasswordStorage.CannotPerformOperationException{
       HashMap<String, Object> puproperties = new HashMap();
        puproperties.put("javax.persistence.sql-load-script-source", "scripts/ClearDB.sql");
        Persistence.generateSchema("seedMavenP", puproperties);
        Persistence.generateSchema("seedMavenP", null);
        System.out.println("creating");
   
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("seedMavenP");
        EntityManager em = emf.createEntityManager();
        System.out.println("asd");
        em.getTransaction().begin();
        User u = new User("test", "test");
        PasswordStorage.createHash("test");
        em.persist(u);
        System.out.println("asd");
        em.getTransaction().commit();
        em.close();
    }
    
    
    
    @Test
    public void testGetUser(){
        System.out.println("get user");
    UserFacade us = new UserFacade();
        System.out.println(us.getUserByUserId("1"));
        System.out.println(us.getPersons());
      }
    
}
