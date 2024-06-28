package dev.thesarfo.config;


import dev.thesarfo.beans.Cat;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
@ComponentScan(basePackages = "dev.thesarfo.beans")
public class ProjectConfig {

//    @Bean
//    public Cat cat(){
//        Cat c = new Cat();
//        c.setName("Tom");
//        return c;
//    }
//
//    @Bean
//    public Owner owner(Cat cat){
//        Owner o = new Owner();
//        o.setCat(cat);
//        return o;
//    }

    @Bean
    @Qualifier("cat1") // this is the name of the bean that will be stored in the context
    public Cat cat1(){
        Cat c = new Cat();
        c.setName("Tom");
        return c;
    }

    @Bean
    @Qualifier("cat2")
    //@Primary // mark this as the primary bean that will be used in the context
    public Cat cat2(){
        Cat c = new Cat();
        c.setName("Leo");
        return c;
    }
}
