public class PrimeCheck {
    public static void main(String[] args) {
        // Check if a number is provided as an argument
        if (args.length == 0) {
            System.out.println("Please provide a number as a command-line argument.");
            return;
        }

        try {
            int num = Integer.parseInt(args[0]); // Convert argument to integer

            if (num <= 1) {
                System.out.println(num + " is not a prime number.");
                return;
            }

            boolean isPrime = true;

            for (int i = 2; i <= Math.sqrt(num); i++) { // Check divisibility up to sqrt(num)
                if (num % i == 0) {
                    isPrime = false;
                    break;
                }
            }

            if (isPrime)
                System.out.println(num + " is a prime number.");
            else
                System.out.println(num + " is not a prime number.");

        } catch (NumberFormatException e) {
            System.out.println("Invalid input! Please enter a valid integer.");
        }
    }
}