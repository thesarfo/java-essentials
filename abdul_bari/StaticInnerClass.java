package abdul_bari;

class StaticOuter{
    static int x = 10;
    int y = 20;

    static class Inner{
        void display(){
            System.out.println(x);
        }
    }
}

public class StaticInnerClass {
    public static void main(String[] args) {
        StaticOuter.Inner i = new StaticOuter.Inner();
        i.display();
    }
}
