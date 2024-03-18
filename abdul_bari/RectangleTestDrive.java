package abdul_bari;


class Rectangle {
    public double length;
    public double breadth;

    public double area(){
        return length * breadth;
    }

    public double perimeter(){
        return 2 * (length + breadth);
    }

    public boolean isSquare(){
        return length == breadth;
    }
}
public class RectangleTestDrive {
    public static void main(String[] args) {
        Rectangle r1 = new Rectangle();
        r1.length = 6;
        r1.breadth = 5;

        r1.area();
        r1.perimeter();

        System.out.println("Area1: " + r1.area());
        System.out.println("Perimeter1: " + r1.perimeter());
        System.out.println("It is a square: " + r1.isSquare());
    }
}
