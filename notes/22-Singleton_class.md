### Singleton class
This is a class that which can create only a single object. You cannot create more than one object from the class. If you need multiple objects, just use the one object in multiple places.

```java
class CoffeeMachine{
    private float coffeeQty;
    private float milkQty;
    private float waterQty;
    private float sugarQty;
    
    static private CoffeeMachine my = null; // made it static because it is gonna be accessed by the getInstance() method below, which is also static
    
    private CoffeeMachine(){ // made the constructor private so you cant create object of the CoffeeMachine
        coffeeQty = 1;
        milkQty = 1;
        waterQty = 1;
        sugarQty = 1;
    }
    
    public void fillWater(float qty){
        waterQty = qty;
    }
    public void fillSugar(float qty){
        sugarQty = qty;
    }
    public void getCoffee(){
        return 0.23f;
    }
    
    static public CoffeeMachine getInstance(){ // this method will create an instance of the CoffeeMachine and return the instance
        if (my == null){
            my = new CoffeeMachine();
        }
        return our;
    }
    
//    CoffeeMachine c = CoffeeMachine.getInstance(); // create a CoffeeMachine Object
    
    public class Singleton{
        public static void main(String[] args){
            CoffeeMachine m1 = CoffeeMachine.getInstance();
            CoffeeMachine m2 = CoffeeMachine.getInstance();
            CoffeeMachine m3 = CoffeeMachine.getInstance();

            System.out.println(m1=" " + m2=" " + m3);
            if (m1 == m2 && m1 == m3){
                System.out.println("Same");
            }
        }
    }
    
}
```