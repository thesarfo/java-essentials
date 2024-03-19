package abdul_bari;

abstract class Test1{
    abstract public void meth1();
    abstract public void meth2();
}

class Test2 extends Test1{
    @Override
    public void meth1(){

    }
    @Override
    public void meth2(){
    }

}

interface TestOne{
    void methOne();
    void methTwo();
}

class TestTwo implements TestOne{
    public void methOne(){

    }

    public void methTwo(){

    }

}

public class AbstractVsInterface {
    public static void main(String[] args) {
        Test1 t = new Test2();
        TestOne tee = new TestTwo();
    }
}
