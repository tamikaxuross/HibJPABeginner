package org.example;

import jakarta.persistence.TypedQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class MainSlide50 {
    public static void main(String[] args) {
        SessionFactory factory = new Configuration().configure().buildSessionFactory();
        Session session = factory.openSession();
        TypedQuery<Object[]> queryy = session.createQuery( "SELECT U.salary, U.fullName FROM User AS U", Object[].class);
        List<Object[]> resultss = queryy.getResultList();
        for (Object[] a : resultss) {
            System.out.println("Salary: " + a[0] + ", Full name: " + a[1]); }
    }

}
