In Java, an interface is like a contract or a blueprint for classes. It defines a set of methods that a class must implement if it wants to claim that it "implements" that interface.

Imagine you're building a house. You might have a blueprint that specifies what each room should look like and what features it should have. This blueprint doesn't build the house itself, but it tells builders how each room should be built.

Similarly, an interface in Java specifies what methods a class should have, but it doesn't provide the implementation of those methods. It's up to the classes that implement the interface to define how those methods work.

Simply put, an interface provides us with a way for us to build loosely coupled components in our programs. By putting an interface between our classes and methods.

The below is an example of a tightly coupled program.
```java
//TaxCalculator.java
public class TaxCalculator {
    private final double taxableIncome;

    public TaxCalculator(double taxableIncome) {
        this.taxableIncome = taxableIncome;
    }

    public double calculateTax(){ 
        return taxableIncome * 0.3;
    }
}
```

```java
// TaxReport.java
public class TaxReport {
    private TaxCalculator calculator;

    public TaxReport(){
        calculator = new TaxCalculator(500_000);
    }
    public void show(){
        var tax = calculator.calculateTax();
        System.out.println(tax);
    }
}
```

The TaxReport class is tightly dependent on the TaxCalculator class. Such that if we add an extra parameter to our TaxCalculator constructor, our TaxReport class will now be broken...such that we will have to reconfigure it with the extra parameter.

### Creating an Interface