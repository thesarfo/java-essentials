package abdul_bari;

class LocalOuter{
    public void display(){
        class Inner{
            public void show(){
                System.out.println("Hello");
            }
        }
        Inner i = new Inner();
        i.show();
    }
}

public class LocalInnerClass {
    public static void main(String[] args) {
        LocalOuter o = new LocalOuter();
        o.display();
    }
}
