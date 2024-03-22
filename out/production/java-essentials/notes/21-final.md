## Final keyword
There are three implementations of the final keyword

1. Final variable
2. Final method
3. Final class

### Final variable
They are constants, it means their values are fixed and cannot be modified.
```java
class My{
    final int MIN = 1; // can be initialized and defined at a go
    final int NORMAL;
    final int MAX;
    
    static {
        NORMAL = 5; // can be initialized inside a static block
    }
    MY(){
        MAX = 10; // can be initialized inside a constructor
    }
}
```

### Final method
These methods cannot be overrode.
```java
class Super{
    final void meth1(){
        System.out.println("Hello");
    }
}

class Sub extends SUper{
    void meth1(){ // this is wrong. final methods cannot be overrode
        System.out.println("Hi");
    }
    void meth2(){
        System.out.println("Bye");
    }
}
```

### Final class
These classes don't support inheritance and hence cannot be extended. But you can create objects of that class.
```java
import head_first_java.Surgeon;

final class Super {
    // some code here
}

class Sub extends Super{ // wrong. final methods cannot be extended
    // some code here
}
```