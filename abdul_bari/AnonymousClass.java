package abdul_bari;

abstract class My{
    abstract void display();
}

class AnonOuter {
    public void meth(){
        My m = new My(){
            // the My class has been defined and overriden so it has become a concrete class, but doesnt have a name. hence anonymous class
            public void display(){
                System.out.println("Hello");
            }
        };
        m.display();
    }
}

public class AnonymousClass {
    public static void main(String[] args) {

    }
}
