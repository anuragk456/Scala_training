import java.util.Scanner;

class Tuple {
    int sno;
    String name;
    String city;

    public Tuple(int sno, String name, String city) {
        this.sno = sno;
        this.name = name;
        this.city = city;
    }

    @Override
    public String toString() {
        return "(" + sno + "," + name + "," + city + ")";
    }
}

class DepartmentNode {
    String departmentName;
    DepartmentNode[] subDepartments;
    Tuple[] employees;
    int employeeCount;
    int subDeptCount;

    public DepartmentNode(String departmentName) {
        this.departmentName = departmentName;
        this.employees = new Tuple[10];
        this.subDepartments = new DepartmentNode[10];
        this.employeeCount = 0;
        this.subDeptCount = 0;
    }

    public void addEmployee(int sno, String name, String city) {
        if (employeeCount < employees.length) {
            employees[employeeCount++] = new Tuple(sno, name, city);
        } else {
            System.out.println("Employee capacity reached for " + departmentName);
        }
    }

    public void addSubDepartment(String departmentName) {
        if (subDeptCount < subDepartments.length) {
            subDepartments[subDeptCount++] = new DepartmentNode(departmentName);
        } else {
            System.out.println("Sub-department capacity reached for " + departmentName);
        }
    }

    public void printTree(String indent) {
        System.out.println(indent + departmentName);

        for (int i = 0; i < employeeCount; i++) {
            System.out.println(indent + "    " + employees[i]);
        }

        for (int i = 0; i < subDeptCount; i++) {
            subDepartments[i].printTree(indent + "    ");
        }
    }
}



public class OrganizationTree {
    private DepartmentNode root;

    public OrganizationTree() {
        root = new DepartmentNode("Organization");
    }

    public void addEmployee(String departmentPath, int sno, String name, String city) {
        DepartmentNode currentNode = root;
        String[] departments = departmentPath.split("/");

        for (String dept : departments) {
            boolean deptFound = false;
            for (int i = 0; i < currentNode.subDeptCount; i++) {
                if (currentNode.subDepartments[i].departmentName.equals(dept)) {
                    currentNode = currentNode.subDepartments[i];
                    deptFound = true;
                    break;
                }
            }
            if (!deptFound) {
                System.out.println("Department " + dept + " not found.");
                return;
            }
        }

        currentNode.addEmployee(sno, name, city);
    }

    public void addSubDepartment(String parentDeptPath, String subDeptName) {
        DepartmentNode currentNode = root;
        String[] departments = parentDeptPath.split("/");

        for (String dept : departments) {
            boolean deptFound = false;
            for (int i = 0; i < currentNode.subDeptCount; i++) {
                if (currentNode.subDepartments[i].departmentName.equals(dept)) {
                    currentNode = currentNode.subDepartments[i];
                    deptFound = true;
                    break;
                }
            }
            if (!deptFound) {
                System.out.println("Department " + dept + " not found.");
                return;
            }
        }

        currentNode.addSubDepartment(subDeptName);
    }

    public void printOrganizationTree() {
        root.printTree("");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        OrganizationTree organization = new OrganizationTree();
        
        System.out.println("Welcome to the Organization Management System!");
        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Add employee");
            System.out.println("2. Add sub-department");
            System.out.println("3. Print organization tree");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter department path (e.g., Finance/Payments): ");
                    String deptPath = scanner.nextLine();
                    System.out.print("Enter sno: ");
                    int sno = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter city: ");
                    String city = scanner.nextLine();

                    organization.addEmployee(deptPath, sno, name, city);
                    break;

                case 2:
                    System.out.print("Enter parent department path (e.g., Finance): ");
                    String parentDeptPath = scanner.nextLine();
                    System.out.print("Enter sub-department name: ");
                    String subDeptName = scanner.nextLine();
                    
                    organization.addSubDepartment(parentDeptPath, subDeptName);
                    break;

                case 3:
                    organization.printOrganizationTree();
                    break;

                case 4:
                    System.out.println("Exiting the system...");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid option, please try again.");
            }
        }
    }
}
