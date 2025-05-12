import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;

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

class Department implements Serializable {
    String name;
    Department(String name) {
        this.name = name;
    }
}

class Employee implements Payable, ReportGenerator, Serializable {
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
        System.out.println("Employee: " + name + " | Salary: " + salary);
    }
}

class Manager extends Employee {
    public Manager(int id, String name, double salary, Department dept) throws InvalidSalaryException {
        super(id, name, salary + 5000, dept);
    }
}

class Developer extends Employee {
    public Developer(int id, String name, double salary, Department dept) throws InvalidSalaryException {
        super(id, name, salary + 2000, dept);
    }
}

class Intern extends Employee {
    public Intern(int id, String name, double salary, Department dept) throws InvalidSalaryException {
        super(id, name, salary, dept);
    }
}

public class EmployeeManagementGUI extends JFrame {
    private ArrayList<Employee> employees;
    private JTable table;
    private DefaultTableModel model;
    private final String FILE_NAME = "employees.dat";

    public EmployeeManagementGUI() {
        employees = loadEmployees();

        setTitle("Employee Management System");
        setSize(700, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        model = new DefaultTableModel(new String[]{"ID", "Name", "Salary", "Department"}, 0);
        table = new JTable(model);
        refreshTable();

        JScrollPane scrollPane = new JScrollPane(table);

        JButton addBtn = new JButton("Add");
        JButton deleteBtn = new JButton("Delete");

        addBtn.addActionListener(e -> addEmployeeDialog());
        deleteBtn.addActionListener(e -> deleteEmployee());

        JPanel panel = new JPanel();
        panel.add(addBtn);
        panel.add(deleteBtn);

        add(scrollPane, BorderLayout.CENTER);
        add(panel, BorderLayout.SOUTH);
    }

    private void addEmployeeDialog() {
        JTextField idField = new JTextField(5);
        JTextField nameField = new JTextField(10);
        JTextField salaryField = new JTextField(7);
        JTextField deptField = new JTextField(10);
        JComboBox<String> typeBox = new JComboBox<>(new String[]{"Manager", "Developer", "Intern"});

        JPanel myPanel = new JPanel(new GridLayout(0, 1));
        myPanel.add(new JLabel("ID:"));
        myPanel.add(idField);
        myPanel.add(new JLabel("Name:"));
        myPanel.add(nameField);
        myPanel.add(new JLabel("Salary:"));
        myPanel.add(salaryField);
        myPanel.add(new JLabel("Department:"));
        myPanel.add(deptField);
        myPanel.add(new JLabel("Type:"));
        myPanel.add(typeBox);

        int result = JOptionPane.showConfirmDialog(null, myPanel,
                "Add New Employee", JOptionPane.OK_CANCEL_OPTION);

        if (result == JOptionPane.OK_OPTION) {
            try {
                int id = Integer.parseInt(idField.getText());
                String name = nameField.getText();
                double salary = Double.parseDouble(salaryField.getText());
                String dept = deptField.getText();
                String type = (String) typeBox.getSelectedItem();

                Department department = new Department(dept);
                Employee emp = switch (type) {
                    case "Manager" -> new Manager(id, name, salary, department);
                    case "Developer" -> new Developer(id, name, salary, department);
                    case "Intern" -> new Intern(id, name, salary, department);
                    default -> null;
                };

                employees.add(emp);
                saveEmployees();
                refreshTable();
                JOptionPane.showMessageDialog(this, "Employee added.");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Please enter valid numeric values.");
            } catch (InvalidSalaryException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage());
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
            }
        }
    }

    private void deleteEmployee() {
        int row = table.getSelectedRow();
        if (row >= 0) {
            employees.remove(row);
            saveEmployees();
            refreshTable();
            JOptionPane.showMessageDialog(this, "Employee deleted.");
        } else {
            JOptionPane.showMessageDialog(this, "Please select a row to delete.");
        }
    }

    private void refreshTable() {
        model.setRowCount(0);
        for (Employee emp : employees) {
            model.addRow(new Object[]{emp.id, emp.name, emp.salary, emp.dept.name});
        }
    }

    private void saveEmployees() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            out.writeObject(employees);
        } catch (IOException e) {
            System.out.println("Error saving: " + e.getMessage());
        }
    }

    private ArrayList<Employee> loadEmployees() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            return (ArrayList<Employee>) in.readObject();
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new EmployeeManagementGUI().setVisible(true));
    }
}

