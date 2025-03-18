import java.util.Stack;
import java.util.LinkedList;
import java.util.Queue;
public class StackQueueDemo {
    
    public static void main(String[] args) {
        Stack<Integer>stack= new Stack<>();
          System.out.println("Stack Operations:");
           stack.push(10);
           stack.push(20);
           stack.push(30);
           System.out.println("Stack After Pushing:"+stack);
           System.out.println("Popped Element:"+stack.pop());
           System.out.println("Top Element(peek):"+stack.peek());
           System.out.println("Stack after popping:"+stack);

           Queue<Integer>queue=new LinkedList<>();
           System.out.println("\nQueue Operations:");
           queue.offer(10);
           queue.offer(20); 
           queue.offer(30);
           System.out.println("Queue After Enqueueing:"+queue);
           System.out.println("Dequeued Element:"+queue.poll());
           System.out.println("Front Element (peek):"+queue.peek());
           System.out.println("Queue after Dequeuing:"+queue);
    }
}
