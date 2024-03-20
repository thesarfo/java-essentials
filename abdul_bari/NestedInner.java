package abdul_bari;


class OuterNew{
    int x = 10;
    InnerNew i = new InnerNew();

    class InnerNew{
        int y = 20;
        public void innerDisplay(){
            System.out.println(x + " " + y);
        }
    }
    public void outerDisplay(){
        i.innerDisplay();
        System.out.println(i.y);
    }
}
public class NestedInner {
    public static void main(String[] args) {
        OuterNew o = new OuterNew();
        o.outerDisplay();
        OuterNew.InnerNew oi = new OuterNew().new InnerNew();
        oi.innerDisplay();
    }
}
