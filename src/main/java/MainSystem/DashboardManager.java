package MainSystem;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;


@Named(value ="dashboard")
@SessionScoped
public class DashboardManager implements Serializable{

    EntityManagerFactory factory;
    EntityManager em;

    public DashboardManager() {
        factory = Persistence.createEntityManagerFactory("org.hibernate.tutorial.jpa");
        em = factory.createEntityManager();

    }

    public List<ParkingLot> getLots(){

        List<ParkingLot> lots = new LinkedList<ParkingLot>();
        try {
            TypedQuery<ParkingLot> q = em.createQuery("SELECT b FROM ParkingLot b", ParkingLot.class);
            lots = q.getResultList();

        } catch (Exception e) {
            System.err.println("Error when trying to retrieve data from database: " + e);
        }
        return lots;

    }

    public void changePassword(int id){
        User user = getUserById(id);

    }

    public User getUserById(int id){
        List<User> users;
        try {
            TypedQuery<User> q = em.createQuery("SELECT b FROM User b", User.class);
            users = q.getResultList();
            for(User u : users){
                if(u.getId() == id){
                    return u;
                }
            }
            return null;


        } catch (Exception e) {
            System.err.println("Error when trying to retrieve data from database: " + e);
            return null;
        }
    }







}
