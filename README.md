## Hibernate Mapping - Many to Many

### Overview
- Many to Many mapping
  - A course can have many students
  - A students can have many courses
  ```mermaid
    flowchart LR;
    C1([Course]);C2([Course]);C3([Course]);C4([Course]);
    S1([Student]);S3([Student]);S4([Student]);
    C1 --> S1;
    C2 --> S1;
    C2 --> S3;
    C3 --> S4;
    C4 --> S3;
    C4 --> S4;
  ```
- Join Table
  - A table that provides a mapping between two tables.
  - It has foreign keys for each table to define the mapping relationships

### Development process: Many to Many
1. Prep Work - Define database tables
2. Update Course class
```
@Entity
@Table(name="course")
public static class Course{
  //...
  
  @ManyToMany
  @JoinTable(
    name = "course_student",
    joinColumns = @JoinColumn(name="course_id"),
    inverseJoinColumn=@JoinColumn(name="student_id")
  )
  private List<Student> students;
  //getter / setter
  
  //...
}
```
3. Update Student class
```
@Entity
@Table(name="student")
public class Student{
  //....
  @ManyToMany
  @JoinTable(
    name="course_student",
    joinColumns = @JoinColumn(name="student_id"),
    inverseJoinColumn=@JoinColumn(name="course_id")
  )
  private List<Course> courses;
  //....
}
```
4. Create Main App