public class Employee {

    private int    empId;
    private String empName;
    private int    age;
    private String gender;
    private String email;
    private String phone;
    private String address;
    private String designation;
    private String hireDate;   //(YYYY-MM-DD) 
    private int    deptId;

    public Employee() {
    }

    public Employee(String empName, int age, String gender, String email,
                   String phone, String address, String designation,
                    String hireDate, int deptId) {
        this.empName     = empName;
        this.age         = age;
        this.gender      = gender;
        this.email       = email;
        this.phone       = phone;
        this.address     = address;
        this.designation = designation;
        this.hireDate    = hireDate;
        this.deptId      = deptId;
    }

    // Parameterized Constructor (with ID — used when reading from DB)
    public Employee(int empId, String empName, int age, String gender, String email,
                    String phone, String address, String designation,
                    String hireDate, int deptId) {
        this(empName, age, gender, email, phone, address, designation, hireDate, deptId);
        this.empId = empId;
    }

    // Getters
    public int    getEmpId(){ 
        return empId; 
    }
    public String getEmpName(){ 
        return empName; 
    }
    public int    getAge(){ 
        return age; 
    }
    public String getGender(){ 
        return gender; 
    }
    public String getEmail(){ 
        return email; 
    }
    public String getPhone(){ 
        return phone; 
    }
    public String getAddress(){ 
        return address; 
    }
    public String getDesignation(){ 
        return designation; 
    }
    public String getHireDate(){ 
        return hireDate; 
    }
    public int getDeptId(){ 
        return deptId; 
    }

    public void setEmpId(int empId){ 
        this.empId = empId;
    }
    public void setEmpName(String empName){ 
        this.empName = empName; 
    }
    public void setAge(int age){ 
        this.age = age; 
    }
    public void setGender(String gender){ 
        this.gender = gender; 
    }
    public void setEmail(String email){ 
        this.email = email; 
    }
    public void setPhone(String phone){ 
        this.phone = phone; 
    }
    public void setAddress(String address){ 
        this.address = address; 
    }
    public void setDesignation(String d){ 
        this.designation = d; 
    }
    public void setHireDate(String hireDate){ 
        this.hireDate = hireDate; 
    }
    public void setDeptId(int deptId){ 
        this.deptId = deptId; 
    }

    public void display() {
        System.out.println("Emp ID      : " + empId);
        System.out.println("Name        : " + empName);
        System.out.println("Age         : " + age);
        System.out.println("Gender      : " + gender);
        System.out.println("Email       : " + email);
        System.out.println("Phone       : " + phone);
        System.out.println("Address     : " + address);
        System.out.println("Designation : " + designation);
        System.out.println("Hire Date   : " + hireDate);
        System.out.println("Dept ID     : " + deptId);
    }
}
