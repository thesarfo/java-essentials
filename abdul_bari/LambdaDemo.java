package abdul_bari;


interface MyLambda{
    public void display(String str);
}


public class LambdaDemo {

    public static void reverse(String str){
        StringBuffer sb = new StringBuffer(str);
        sb.reverse();
        System.out.println(sb);
    }
    public static void main(String[] args){
        MyLambda mlb = System.out::println;
        MyLambda ml = LambdaDemo::reverse;
        ml.display("Hello");
    }
}
