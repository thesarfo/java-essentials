package programs;

public class TaxReport {
    private final TaxCalculator calculator;

    public TaxReport(TaxCalculator calculator){
        this.calculator = calculator;
    }
    public void show(){
        var tax = calculator.calculateTax();
        System.out.println(tax);
    }
}
