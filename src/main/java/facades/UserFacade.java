package facades;

import entity.User;
import security.IUserFacade;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import security.IUser;
import security.IUserFacade;
import security.PasswordStorage;

public class UserFacade implements IUserFacade {
  /*When implementing your own database for this seed, you should NOT touch any of the classes in the security folder
    Make sure your new facade implements IUserFacade and keeps the name UserFacade, and that your Entity User class implements 
    IUser interface, then security should work "out of the box" with users and roles stored in your database */
  
      EntityManagerFactory emf;
     private final  Map<String, IUser> users = new HashMap<>();
     
    public UserFacade()
    {
    }

    public void setEmf(EntityManagerFactory emf)
    {
        this.emf = emf;
    }
    
    
    public UserFacade(EntityManagerFactory emf)
    {
        this.emf = emf;
    }
    
  /*
  Return the Roles if users could be authenticated, otherwise null
  */
    @Override
  public List<String> authenticateUser(String userName, String password){
           EntityManager em = emf.createEntityManager();
           IUser iu = null;
           IUser ip =null;
           try {
               System.out.println("---------------------------asdsad");
            em.getTransaction().begin();
            iu = em.find(IUser.class, userName);
            ip = em.find(IUser.class, password);
            if (iu != null && iu.getPassword().equals(password)) {
                return iu.getRolesAsStrings();

            } else {
                return null;
            }
        } catch (Exception ex) {
               System.out.println("Error:" + ex);
           return null;
    }finally{
          em.close();
           }
         
  }

    
  public List<User> getPersons()
    {
        EntityManager em = emf.createEntityManager();

        List<User> persons = null;
        
        try
        {
            em.getTransaction().begin();
            persons = em.createQuery("Select p from user p").getResultList();
            em.getTransaction().commit();
            return persons;
        }
        finally
        {
            em.close();
        }
    }

    public IUser getUserByUserId(int id) {
             EntityManager em = emf.createEntityManager();

        User u = null;
        
        try
        {
            em.getTransaction().begin();
            u = em.find(User.class, id);
            em.getTransaction().commit();
            return u;
        }
        finally
        {
            em.close();
        } 
    }

    @Override
    public IUser getUserByUserId(String id) {


         EntityManager em = emf.createEntityManager();

        User u = null;
        
        try
        {
            em.getTransaction().begin();
            u = em.find(User.class, id);
            em.getTransaction().commit();
            return u;
        }
        finally
        {
            em.close();
        } 
    }
}
