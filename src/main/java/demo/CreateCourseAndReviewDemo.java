package demo;

import entity.Course;
import entity.Instructor;
import entity.InstructorDetail;
import entity.Review;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateCourseAndReviewDemo {

    public static void main(String[] args) {

        //create session factory
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .addAnnotatedClass(Course.class)
                .addAnnotatedClass(Review.class)
                .buildSessionFactory();

        //create session
        Session session = factory.getCurrentSession();

        try {

          //start a transaction
            session.beginTransaction();

            //create a course
            Course tempCourse = new Course("Pacman - How to score one Million point");

            //add some reviews
            tempCourse.addReviews(new Review("Great course ...  loved it"));
            tempCourse.addReviews(new Review("Cool course, jow well done"));
            tempCourse.addReviews(new Review("What a dumb course"));

            // save the course ... and leverage the cascade all :-)
            System.out.println("Saving the course");
            System.out.println(tempCourse);
            System.out.println(tempCourse.getReviews());

            session.save(tempCourse);

            //commit transaction
            session.getTransaction().commit();

            System.out.println("Done");
        }finally {
            session.close();
            factory.close();
        }


    }
}
