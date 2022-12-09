package demo;

import entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class QueryStudentDemo {

    public static void main(String[] args) {

        //create session factory
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();

        //create session
        Session session = factory.getCurrentSession();

        try {

            //start a transaction
            session.beginTransaction();

            //query students
            List<Student> theStudents = session.createQuery("from Student").getResultList();

            //display the students
            displayStudent(theStudents);

            //query student lastName = 'DOE'
            theStudents = session.createQuery("from Student s where s.lastName='Duck'").list();
            displayStudent(theStudents);

            //query students: lastName = 'Doe' OR firstName = 'Daffy'
            theStudents = session.createQuery("from Student s where s.lastName='Duck' OR s.firstName='Azhar'").list();
            displayStudent(theStudents);

            //query students: where email like '%LUV2code.com'
            theStudents = session.createQuery("from Student s where s.email LIKE '%luv2code.com'").list();
            displayStudent(theStudents);

            //commit transaction
            session.getTransaction().commit();


        }finally {
            factory.close();
        }


    }

    private static void displayStudent(List<Student> theStudents) {
        theStudents.stream()
                .forEach(System.out::println);
    }
}
