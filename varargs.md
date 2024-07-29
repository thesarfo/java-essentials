The idea behind varargs is that we can basically pass a "varying number of arguments" to a method. Assume the below code

```java
public class Test{
    public static void main(String[] args){
        m1(); // method call without args
        m1(1); // method call with 1 arg
        m1(1, 2, 3, 4); // method call with multiple args
    }

    static void m1(int... a){
        out.println(a);
    }
}
```

The varargs that you pass to the method are actually processed as an array. So in the case of multiple arguments, we can access an argument with array indexing.

```java
static void m1(int... a){
    out.println(a[0]); // calling this method will print the first arg. it will return an array index out of bounds if you call the method without args
}
```

All array access stuff like for-loops etc apply to varargs. 

Varargs can be given to methods with multiple parameters as well. See below
```java
static void m2(String x, int... y){
    out.println(x); // prints the first string arg you call m2 with
    out.println(y.length); // prints the length of the remaining integers, it may change based on the args you call m2 with
}

m2("abc", 1, 2, 3, 4, 5); // prints "abc" and "5"
```

An array can also be passed as a vararg, as shown be below. But note that since normal varargs are processed as arrays, passing an array as a vararg would actually make the array be processed as a two-dimensional array aka a matrix.
```java
static void m3(int[]... x){
    for(int[] a : x){ // normal looping through a matrix
        for(int b: a){
            //
        }
    }
}
```