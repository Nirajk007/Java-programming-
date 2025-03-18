public class Employee{
    private String firstName;
    private String lastName;
    private double monthlySalary;

    public Employee(String firstName,String lastName,double monthlySalary){
        this.firstName=firstName;
        this.lastName=lastName;

        setMonthlySalary(monthlySalary);
    }

    public void setFirstName(String firstName){
        this.firstName=firstName;
    }
    public String geFirstName(){
        return firstName;
    }

    public void setlastName(String lastName){
        this.lastName=lastName;
    }

    public String getLaStName(){
        return lastName;
    }

    public void setMonthlySalary(double monthlySalary){
          if(monthlySalary>0){
            this.monthlySalary=monthlySalary;
          }
          else{
            this.monthlySalary=0.0;
          }
    }

    public double getMonthlySalary(){
        return monthlySalary;
    }
    
    public double getYearlySalary(){
        return monthlySalary*12;
    }

    public void giveRaise(){
        monthlySalary *=1.10;
    }

}