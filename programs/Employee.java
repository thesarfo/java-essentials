package programs;

public class Employee {
    private int baseSalary;
    private int hourlyRate;

    public int calculateWage(int extraHours){
        return baseSalary + (hourlyRate * extraHours);
    }
    public void setBaseSalary(int baseSalary){
        if (baseSalary <= 0) throw new IllegalArgumentException("Salary can't be less than zero");
        this.baseSalary = baseSalary;
    }
    private int getBaseSalary(){
        return baseSalary;
    }

    public void setHourlyRate(int hourlyRate){
        if (hourlyRate <= 0) throw new IllegalArgumentException("Hourly Rate cannot be less than zero");
        this.hourlyRate = hourlyRate;
    }
    private int getHourlyRate(){
        return hourlyRate;
    }
}