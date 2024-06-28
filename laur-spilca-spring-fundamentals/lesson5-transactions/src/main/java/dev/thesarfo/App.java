package dev.thesarfo;

import dev.thesarfo.config.ProjectConfig;
import dev.thesarfo.service.ProductService;
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
            ProductService p = c.getBean(ProductService.class);
            p.addOneProduct();
        }
    }
}
