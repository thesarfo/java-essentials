package abdul_bari;

abstract class Shape{
    abstract public double perimeter();
    abstract public double area();
}

class Circle extends Shape{
    double radius;
    @Override
    public double perimeter(){
        return 2 * Math.PI * radius;
    }
    @Override
    public double area(){
        return Math.PI * radius * radius;
    }
    double radius(){
        System.out.println("Radius of a circle");
        return 0;
    }
}

class Rectangle extends Shape{
    double length;
    double breadth;

    @Override
    public double perimeter(){
        return 2*(length + breadth);
    }

    public double area(){
        return length * breadth;
    }

}

public class AbstractExercise {
    public static void main(String[] args) {
        Rectangle r = new Rectangle();
        r.breadth = 5;
        r.length = 10;
        System.out.println(r.area());
    }
}
