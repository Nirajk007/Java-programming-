public class EmployeeTest {
    public static void main(String[] args) {
        Employee emp1 = new Employee("Niraj","Kadane",30000);
        Employee emp2 = new Employee("Chintamani","Potdar",40000);
        System.out.println(emp1.geFirstName()+" "+ emp1.getLaStName() + "'s Yearly Salary: $" + emp1.getMonthlySalary());
        System.out.println(emp2.geFirstName()+" "+ emp2.getLaStName() + "'s Yearly Salary: $" + emp2.getMonthlySalary());

        emp1.giveRaise();
        emp2.giveRaise();

        System.out.println("\nAfter 10% raise:");
        System.out.println(emp1.geFirstName()+" "+ emp1.getLaStName() + "'s Yearly Salary: $" + emp1.getYearlySalary());
        System.out.println(emp2.geFirstName()+" "+ emp2.getLaStName() + "'s Yearly Salary: $" + emp2.getYearlySalary());
    }
}
