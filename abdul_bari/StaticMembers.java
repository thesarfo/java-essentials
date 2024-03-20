package abdul_bari;

class HondaCity{
    static long price = 10;
    int a, b;

    static double OnRoadPrice(String city){
        switch (city){
            case "delhi":
                return price + price * 0.1;
            case "mumbai":
                return price + price * 0.09;
        }
        return price;
    }
}

public class StaticMembers {
    public static void main(String[] args) {
        HondaCity n1 = new HondaCity();
        HondaCity h2 = new HondaCity();
    }
}
