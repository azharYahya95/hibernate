package demo;

import entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class ReadStudentDemo {

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
            Student tempStudent = new Student("Daffy", "Duck", "DaffyDuck@luv2code.com");

            //start a transaction
            session.beginTransaction();

            //save the student object
            System.out.println("Saving the Student...");
            System.out.println(tempStudent);
            session.save(tempStudent);

            //commit transaction
            session.getTransaction().commit();

            //My New Code

            //find out the student's id: primary key
            System.out.println("saved Student. Generated id: "+tempStudent.getId());
            System.out.println("Done");

            //now get a new session and start a transaction
            session = factory.getCurrentSession();
            session.beginTransaction();

            //retrieve student based on id: primary key
            System.out.println("Getting student with id: "+tempStudent.getId());

            Student myStudent = session.get(Student.class, tempStudent.getId());

            System.out.println("Get complete: "+myStudent);

            //commit transaction
            session.getTransaction().commit();

            System.out.println("Done!!");

        }finally {
            factory.close();
        }


    }
}
