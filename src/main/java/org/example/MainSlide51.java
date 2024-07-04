package org.example;

import jakarta.persistence.TypedQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class MainSlide51 {

    public static void main(String[] args) {
        SessionFactory factory = new Configuration().configure().buildSessionFactory();
        Session session = factory.openSession();
        String hql = "SELECT SUM(U.salary), U.city FROM User U GROUP BY U.city";
        //The query returns Object[] arrays of length 2
        TypedQuery query = session.createQuery(hql);
        List<Object[]> result =query.getResultList();
        for (Object[] o : result) {
            System.out.println("Total salary " +o[0] +" | city: "+ o[1] );
        }
    }

}
