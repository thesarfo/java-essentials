### Some annotations in Spring
@Required
@Autowired
@Configuration
@ComponentScan
@Bean
@Component
@Controller
@Service
@Repository
@ResponseBody

1. @Component
@Component is a class-level annotation. It is used to denote a class as a Component. We can use @Component across the application to mark the beans as Spring’s managed components. A component is responsible for some operations. Spring framework provides three other specific annotations to be used when marking a class as a Component.

2. @Service
This Annotation specify that the class with @Service holds some business logic or services in it. Besides being used in the service layer, there isn’t any other special use for this annotation.

3. @Repository
This Annotation specifies that the class with @Repository is dealing with CRUD Operations( Create Read Update Delete). It mostly deals with DAO or with Repositories that deal with databases.

4. @Controller
This Annotation specifies that the class with @Controller to indicate that they’re front controllers and responsible to handle user requests and for returning the appropriate responses to user. It is mostly used with REST Web Services.

4. @ResponseBody
We use the @ResponseBody annotation to inform the dispatcher servlet that this method doesn’t return a view name but the HTTP response directly.


To aavoid code duplication, we want to stop repeating the @ResponseBody annotation for each method. So Spring offers us the @RestController annotation, which is a combination of the @Controller and @ResponseBody annotation.