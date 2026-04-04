public class Department {

    private int    deptId;
    private String deptName;
    private String location;
    private String managerName;
    private double budget;

    public Department() {
    }

    public Department(String deptName, String location, String managerName, double budget) {
        this.deptName    = deptName;
        this.location    = location;
        this.managerName = managerName;
        this.budget      = budget;
    }

    public Department(int deptId, String deptName, String location, String managerName, double budget) {
        this.deptId      = deptId;
        this.deptName    = deptName;
        this.location    = location;
        this.managerName = managerName;
        this.budget      = budget;
    }

    public int    getDeptId(){ 
        return deptId; 
    }
    public String getDeptName(){ 
        return deptName; 
    }
    public String getLocation(){ 
        return location; 
    }
    public String getManagerName() { 
        return managerName; 
    }
    public double getBudget(){ 
        return budget; 
    }

    public void setDeptId(int deptId){ 
        this.deptId = deptId; 
    }
    public void setDeptName(String deptName){ 
        this.deptName = deptName; 
    }
    public void setLocation(String location){ 
        this.location = location; 
    }
    public void setManagerName(String m){ 
        this.managerName = m; 
    }
    public void setBudget(double budget){ 
        this.budget = budget; 
    }

    // Display method
    public void display() {
        System.out.println("Dept ID   : " + deptId);
        System.out.println("Dept Name : " + deptName);
        System.out.println("Location  : " + location);
        System.out.println("Manager   : " + managerName);
        System.out.println("Budget    : " + budget);
    }
}
