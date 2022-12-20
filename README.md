## Hibernate Mapping - Eager Loading vs Lazy Loading

<hr>

```mermaid
flowchart LR;
I([Instructor]);C1([Course]);C2([Course]);C3([Course]);
I --> C1;I --> C2; I --> C3;
```
<hr>

### Eager Loading
- Will load all dependent entities
  - Load instructor and all of their courses at once

<hr>

### Lazy Loading
- will load main entity first
  - then load dependent entities on demand
- If Hibernate session is closed, and you attempt to retrieve lazy loading
  - hibernate will throw exception
- Technique on using lazy loading
  - to retrieve lazy data, you need to open Hibernate session
  - Retrieve lazy data using
    - Option 1: session.get and call appropriate getter methods
    - Option 2: Hibernate  query with HQL

<hr>

### Best Practice
- Only load data when absolutely needed
- prefer Lazy Loading instead of Eager Loading

<hr>

### Fetch Type
- example code
    ```java
    
    import javax.persistence.Entity;
    import javax.persistence.FetchType;
    import javax.persistence.OneToMany;
    import javax.persistence.Table;
    
    @Entity
    @Table(name = "instructor")
    public class Instructor {
        //..
        @OneToMany(fetch = FetchType.LAZY, mappedBy = "instructor")
        private List<Course> courses;
        //..
    }
    
    ```
- Default Fetch Types

| Mapping     | Default Fetch Types |
|-------------|---------------------|
| @OneToOne   | FetchType.EAGER     |
| @OneToMany  | FetchType.LAZY      |
| @ManyToOne  | FetchType.EAGER     |
| @ManyToMany | FetchType.LAZY     |