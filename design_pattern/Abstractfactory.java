// step 1: define employee interface
interface Employee {
    int salary();
}

// step 2: implement concrete employee classes
class Employee1 implements Employee {
    public int salary() {
        return 5000;
    }
}

class Employee2 implements Employee {
    public int salary() {
        return 10000;
    }
}

// step 3: create abstract factory interface
interface EmployeeAbstractFactory {
    Employee createEmployee();
}

// step 4: implement concrete factories for each employee type
class Employee1Factory implements EmployeeAbstractFactory {
    public Employee createEmployee() {
        return new Employee1();
    }
}

class Employee2Factory implements EmployeeAbstractFactory {
    public Employee createEmployee() {
        return new Employee2();
    }
}

// step 5: create factory producer to get factory
class FactoryProducer {
    public static EmployeeAbstractFactory getFactory(String empType) {
        if (empType.trim().equalsIgnoreCase("EMPLOYEE1")) {
            return new Employee1Factory();
        } else if (empType.trim().equalsIgnoreCase("EMPLOYEE2")) {
            return new Employee2Factory();
        }
        return null;
    }
}

// step 6: user code
public class Abstractfactory {
    public static void main(String[] args) {
        // Get the factory for EMPLOYEE1
        EmployeeAbstractFactory employeeFactory = FactoryProducer.getFactory("EMPLOYEE1");
        Employee employee = employeeFactory.createEmployee();
        
        System.out.println(employee);
        System.out.println("Salary: " + employee.salary());

        // Get the factory for EMPLOYEE2
        EmployeeAbstractFactory employeeFactory2 = FactoryProducer.getFactory("EMPLOYEE2");
        Employee employee2 = employeeFactory2.createEmployee();

        System.out.println(employee2);
        System.out.println("Salary: " + employee2.salary());
    }
}