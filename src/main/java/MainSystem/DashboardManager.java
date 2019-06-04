package MainSystem;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;

/**
 * Created by xinyuan.zhang on 9/4/17.
 */


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







}
