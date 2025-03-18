import java.util.Arrays;
import java.util.Scanner;

public class SortNumbers
{
    public static void main(String[]args)
    {
        int[] numbers=new int[10];
         Scanner scanner=new Scanner(System.in);
         System.out.println("Enter 10 numbers:");
          for(int i=0;i<10;i++)
          {
            numbers[i]=scanner.nextInt();
          }
          scanner.close();

          Arrays.sort(numbers);
          System.out.println("Numbers is ascending order:");
          {
            for(int num:numbers)
            {
                System.out.println(num+" ");
            }
            System.out.println();
          }
    }
}