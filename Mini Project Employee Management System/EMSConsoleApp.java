import java.io.*;
import java.util.*;

interface Payable {
    double calculatePay();
}

interface ReportGenerator {
    void generateReport();
}

class InvalidSalaryException extends Exception {
    public InvalidSalaryException(String message) {
        super(message);
    }
}

class Department {
    String name;

    Department(String name) {
        this.name = name;
    }
}

class Employee implements Payable, ReportGenerator {
    int id;
    String name;
    double salary;
    Department dept;

    Employee(int id, String name, double salary, Department dept) throws InvalidSalaryException {
        if (salary < 0) throw new InvalidSalaryException("Salary cannot be negative");
        this.id = id;
        this.name = name;
        this.salary = salary;
        this.dept = dept;
    }

    public double calculatePay() {
        return salary;
    }

    public void generateReport() {
        System.out.println("Employee Report: " + name + ", Dept: " + dept.name + ", Pay: " + salary);
    }

    public String toString() {
        return id + "," + name + "," + salary + "," + dept.name;
    }
}

class Manager extends Employee {
    public Manager(int id, String name, double salary, Department dept) throws InvalidSalaryException {
        super(id, name, salary + 5000, dept); // bonus
    }
}

class Developer extends Employee {
    public Developer(int id, String name, double salary, Department dept) throws InvalidSalaryException {
        super(id, name, salary + 2000, dept); // bonus
    }
}

class Intern extends Employee {
    public Intern(int id, String name, double salary, Department dept) throws InvalidSalaryException {
        super(id, name, salary, dept);
    }
}

public class EMSConsoleApp {
    static List<Employee> employees = new ArrayList<>();
    static final String FILE_NAME = "employees.txt";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        loadEmployees();

        while (true) {
            System.out.println("\n1. Add Employee\n2. View Employees\n3. Save & Exit");
            int choice = sc.nextInt();
            sc.nextLine();

            if (choice == 1) {
                try {
                    System.out.print("ID: ");
                    int id = sc.nextInt(); sc.nextLine();
                    System.out.print("Name: ");
                    String name = sc.nextLine();
                    System.out.print("Salary: ");
                    double salary = sc.nextDouble(); sc.nextLine();
                    System.out.print("Department: ");
                    String deptName = sc.nextLine();
                    System.out.print("Type (Manager/Developer/Intern): ");
                    String type = sc.nextLine();

                    Department dept = new Department(deptName);
                    Employee emp = switch (type.toLowerCase()) {
                        case "manager" -> new Manager(id, name, salary, dept);
                        case "developer" -> new Developer(id, name, salary, dept);
                        case "intern" -> new Intern(id, name, salary, dept);
                        default -> throw new IllegalArgumentException("Invalid type.");
                    };

                    employees.add(emp);
                    System.out.println("Employee added.");
                } catch (Exception e) {
                    System.out.println("Error: " + e.getMessage());
                }
            } else if (choice == 2) {
                for (Employee emp : employees) emp.generateReport();
            } else if (choice == 3) {
                saveEmployees();
                System.out.println("Saved. Exiting...");
                break;
            }
        }
    }

    static void loadEmployees() {
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                Department dept = new Department(parts[3]);
                Employee emp = new Employee(Integer.parseInt(parts[0]), parts[1], Double.parseDouble(parts[2]), dept);
                employees.add(emp);
            }
        } catch (Exception e) {
            System.out.println("No previous records found.");
        }
    }

    static void saveEmployees() {
        try (PrintWriter pw = new PrintWriter(new FileWriter(FILE_NAME))) {
            for (Employee emp : employees) {
                pw.println(emp);
            }
        } catch (IOException e) {
            System.out.println("Error saving records.");
        }
    }
}
