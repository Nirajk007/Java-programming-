import java.util.Scanner;

class Area{
    private double length;
    private double breadth;

    public void setDim(double length,double breadth){
        this.length=length;
        this.breadth=breadth;
    }

    public double getArea(){
        return length*breadth;
    }

    
}

public class RectangleArea {
    public static void main(String[] args) {
        
        Scanner scanner= new Scanner(System.in);

        System.out.println("Enter the length of Rectangle: ");
        double length=scanner.nextDouble();

        System.out.println("Enter the breadth of Reactangle: ");
        double breadth=scanner.nextDouble();

        Area rectangle= new Area();

        rectangle.setDim(length, breadth);

        System.out.println("The Area of the rectangle is: "+ rectangle.getArea());
        scanner.close();
    }
}
