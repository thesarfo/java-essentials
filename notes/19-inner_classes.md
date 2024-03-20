An inner class is simply a class inside another class.

1. Nested inner class
An inner class can access the contents of the Outer class. You can use the inner class by creating its objects in the outer class directly. But the outer class cannot access the contents of inner class directly, unless it has created an object of the inner class. Refer to the 'NestedInner' and 'NestedInnerClass' files in the abdul_bari folder.


2. Local inner class
This is a class we define inside a method of the outer class, and that class is only useful inside the method. If you want any class to inherit from another class, or you want a class to implement an interface, and that class is only useful in the method, you define a local inner class.


3. Anonymous inner class
This is a class that is defined during the definition of an object. If you have to implement an interface and it's usage is limited, you dont have to write a separate class, and create its object...you can use an anonymous inner class.

Refer to the 'AnonymousClass' file in the abdul_bari folder.


4. Static inner class
They are the static members of outer classes. Objects of the static inner class can be directly created in the outer class without creating an object of the class.