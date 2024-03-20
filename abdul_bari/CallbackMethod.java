package abdul_bari;


interface IMember{
    public void callback();
}

class Store{
    IMember members[] = new IMember[100];
    int count = 0;

    Store(){

    }

    public void register(IMember m){
        members[count++] = m;
    }

    public void inviteSale(){
        for (int i = 0; i < count; i++){
            members[i].callback();
        }
    }
}

class Customer implements IMember{
    String name;
    Customer(String n){
        name = n;
    }
    @Override
    public void callback(){
        System.out.println("Okay, I will visit " + name);
    }
}

public class CallbackMethod {
    public static void main(String[] args) {
        Store s = new Store();
        Customer c1 = new Customer("John");
        Customer c2 = new Customer("Smith");
        s.register(c1);
        s.register(c2);
        s.inviteSale();
    }
}
