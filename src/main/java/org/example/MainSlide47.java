package org.example;

import jakarta.persistence.TypedQuery;
import model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class MainSlide47 {

    public static void main(String[] args) {
        SessionFactory factory = new Configuration().configure().buildSessionFactory();
        Session session = factory.openSession();
        String hql = "FROM User"; // Example of HQL to get all records of user class
        TypedQuery query = session.createQuery(hql);
        List<User> results = query.getResultList();
        for (User u : results) {
            System.out.println("User Id: " + u.getId() + "|" + " Full name:" + u.getFullName() + "|" + "Email: " + u.getEmail() + "|" + "password" + u.getPassword());
        }
    }
}