## Hibernate Configuration
### 1. What hibernate configuration file do
- just tell how to setup the database

### 2. Hibernate Annotations for Configuration
#### a.  Map Class to Database Table
- @Entity
  - show that we gonna map to the database table
- @Table
  - show the name of the database table that we want to map to

```java
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "student")
public class Student{
    //...
}
```

#### b. Map fields to database columns
- @Id
  - its Primary Key for class
  - unique number
- @Column(name="id")
  - maps java fields to database column

```java
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "student")
public class Student {

  @Id
  @Column(name="id")
  private int id;
  
  @Column(name = "first_name")
  private String firstName;
}
```

### 3. Session Factory and Session
|Class|Description|
|---|---|
|Session Factory|- Reads hibernate config file </br> - Create sessions object </br> - Only create once in your app|
|Session|- Wraps a JDBC connection <br> - used to save/retrieve Object <br> retrieved from session Factory|

```
public static void main(String[]args){
        SessionFactory factory = new Configuration()
                                .configure("hibernate.cfg.xml")
                                .addAnnotatedClass(Student.class)
                                .buildSessionFactory();
        Session session = factory.getCurrentSession();
        try{
            //use this session to save /retrieve Java Object
        }finally{
            factory.close();
        }
}
```
- hibernate.cfg.xml - default configure file call by Hibernate

### 4. Primary Keys
##### a. What is Primary Key
- Uniquely index each row in a table
- must be a unique value
- cannot contain null value

##### b. ID generation strategies
|Name|Description|
|---|---|
|GenerationType.AUTO|Pick an appropriate strategy for the particular database|
|GenerationType.IDENTITY|Assign primary keys using database identity column|
|GenerationType.SEQUENCE|Assign primary keys using database sequence|
|GenerationType.TABLE|Assign primary keys using an underlying database table to ensure uniqueness|

*example code*

```java
import javax.persistence.*;

@Entity
@Table(name = "student")
public class Student {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private int id;
}
```
- @GeneratedValue(strategy = GenerationType.IDENTITY) 
  - follow how database setup
  - most commonly used for MySQL AutoIncrement

### 5. CRUD
##### a. Save Object
- code example
  ```
  try{
      //create a student object
      Student tempStudent = new Student("Paul", "Wall", "paul@luv2code.com");
      
      //start transaction
      session.beginTransaction();
      
      //save the student
      session.save(tempStudent);
      
      //commit the transaction
      session.getTransaction().commit();
      
  }finally{
      factory.close();
  }
  ```
##### b. Retrieve Object with Hibernate
- can only retrieve it using Primary Key
- if not found, it will return null
- exampleCode
  ```
  //create Java Object
  Student theStudent = new Student("Azhar", "Yahya", "azharusom95@gmail.com");
  
  //save it to database
  session.save(theStudent);
  
  //retrieve/read datat using Primary Key
  Student myStudent = session.get(Student.class, theStudent.getId());
  ```
##### c. Querying Object with Hibernate
- can Query using HQL
  - Similar in nature to SQL
    - where, like, order by, join, in, etc
-example
    - Retrieve student using OR predicate
      ```
        List<Student> theStudents =
                    session
                          .createQuery("from Student s where s.lastName='Doe' OR s.firstName='Daffy'")
                          .getResultList();
      ```
    - Retrieve student using LIKE predicate
      ```
        List<Student> theStudents =
                    session
                          .createQuery("from Student s where s.email LIKE '%luv2code.com'")
                          .getResultList();
      ```
##### d. Update Object with Hibernate
- Update one Student
```
int studentId = 1
Student myStudent = session.get(Student.class, studentId);

//update first Name to "Scooby"
myStudent.setFirstName("Scooby");

//commit the transaction
session.getTransaction().commit();

```
- Update email for all Student
```
session.
      createQuery("update Student set email='foo@gmail.com'")
      .executeUpdate();
```

##### e. Delete Object with Hibernate
```
int student = 1;
Student myStudent = session.get(Student.class, studentId);

//delete the student
session.delete(myStudent); 

//commit the transaction
session.getTransaction().commit();
```
- another way to delete
```
session
    .createQuery("delete from Student where id=2")
    .executeUpdate();
```