In hindsight, connecting to a real database in springboot isnt really a big config, you just have to add the information in your application.properties file. something like this
```bash
spring.datasource.name=
spring.datasource.url=
spring.datasource.username
spring.datasource.password
# these ones have to be commented out unless you need them or sth
```

If you're using something like postgresql, you can install postgres locally, create a db for your project and provide the credentials like it's above. But you can also use docker to do that.

Also the "schema.sql" file gets picked up by embedded dbs(h2) by default,  if you're using a real db, you will want to do that as well, but since it isnt automatic, you'll have to specify that in your application.properties file. like below
```bash
spring.sql.init.mode=always
```

Now there are various ways to connect to a real db in springboot, through docker, dockercompose, or installing the db locally and so on. The approach we are going to use is the docker approach. So we go to start.spring.io and we add dependencies for the postgresql driver and the docker compose dependency. What the docker compose dependency does is that it will look at all the other dependencies(postgres) you have selected, and then create a compose.yaml file for it.


Now, you can use JDBC client or Spring Data Jpa for your database activities. But when you use JDBC, you write your own sql queries whereas it isnt the same for JPA, as jpa uses an orm called hibernate for its activities. So to use spring data jpa, you add the dependency, and then in your repository, you make your repository extend either ListCrudRepository or CrudRepository. The former returns a list while the latter returns an iterable. Both provide functionalities for CRUD related activities.After extending either ListCrudRepository or CrudRepository, you have to give it two arguments, the type of item you want to return, and the data type of the id.
```java
public interface RunRepository extends ListCrudRepository<Run, Integer> {
}
```

By just adding the above code in your repository, you have all the CRUD functionalities out of the box, without writing extra code. But if you want to return something custom(that isnt a CRUD activity), you can also add it in your repository ass well. For instance, from our above example, we want to return a list of Runs but by their location. we can do it like this.
```java
// runrepository
public interface RunRepository extends ListCrudRepository<Run, Integer> {
    List<Run> findAllByLocation(String location);
}
```

And then we write a custom method in our controller that implements the above functionality to return Runs by their location
```java
// runcontroller - we have added the location as a path param/variable, but you can add it as a query param too.
    @GetMapping("/location/{location}")
    List<Run> findbyLocation(@PathVariable String location){
        return runRepository.findAllByLocation(location);
    }
```