package dev.thesarfo;

import dev.thesarfo.config.ProjectConfig;
import dev.thesarfo.service.HelloService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        try(AnnotationConfigApplicationContext c = new AnnotationConfigApplicationContext(ProjectConfig.class)){
            HelloService service = c.getBean(HelloService.class);
            String message = service.hello("Ernest");
            System.out.println("Result is " + message);
        }
    }
}
