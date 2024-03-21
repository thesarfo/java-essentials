package programs;

abstract class Shape{
    abstract double calculateArea();
    abstract double calculatePerimeter();
}


class Circle extends Shape {
    private double radius;

    public Circle(double radius) {
        this.radius = radius;
    }

    @Override
    double calculateArea() {
        return Math.PI * radius * radius;
    }

    @Override
    double calculatePerimeter() {
        return 2 * Math.PI * radius;
    }
}

public class Twitch {
    public static void main(String[] args) {
        Circle c = new Circle(8);

        System.out.println("Area of a circle: " + c.calculateArea());
        System.out.println("Perimeter of a circle: " + c.calculatePerimeter());
    }
}
