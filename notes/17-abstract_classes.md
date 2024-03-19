An abstract class is a class that we cant create an object of. We add the keyword 'abstract' when initializing it. Normally, the classes we create in our programs are concrete classes. 
An abstract class can have an abstract method. An abstract method also begins with the 'abstract' keyword, and doesn't have a body. A method without a body is almost always an abstract class. A class with an abstract method is automatically an abstract. Since the abstract method is not completely defined(lacks a body), by extension...it means that the class itself isn't completely defined, and that means that you cannot create objects of that class.

```java
abstract class Super{
    Super(){
        System.out.println("Super");
    }
    void method1 (){
        System.out.println("Super Method");
    }
    abstract void method2();
}

class Sub extends Super{
    void method2(){
        System.out.println("Sub Method 2");
    }
}
```

Even though the Sub class is inheriting from the Super class, the Sub class is not an abstract class, it is a concrete class. This is because when the Sub class inherited from the Super class, it inherited teh constructor, method1 and method2. But note that we have overridden the method2 in the Sub class, so now everything in the Sub class is fully defined. So the Sub class is not an abstract class.

If a class inherits from an abstract class, that class also becomes an abstract class. That class will become concrete if it overrides all the methods of the parent class.

### Why do we need abstract classes
Such classes are meant only for multiple inheritance and polymorphism. So you must write a subclass that inherits from the super(abstract) class, and override the super class's methods. Abstract classes are used for defining standards and imposing standards. See the AbstractHospitalExample in the abdul_bari folder.
Review the 'AbstractExample' file in the abdul_bari folder to view an implementation

#### Note
1. You cannot create an object of an abstract class, just a reference.
2. You can create an object of a concrete class that inherits from the abstract class.
3. If there's even just one method in your child class that doesn't override the parent class, then the child class becomes an abstract class
4. The @Override keyword in your child class isn't a strict requirement, but considered good practice