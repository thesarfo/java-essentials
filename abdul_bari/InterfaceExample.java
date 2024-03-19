package abdul_bari;

class Phone{
    public void call() {
        System.out.println("Phone call");
    }
    public void sms(){
        System.out.println("Phone sending SMS");
    }
}

interface ICamera{
    void click();
    void record();
}

interface IMusicPlayer{
    void play();
    void stop();
}

class Smartphone extends Phone implements ICamera, IMusicPlayer{
    public void videoCall(){
        System.out.println("Smart phone video calling");
    }

    @Override
    public void click() {
        System.out.println("Smart phone clicking photo");
    }

    @Override
    public void record() {
        System.out.println("Smart phone recording video");
    }

    @Override
    public void play() {
        System.out.println("Smart phone playing music");
    }

    @Override
    public void stop(){
        System.out.println("Smart phone stopped playing music");
    }
}

public class InterfaceExample {
    public static void main(String[] args) {
        Smartphone sp = new Smartphone();
        sp.play();
        sp.click();
        sp.call();
        sp.record();

        ICamera c = new Smartphone();
        c.click();
        c.record();

        IMusicPlayer m = new Smartphone();
        m.play();
        m.stop();

        Phone p = new Smartphone();
        p.sms();
        p.call();
    }
}
