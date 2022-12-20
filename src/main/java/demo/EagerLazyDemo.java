package demo;

import entity.Course;
import entity.Instructor;
import entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class EagerLazyDemo {

    public static void main(String[] args) {

        //create session factory
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .addAnnotatedClass(Course.class)
                .buildSessionFactory();

        //create session
        Session session = factory.getCurrentSession();

        try {

          //start a transaction
            session.beginTransaction();

            // get the instructor from db
            int theId = 1;
            Instructor tempInstructor = session.get(Instructor.class, theId);

            System.out.println("luv2code: Instructor: "+tempInstructor);

            System.out.println("Courses: "+tempInstructor.getCourse());

            System.out.println("luv2code: Done!");

                       //commit transaction
            session.getTransaction().commit();

            //close the session

            session.close();

            System.out.println("Hey the session closed");

            /**
             * To apply lazy loading
             *
             * 1. call getter method while session still open
             * 2.
             */

            //get courses for the instructoe
            System.out.println("Courses: "+tempInstructor.getCourse());

            System.out.println("luv2code: Done!");
        }finally {
            session.close();
            factory.close();
        }


    }
}
