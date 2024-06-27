package dev.thesarfo;

import dev.thesarfo.beans.Cat;
import dev.thesarfo.beans.Owner;
import dev.thesarfo.config.ProjectConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        try (AnnotationConfigApplicationContext c = new AnnotationConfigApplicationContext(ProjectConfig.class)){
//            Cat x = c.getBean(Cat.class);
            Owner o = c.getBean(Owner.class);

//            x.setName("Leo");
//            System.out.println(x);
            System.out.println(o);
        }
    }
}
