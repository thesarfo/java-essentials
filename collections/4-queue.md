### Key Points about the Queue interface

1. **FIFO**: The Queue follows the First-In-First-Out algorithm. It means that the elements get added at the end (rear) and are removed from the beginning (front) of the Queue.

2. **Insertion and Removal Operations**: The Queue interface supports all basic operations such as insert, remove, and examine. For insertion, we can use the `add(E e)` or `offer(E e)`methods. For removing an element, `remove()` and `poll()` are used. To examine or retrieve an element, `element()` and `peek()` are available. 

3. **Exception throwing and Non-Exception throwing methods**: Each operation in a queue has two methods, one that throws an exception when the operation fails and the other returns a special value (null or false, depending on the operation). For example, `add(E e)` throws an `IllegalStateException` if no space is currently available and `offer(E e)` simply returns false in the same scenario. 

The below diagram shows a list of methods the Queue interface provides
<img src="/images/queue.png">

### Queue interface with its LinkedList Implementation Class
```java
public class QueueExample {
    public static void main(String[] args) {
        // Create and initialize a Queue using a LinkedList
        Queue<String> elementQueue = new LinkedList<>();

        // Adding new elements to the Queue (The Enqueue operation)
        elementQueue.add("element1");
        elementQueue.add("element2");
        elementQueue.add("element3");
        elementQueue.add("element4");

        System.out.println("WaitingQueue : " + elementQueue);

        // Removing an element from the Queue using remove() (The Dequeue operation)
        // The remove() method throws NoSuchElementException if the Queue is empty
        String name = elementQueue.remove();
        System.out.println("Removed from WaitingQueue : " + name + " | New WaitingQueue : " + elementQueue);

        // Removing an element from the Queue using poll()
        // The poll() method is similar to remove() except that it returns null if the Queue is empty.
        name = elementQueue.poll();
        System.out.println("Removed from WaitingQueue : " + name + " | New WaitingQueue : " + elementQueue);
    }
```

#### More Queue Methods
```java
public class QueueSizeSearchFrontExample {
    public static void main(String[] args) {
        Queue<String> elementQueue = new LinkedList<>();

        elementQueue.add("element1");
        elementQueue.add("element2");
        elementQueue.add("element3");
        elementQueue.add("element4");

        System.out.println("WaitingQueue : " + elementQueue);

        // Check is a Queue is empty
        System.out.println("is waitingQueue empty? : " + elementQueue.isEmpty());

        // Find the size of the Queue
        System.out.println("Size of waitingQueue : " + elementQueue.size());

        // Check if the Queue contains an element
        String name = "Johnny";
        if(elementQueue.contains(name)) {
            System.out.println("WaitingQueue contains " + name);
        } else {
            System.out.println("Waiting Queue doesn't contain " + name);
        }

        // Get the element at the front of the Queue without removing it using element()
        // The element() method throws NoSuchElementException if the Queue is empty
        String firstElementInTheWaitingQueue =  elementQueue.element();
        System.out.println("Waiting Queue (element()) : " + firstElementInTheWaitingQueue);

        // Get the element at the front of the Queue without removing it using peek()
        // The peek() method is similar to element() except that it returns null if the Queue is empty
        firstElementInTheWaitingQueue = elementQueue.peek();
        System.out.println("Waiting Queue : " + firstElementInTheWaitingQueue);

    }
}
```