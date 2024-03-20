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


### Do's and dont's of Interfaces.

#### Rules
1. Inside an interface, by default all methods are public and abstract. So whether you write 'public' or 'abstract' doesn't matter.
2. You cannot make the methods private, because they are going to be implemented by classes.
3. You can have an identifier(variable) in an interface, but the name of such identifier has to be in CAPS, so that we can recognize them as identifiers from interfaces.
4. These identifiers are static and final(constant) by default. so they cannot be changed. So whether you add the 'final' or 'static' keyword doesn't matter.
5. A method in an interface, cannot have a body because they are abstract. But if you specify the method as static, they can have a body.
6. An interface can extend from another interface.
7. An interface can have a "default" method. A default method unlike the abstract methods in an interface, have a body. So any class extending that interface can choose to override that default method or not. If the default method is not overridden, whatever is in the original interface will apply to the child class.
8. You can also have a private method in an interface. A private method can only be used by the default method in the interface. Such private methods cannot be used outside the interface.




#### Why we use default methods.

Normally, every class that implements an interface becomes an abstract class, unless they override all the methods of the interface. This means that, when we add a new method to the original interface, all the classes implementing the interface will become static. That means that when we define an interface, we cannot come back and change it without it affecting the child classes(since they will have to override the new method). So what if we wanna make a change to the interface without affecting the child classes. That is where a default method comes in. We can add a default method to an interface, and all child classes can either override them or not, it doesn't matter either way. What matters is that the child classes will have access to the default method, and they won't become abstract.