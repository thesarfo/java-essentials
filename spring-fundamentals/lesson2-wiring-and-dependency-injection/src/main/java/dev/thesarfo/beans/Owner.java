package dev.thesarfo.beans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class Owner {

//    @Autowired
    @Qualifier("cat2") // tell the context that this is the bean we wanna use
    private Cat cat;


    // constructor injection
//    @Autowired
//    public Owner(Cat cat){
//        this.cat = cat;
//    }

    public Cat getCat() {
        return cat;
    }


    public void setCat(Cat cat) {
        this.cat = cat;
    }

    @Override
    public String toString() {
        return "Owner{" +
                "cat=" + cat +
                '}';
    }
}
