To connect with the h2 database and its console, you will need to add the dependencies in your pom.xml file. The dependencies include the jdbc-api, and the h2 database. they will look like this

```xml
 <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-jdbc</artifactId>
    </dependency>

    <dependency>
      <groupId>com.h2database</groupId>
      <artifactId>h2</artifactId>
      <scope>runtime</scope>
    </dependency>
```

And then you need to specify your database(h2) properties inside your application.properties file. here we just want to set the db name, and the datasource. see below

```bash
spring.application.name=runnerz-api
spring.h2.console.enabled=true
spring.datasource.generate-unique-name=false
spring.datasource.name=runnerz-api
```

And then when you restart the app, you will see a generated url for your database that looks like this "url=jdbc:h2:mem:runnerz-api". You can copy this and navigate to "localhost:8080/h2-console" and then paste the database url in there, and connect to it.

What a JDBC api makes you do is that it allows you to run sql queries from your code(in your repository) to talk to your database. Below is how a crud jdbc repository will look like
```java
@Repository
public class RunRepository{
    private static final Logger log = LoggerFactory.getLogger(RunRepository.class);
    private final JdbcClient jdbcClient;

    public RunRepository(JdbcClient jdbcClient){
        this.jdbcClient = jdbcClient;
    }

    public List<Run> findAll(){
        return jdbcClient.sql("select * from run")
                .query(Run.class)
                .list();
    }

    public Optional<Run> findById(Integer id) {
        return jdbcClient.sql("SELECT id,title,started_on,completed_on,miles,location FROM Run WHERE id = :id" )
                .param("id", id)
                .query(Run.class)
                .optional();
    }

    public void create(Run run) {
        var updated = jdbcClient.sql("INSERT INTO Run(id,title,started_on,completed_on,miles,location) values(?,?,?,?,?,?)")
                .params(List.of(run.id(),run.title(),run.startedOn(),run.completedOn(),run.miles(),run.location().toString()))
                .update(); // we use this update method to create, update or delete..since they are all a form of update to our database.

        Assert.state(updated == 1, "Failed to create run " + run.title());
    }

    public void update(Run run, Integer id) {
        var updated = jdbcClient.sql("update run set title = ?, started_on = ?, completed_on = ?, miles = ?, location = ? where id = ?")
                .params(List.of(run.title(),run.startedOn(),run.completedOn(),run.miles(),run.location().toString(), id))
                .update();

        Assert.state(updated == 1, "Failed to update run " + run.title());
    }

    public void delete(Integer id) {
        var updated = jdbcClient.sql("delete from run where id = :id")
                .param("id", id)
                .update();

        Assert.state(updated == 1, "Failed to delete run " + id);
    }

    public int count() {
        return jdbcClient.sql("select * from run").query().listOfRows().size();
    }

    public void saveAll(List<Run> runs) {
        runs.stream().forEach(this::create);
    }

    public List<Run> findByLocation(String location) {
        return jdbcClient.sql("select * from run where location = :location")
                .param("location", location)
                .query(Run.class)
                .list();
    }


}
```
Obviously this involves a lot of other steps like loading data into your db etc. It is an in memory database(h2) so the data is lost whenever you restart the server. So in production environments it is better to switch to an actual database.