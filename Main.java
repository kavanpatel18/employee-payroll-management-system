import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        DepartmentOperations deptOps    = new DepartmentOperations();
        EmployeeOperations   empOps     = new EmployeeOperations();
        SalaryOperations     salaryOps  = new SalaryOperations();
        PayrollOperations    payrollOps = new PayrollOperations();

        Scanner sc = new Scanner(System.in);
        int choice = -1;

        System.out.println("==============================");
        System.out.println("  PAYROLL MANAGEMENT SYSTEM   ");
        System.out.println("==============================");

        while (choice != 0) {
            System.out.println("\n--- MAIN MENU ---");
            System.out.println("1. Department Operations");
            System.out.println("2. Employee Operations");
            System.out.println("3. Salary Operations");
            System.out.println("4. Payroll Operations");
            System.out.println("5. View Full Payroll Report");
            System.out.println("0. Exit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();

            try {
                if (choice == 1) {
                    departmentMenu(sc, deptOps);
                } else if (choice == 2) {
                    employeeMenu(sc, empOps);
                } else if (choice == 3) {
                    salaryMenu(sc, salaryOps);
                } else if (choice == 4) {
                    payrollMenu(sc, payrollOps);
                } else if (choice == 5) {
                    payrollOps.printFullPayrollReport();
                } else if (choice == 0) {
                    System.out.println("Exiting...");
                } else {
                    System.out.println("Invalid choice.");
                }

            } catch (DatabaseException e) {
                System.out.println("Database Error: " + e.getMessage());
            } catch (EmployeeNotFoundException e) {
                System.out.println("Error: " + e.getMessage());
            } catch (DuplicateRecordException e) {
                System.out.println("Error: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("Unexpected Error: " + e.getMessage());
            }
        }

        DBConnection.closeConnection();
        sc.close();
    }

    static void departmentMenu(Scanner sc, DepartmentOperations deptOps)
            throws DatabaseException {

        System.out.println("\n-- DEPARTMENT MENU --");
        System.out.println("1. Add Department");
        System.out.println("2. View All Departments");
        System.out.println("3. Update Department");
        System.out.println("4. Delete Department");
        System.out.print("Choice: ");
        int ch = sc.nextInt();
        sc.nextLine(); 

        if (ch == 1) {
            System.out.print("Dept Name    : "); String name = sc.nextLine();
            System.out.print("Location     : "); String loc  = sc.nextLine();
            System.out.print("Manager Name : "); String mgr  = sc.nextLine();
            System.out.print("Budget       : "); double bud  = sc.nextDouble();

            Department d = new Department(name, loc, mgr, bud);
            deptOps.addDepartment(d);

        } else if (ch == 2) {
            ArrayList<Department> list = deptOps.getAllDepartments();
            if (list.size() == 0) {
                System.out.println("No departments found.");
            } else {
                for (int i = 0; i < list.size(); i++) {
                    System.out.println("----");
                    list.get(i).display();
                }
            }

        } else if (ch == 3) {
            System.out.print("Enter Dept ID to update: "); int id = sc.nextInt(); sc.nextLine();
            System.out.print("New Dept Name    : "); String name = sc.nextLine();
            System.out.print("New Location     : "); String loc  = sc.nextLine();
            System.out.print("New Manager Name : "); String mgr  = sc.nextLine();
            System.out.print("New Budget       : "); double bud  = sc.nextDouble();

            Department d = new Department(id, name, loc, mgr, bud);
            deptOps.updateDepartment(d);

        } else if (ch == 4) {
            System.out.print("Enter Dept ID to delete: "); int id = sc.nextInt();
            deptOps.deleteDepartment(id);
        }
    }

    static void employeeMenu(Scanner sc, EmployeeOperations empOps)
            throws DatabaseException, DuplicateRecordException, EmployeeNotFoundException {

        System.out.println("\n-- EMPLOYEE MENU --");
        System.out.println("1. Add Employee");
        System.out.println("2. View All Employees");
        System.out.println("3. Search Employee by ID");
        System.out.println("4. Update Employee");
        System.out.println("5. Delete Employee");
        System.out.print("Choice: ");
        int ch = sc.nextInt();
        sc.nextLine();

        if (ch == 1) {
            System.out.print("Name           : "); String name  = sc.nextLine();
            System.out.print("Age            : "); int age      = sc.nextInt(); sc.nextLine();
            System.out.print("Gender         : "); String gen   = sc.nextLine();
            System.out.print("Email          : "); String email = sc.nextLine();
            System.out.print("Phone          : "); String phone = sc.nextLine();
            System.out.print("Address        : "); String addr  = sc.nextLine();
            System.out.print("Designation    : "); String desig = sc.nextLine();
            System.out.print("Hire Date (YYYY-MM-DD): "); String hd = sc.nextLine();
            System.out.print("Dept ID        : "); int deptId   = sc.nextInt(); sc.nextLine();

            Employee e = new Employee(name, age, gen, email, phone, addr, desig, hd, deptId);
            empOps.addEmployee(e);

        } else if (ch == 2) {
            ArrayList<Employee> list = empOps.getAllEmployees();
            for (int i = 0; i < list.size(); i++) {
                System.out.println("----");
                list.get(i).display();
            }

        } else if (ch == 3) {
            System.out.print("Enter Employee ID: "); int id = sc.nextInt();
            Employee e = empOps.getEmployeeById(id);
            e.display();

        } else if (ch == 4) {
            System.out.print("Enter Emp ID to update: "); int id = sc.nextInt(); sc.nextLine();
            System.out.print("New Name        : "); String name  = sc.nextLine();
            System.out.print("New Age         : "); int age      = sc.nextInt(); sc.nextLine();
            System.out.print("New Gender      : "); String gen   = sc.nextLine();
            System.out.print("New Email       : "); String email = sc.nextLine();
            System.out.print("New Phone       : "); String phone = sc.nextLine();
            System.out.print("New Address     : "); String addr  = sc.nextLine();
            System.out.print("New Designation : "); String desig = sc.nextLine();
            System.out.print("New Hire Date   : "); String hd    = sc.nextLine();
            System.out.print("New Dept ID     : "); int deptId   = sc.nextInt(); sc.nextLine();

            Employee e = new Employee(id, name, age, gen, email, phone, addr, desig, hd, deptId);
            empOps.updateEmployee(e);

        } else if (ch == 5) {
            System.out.print("Enter Emp ID to delete: "); int id = sc.nextInt();
            empOps.deleteEmployee(id);
        }
    }

    static void salaryMenu(Scanner sc, SalaryOperations salaryOps)
            throws DatabaseException {

        System.out.println("\n-- SALARY MENU --");
        System.out.println("1. Add Salary");
        System.out.println("2. View Salary by Employee ID");
        System.out.println("3. View All Salaries");
        System.out.println("4. Update Salary");
        System.out.print("Choice: ");
        int ch = sc.nextInt();

        if (ch == 1) {
            System.out.print("Emp ID     : "); int empId  = sc.nextInt();
            System.out.print("Basic Pay  : "); double bp  = sc.nextDouble();
            System.out.print("HRA        : "); double hra = sc.nextDouble();
            System.out.print("DA         : "); double da  = sc.nextDouble();
            System.out.print("Bonus      : "); double bon = sc.nextDouble();
            System.out.print("Tax        : "); double tax = sc.nextDouble();
            System.out.print("Deductions : "); double ded = sc.nextDouble();

            Salary s = new Salary(empId, bp, hra, da, bon, tax, ded);
            salaryOps.addSalary(s);

        } else if (ch == 2) {
            System.out.print("Enter Emp ID: "); int id = sc.nextInt();
            Salary s = salaryOps.getSalaryByEmpId(id);
            if (s != null) s.display();

        } else if (ch == 3) {
            ArrayList<Salary> list = salaryOps.getAllSalaries();
            for (int i = 0; i < list.size(); i++) {
                System.out.println("----");
                list.get(i).display();
            }

        } else if (ch == 4) {
            System.out.print("Enter Salary ID to update: "); int sid = sc.nextInt();
            System.out.print("Emp ID     : "); int empId  = sc.nextInt();
            System.out.print("Basic Pay  : "); double bp  = sc.nextDouble();
            System.out.print("HRA        : "); double hra = sc.nextDouble();
            System.out.print("DA         : "); double da  = sc.nextDouble();
            System.out.print("Bonus      : "); double bon = sc.nextDouble();
            System.out.print("Tax        : "); double tax = sc.nextDouble();
            System.out.print("Deductions : "); double ded = sc.nextDouble();

            Salary s = new Salary(sid, empId, bp, hra, da, bon, tax, ded);
            salaryOps.updateSalary(s);
        }
    }

    static void payrollMenu(Scanner sc, PayrollOperations payrollOps)
            throws DatabaseException {

        System.out.println("\n-- PAYROLL MENU --");
        System.out.println("1. Add Payroll Record");
        System.out.println("2. View Payroll for Employee");
        System.out.println("3. Add Payroll (with Transaction)");
        System.out.print("Choice: ");
        int ch = sc.nextInt();
        sc.nextLine();

        if (ch == 1 || ch == 3) {
            System.out.print("Emp ID          : "); int empId   = sc.nextInt(); sc.nextLine();
            System.out.print("Salary ID       : "); int salId   = sc.nextInt(); sc.nextLine();
            System.out.print("Working Days    : "); int days    = sc.nextInt(); sc.nextLine();
            System.out.print("Overtime Hours  : "); int ot      = sc.nextInt(); sc.nextLine();
            System.out.print("Pay Month       : "); String mon  = sc.nextLine();
            System.out.print("Pay Year        : "); int year    = sc.nextInt(); sc.nextLine();
            System.out.print("Net Salary      : "); double net  = sc.nextDouble(); sc.nextLine();
            System.out.print("Payment Method  : "); String meth = sc.nextLine();
            System.out.print("Payment Date (YYYY-MM-DD): "); String pd = sc.nextLine();

            Payroll p = new Payroll(empId, salId, days, ot, mon, year, net, meth, pd);

            if (ch == 1) {
                payrollOps.addPayroll(p);
            } else {
                payrollOps.addPayrollWithTransaction(p);
            }

        } else if (ch == 2) {
            System.out.print("Enter Emp ID: "); int id = sc.nextInt();
            ArrayList<Payroll> list = payrollOps.getPayrollByEmployee(id);
            for (int i = 0; i < list.size(); i++) {
                System.out.println("----");
                list.get(i).display();
            }
        }
    }
}
