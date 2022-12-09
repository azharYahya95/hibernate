package demo;

import entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class PrimaryKeyDemo {
    public static void main(String[] args) {

        //create session factory
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();

        //create session
        Session session = factory.getCurrentSession();

        try {
            // use the session object to save Java object
            //create a student object
            System.out.println("Creating a new student object");
            Student student1 = new Student("Paul", "Wall", "Paul@luv2code.com");
            Student student2 = new Student("Azhar", "Yahya", "azhar@luv2code.com");
            Student student3 = new Student("mira", "hazira", "mira@luv2code.com");

            //start a transaction
            session.beginTransaction();

            //save the student object
            System.out.println("Saving the Student...");
            session.save(student1);
            session.save(student2);
            session.save(student3);

            //commit transaction
            session.getTransaction().commit();

            System.out.println("Done");
        }finally {
            factory.close();
        }


    }
}
