package edu.miu.cs489;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import edu.miu.cs489.model.Employee;
import edu.miu.cs489.model.PensionPlan;

import java.time.LocalDate;
import java.util.*;

public class EmployeePensionApp {

    private static List<Employee> employees = new ArrayList<>();

    public static void main(String[] args) {
        initializeEmployees();


        System.out.println("employees");
        printAllEmployeesJson();
        System.out.println("Employees on a monthly basis");
        printUpcomingEnrolleesJson();
    }

    private static void initializeEmployees() {

        employees.add(new Employee(1, "Daniel", "Agar", LocalDate.of(2018, 1, 17), 105945.50));
        employees.add(new Employee(2, "Benard", "Shaw", LocalDate.of(2018, 10, 3), 197750.00));
        employees.add(new Employee(3, "Carly", "Agar", LocalDate.of(2014, 5, 16), 842000.75));
        employees.add(new Employee(4, "Wesley", "Schneider", LocalDate.of(2018, 11, 2), 74500.00));


        employees.get(0).setPensionPlan(new PensionPlan(1, LocalDate.of(2023, 1, 17), 100.00));
        employees.get(2).setPensionPlan(new PensionPlan(3, LocalDate.of(2019, 11, 4), 1555.50));
    }

    public static void printAllEmployeesJson() {
        employees.sort(new Comparator<Employee>() {
            public int compare(Employee e1, Employee e2) {
                int lastNameComp = e1.getLastName().compareTo(e2.getLastName());
                if (lastNameComp != 0) return lastNameComp;
                return Double.compare(e2.getYearlySalary(), e1.getYearlySalary()); // For descending order
            }
        });

        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.registerModule(new JavaTimeModule());
            String json = mapper.writeValueAsString(employees);
            System.out.println(json);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void printUpcomingEnrolleesJson() {
        LocalDate nextMonth = LocalDate.now().plusMonths(1);
        ArrayList<Employee> filteredEmployees = new ArrayList<>();
        for (Employee employee : employees) {
            LocalDate employmentDate = employee.getEmploymentDate();
            if (employee.getPensionPlan() == null && employmentDate.plusYears(5).isBefore(nextMonth)) {
                filteredEmployees.add(employee);
            }
        }

        filteredEmployees.sort(Comparator.comparing(Employee::getEmploymentDate));

        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.registerModule(new JavaTimeModule());
            String json = mapper.writeValueAsString(filteredEmployees);
            System.out.println(json);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}