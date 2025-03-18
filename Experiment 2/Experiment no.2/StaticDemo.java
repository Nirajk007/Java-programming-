public class StaticDemo {
    static int count;

    static{
        count = 10;
        System.out.println("Static block is executed.Initial count:"+count);
    }

    public static void incrementCount(){
        count++;
        System.out.println("Count Incremented.Current count:"+count);
    }

    public static void main(String[] args) {
        System.out.println("Inside main method.");
        System.out.println("Initial count value:"+count);
        incrementCount();
        incrementCount();
    }
}

