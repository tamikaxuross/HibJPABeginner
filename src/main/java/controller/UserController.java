package controller;


import jakarta.persistence.TypedQuery;
import model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.List;


public class UserController {
    public static void main(String[] args) {
        SessionFactory factory = new Configuration().configure().buildSessionFactory();
        Session session = factory.openSession();

        updateUserNativeSql(session);

        //addUser(factory,session);

        //findUserNativeSql(session);

        //updateUserNativeSql(session);

        //getMaxSalaryGroupBy(session);
        //getMaxSalaryGroupByBroken(session);

        //findUser(factory,session,2);

        // updateUser(session,3);

        //deleteUser(session,4);

    }


    public static void addUser(SessionFactory factory, Session session) {


        Transaction transaction = session.beginTransaction();
        User uOne = new User();
        uOne.setEmail("haseeb@gmail.com");
        uOne.setFullName("Moh Haseeb");
        uOne.setPassword("has123");
        uOne.setSalary(2000.69);
        uOne.setAge(20);
        uOne.setCity("NYC");

        User uTwo = new User();
        uTwo.setEmail("James@gmail.com");
        uTwo.setFullName("James Santana");
        uTwo.setPassword("James123");
        uTwo.setSalary(2060.69);
        uTwo.setAge(25);
        uTwo.setCity("Dallas");

        User uThree = new User();
        uThree.setEmail("Shahparan@gmail.com");
        uThree.setFullName("AH Shahparan");
        uThree.setPassword("Shahparan123");
        uThree.setSalary(3060.69);
        uThree.setAge(30);
        uThree.setCity("Chicago");

        /*========= We can pass value/data by using constructor =========*/
        User uFour = new User("Christ", "christ@gmail.com", "147852", 35, 35000.3, "NJ");
        User uFive = new User("Sid", "Sid", "s258", 29, 4000.36, "LA");
        //Integer  userid = null;
        session.persist(uOne);
        session.persist(uTwo);
        session.persist(uThree);
        session.persist(uFour);
        session.persist(uFive);

        transaction.commit();
        System.out.println("successfully saved");
        factory.close();
        session.close();
    }

    public static void updateUserNativeSql(Session session){
        String updateQuery = "UPDATE USER SET fullName = ?, password = ? WHERE USER_ID = ?";
        Query query = session.createNativeQuery(updateQuery);
        query.setParameter(1, "John Doe");
        query.setParameter(2, "newPassword");
        query.setParameter(3, 1L);
        try {
            session.getTransaction().begin();
            int updatedRows = query.executeUpdate();
            session.getTransaction().commit();

            if (updatedRows > 0) {
                // Successful update
                System.out.println("Success updated rows ="+updatedRows);
            } else {
                // Handle no updates
                System.out.println("Possible Error !updated rows ="+updatedRows);
            }
        } catch (Exception e) {
            session.getTransaction().rollback();
            // Handle exception
            System.out.println("ERROR!! ROLLING BACK update!!!");
        }


    }
    public static void findUserNativeSql(Session session) {
        String sql = "SELECT USER_ID,age FROM USER";
        Query query = session.createNativeQuery(sql);
        List<Object[]> users = query.getResultList();

        users.forEach((user)-> {
            System.out.print(user[0]+" : ");
            System.out.println(user[1]);
        });


    }

    public static void findUserHql(SessionFactory factory,Session session) {
        String hqlFrom = "FROM User"; // Example of HQL to get all records of user class
        String hqlSelect = "SELECT u FROM User u";
        TypedQuery<User> query = session.createQuery(hqlFrom, User.class);
        List<User> results = query.getResultList();

        System.out.printf("%s%13s%17s%34s%n","|User Id","|Full name","|Email","|Password");
        for (User u:results) {
            System.out.printf(" %-10d %-20s %-30s %s %n", u.getId(), u.getFullName(), u.getEmail(), u.getPassword());
        }
    }

    public  static  void  getMaxSalaryGroupBy(Session session){
        TypedQuery <Object[]> query = session.createQuery(
                "select max(u.salary),u.city from User u group by u.city",Object[].class
        );
        List<Object[]> results = query.getResultList();
        System.out.printf("%s%13s%n","Salary","City");
        for (Object[] a : results) {
            System.out.printf("%-16s%s%n",a[0],a[1]);
        }
    }
    public static void getMaxSalaryGroupByBroken(Session session){
        TypedQuery<Object[]> query = session.createQuery(
                "SELECT max(U.salary), U.City FROM User U GROUP BY U.City", Object[].class);
        List<Object[]> results = query.getResultList();
        System.out.printf("%s%13s%n","Salary","City");
        for (Object[] a : results) {
            System.out.printf("%-16s%s%n",a[0],a[1]);
        }
    }

}

