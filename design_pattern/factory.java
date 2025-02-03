public class factory {
    public static void main(String[] args) {
        Employee employee = EmployeeFactory.getEmployee("EMPLOYEE1");
        System.out.println(employee);
        System.out.println(employee.salary());
    }
}

class EmployeeFactory {
    public static Employee getEmployee(String empType) {
        if (empType.trim().equalsIgnoreCase("EMPLOYEE1")) {
            return new Employee1();
        }
        if (empType.trim().equalsIgnoreCase("EMPLOYEE2")) {
            return new Employee2();
        } else {
            return null;
        }
    }
}

interface Employee {
    int salary();
}

class Employee1 implements Employee {
    public int salary() {
        return 5000;
    }
}

class Employee2 implements Employee {
    public int salary() {
        return 50000;
    }
}