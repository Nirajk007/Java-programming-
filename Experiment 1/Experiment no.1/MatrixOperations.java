import java.util.Scanner;
public class MatrixOperations {
    public static void main(String[]args){
        Scanner sc=new Scanner(System.in);
        int [][] A= new int [2][2];
        int [][] B= new int [2][2];
        System.out.println("Enter the elements of first 2*2 Matrix:");
          for(int i=0;i<2;i++){
           for(int j=0;j<2;j++){
               A[i][j]=sc.nextInt();
           }
          }
    }
}
