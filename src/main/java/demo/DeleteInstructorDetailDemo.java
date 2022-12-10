package demo;

import entity.Instructor;
import entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DeleteInstructorDetailDemo {

    public static void main(String[] args) {

        //create session factory
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .buildSessionFactory();

        //create session
        Session session = factory.getCurrentSession();

        try {

          //start a transaction
            session.beginTransaction();

            //get the instructor detail object
            int theId = 4;
            InstructorDetail tempInstructorDetail =
                    session.get(InstructorDetail.class, theId);

            //print the instructor detail
            System.out.println("tempInstructorDetail: "+ tempInstructorDetail);

            //print the associated instructor
            System.out.println("the associated instructor: "+tempInstructorDetail.getInstructor());

            // now lets delete the instructor details
            System.out.println("Deleting tempInstructorDetail :" + tempInstructorDetail);

            //remove associated object reference
            // break bi-directional link
            tempInstructorDetail.getInstructor().setInstructorDetail(null);

            session.delete(tempInstructorDetail);

            //commit transaction
            session.getTransaction().commit();

            System.out.println("Done");
        }catch (Exception ex){
          ex.printStackTrace();
        } finally {
            session.close();
            factory.close();
        }


    }
}
