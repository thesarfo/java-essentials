package head_first_java;


class GoodDog{
    private int size;

    public int getSize(){
        return size;
    }
    public void setSize(int s) {
        this.size = s;
    }
    void bark(){
        if (size > 60){
            System.out.println("Woof Woof");
        } else if (size > 14){
            System.out.println("Ruff Ruff");
        } else {
            System.out.println("Yip Yip");
        }
    }
}
public class GoodDogTestDrive {
    public static void main(String[] args){
        // create new objects of the above class
        GoodDog one = new GoodDog();
        one.setSize(79); // use the setter
        GoodDog two = new GoodDog();
        two.setSize(8);

        System.out.println("Dog one: " + one.getSize()); // use the getter
        System.out.println("Dog two: " + one.getSize());

        one.bark();
        two.bark();
    }
}