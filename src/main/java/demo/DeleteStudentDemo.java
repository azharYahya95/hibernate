package demo;

import entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DeleteStudentDemo {

    public static void main(String[] args) {

        //create session factory
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();

        //create session
        Session session = factory.getCurrentSession();

        try {
            int studentId = 1;

            session = factory.getCurrentSession();
            session.beginTransaction();

            //retrieve student based on the id; primary Key
            System.out.println("Getting student with id: "+studentId);

            Student myStudent = session.get(Student.class, studentId);

            //delete the student
            //System.out.println("Deleting student: "+myStudent);
            //session.delete(myStudent);

            System.out.println("Deleting student: id=2");
            session.createQuery("delete from Student where id=2").executeUpdate();

            //commit Transation
            session.getTransaction().commit();

            System.out.println("DOne!!");
        }finally {
            factory.close();
        }


    }
}
