package demo;

import entity.Course;
import entity.Instructor;
import entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateInstructorDemo {

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
            // create the objects
            Instructor tempInstructor =
                    new Instructor("Susan", "Patel", "susan@luv2code.com");

            InstructorDetail instructorDetail
                    = new InstructorDetail("http://www.luv2code.com/instagram", "Video Games");

            //associate the objects
            tempInstructor.setInstructorDetail(instructorDetail);


          //start a transaction
            session.beginTransaction();

            //save the instructor
            //
            // Note: this will Also save the details of object
            // because of CascadeType.ALL
            //
            System.out.println("Saving instrucotr: "+tempInstructor );
            session.save(tempInstructor);

            //commit transaction
            session.getTransaction().commit();

            System.out.println("Done");
        }finally {
            session.close();
            factory.close();
        }


    }
}
