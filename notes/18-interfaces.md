Interfaces are strictly used to achieve polymorphism. So if you don't have anything to give to the subclass, you use an interface. An abstract class can have only abstract methods, and a child class inheriting from such abstract class will be forced to override the methods in order to use it. In a scenario like this, instead of writing an abstract class, use an interface instead. An interface can be viewed as an abstract class with all abstract methods. Refer to the 'AbstractVsInterface' file in the abdul_bari folder to view a comparison of an abstract class and an interface side by side.

In java, a class can only extend one class, but when a class is implementing an interface, it can implement multiple interfaces.

By creating an object of an interface, and calling those overridden methods on the object, we are achieving dynamic method dispatching and that is runtime polymorphism.

```java
// example of how to create an interface
interface IMusicPlayer{
    void play();
    void stop();
}
```

```java
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
```

Looking at the example above, it is the same smartphone object, but we can use it as a phone, camera, music player etc. Therefore achieving polymorphism.