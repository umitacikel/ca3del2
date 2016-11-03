package facades;

import entity.User;
import security.IUserFacade;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import security.IUser;
import security.IUserFacade;
import security.PasswordStorage;

public class UserFacade implements IUserFacade {

    /*When implementing your own database for this seed, you should NOT touch any of the classes in the security folder
    Make sure your new facade implements IUserFacade and keeps the name UserFacade, and that your Entity User class implements 
    IUser interface, then security should work "out of the box" with users and roles stored in your database */

    EntityManagerFactory emf;
    private final Map<String, IUser> users = new HashMap<>();

    public void setEmf(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public UserFacade(EntityManagerFactory emf) {
        this.emf = emf;
    }

    /*
  Return the Roles if users could be authenticated, otherwise null
     */
    @Override
    public List<String> authenticateUser(String userName, String password) {
        EntityManager em = emf.createEntityManager();

        List<User> userList = null;
        System.out.println("hello " + userList);

        try {
            em.getTransaction().begin();
            System.out.println("hello2 ");

            Query query = em.createQuery("SELECT u FROM User AS u WHERE u.userName = :userName AND u.password = :password");
            query.setParameter("userName", userName);
            query.setParameter("password", password);
            userList = query.getResultList();
            em.getTransaction().commit();
            System.out.println(userList);

            if (userList != null && !userList.isEmpty()) {
                System.out.println("1");
                User user = userList.get(0);
                System.out.println("2");
                if (user.getPassword().equals(password)) {
                    System.out.println("3");
                    return user.getRolesAsStrings();
                } else {
                    return null;
                }
            } else {
                return null;
            }
        } catch (Exception ex) {
            System.out.println("Error:" + ex);
        } finally {
            em.close();
        }
        return null;
    }

    public List<User> getPersons() {
        EntityManager em = emf.createEntityManager();

        List<User> persons = null;

        try {
            em.getTransaction().begin();
            persons = em.createQuery("Select p from user p").getResultList();
            em.getTransaction().commit();
            return persons;
        } finally {
            em.close();
        }
    }

    public IUser getUserByUserId(int id) {
        EntityManager em = emf.createEntityManager();

        User u = null;

        try {
            em.getTransaction().begin();
            u = em.find(User.class, id);
            em.getTransaction().commit();
            return u;
        } finally {
            em.close();
        }
    }

    @Override
    public IUser getUserByUserId(String id) {

        EntityManager em = emf.createEntityManager();

        User u = null;

        try {
            em.getTransaction().begin();
            u = em.find(User.class, id);
            em.getTransaction().commit();
            return u;
        } finally {
            em.close();
        }
    }
}
