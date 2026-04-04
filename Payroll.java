public class Payroll {

    private int    payrollId;
    private int    empId;
    private int    salaryId;
    private int    workingDays;
    private int    overtimeHours;
    private String payMonth;
    private int    payYear;
    private double netSalary;
    private String paymentMethod;
    private String paymentDate;

    public Payroll() {
    }

    public Payroll(int empId, int salaryId, int workingDays, int overtimeHours,
                   String payMonth, int payYear, double netSalary,
                   String paymentMethod, String paymentDate) {
        this.empId         = empId;
        this.salaryId      = salaryId;
        this.workingDays   = workingDays;
        this.overtimeHours = overtimeHours;
        this.payMonth      = payMonth;
        this.payYear       = payYear;
        this.netSalary     = netSalary;
        this.paymentMethod = paymentMethod;
        this.paymentDate   = paymentDate;
    }

    public Payroll(int payrollId, int empId, int salaryId, int workingDays,
                   int overtimeHours, String payMonth, int payYear,
                   double netSalary, String paymentMethod, String paymentDate) {
        this(empId, salaryId, workingDays, overtimeHours,
             payMonth, payYear, netSalary, paymentMethod, paymentDate);
        this.payrollId = payrollId;
    }

    public int getPayrollId(){ 
        return payrollId; 
    }
    public int getEmpId(){ 
        return empId; 
    }
    public int getSalaryId(){ 
        return salaryId; 
    }
    public int getWorkingDays(){ 
        return workingDays; 
    }
    public int getOvertimeHours() { 
        return overtimeHours; 
    }
    public String getPayMonth(){ 
        return payMonth; 
    }
    public int getPayYear(){ 
        return payYear; 
    }
    public double getNetSalary(){ 
        return netSalary; 
    }
    public String getPaymentMethod() { 
        return paymentMethod; 
    }
    public String getPaymentDate(){ 
        return paymentDate; 
    }

    public void setPayrollId(int payrollId){ 
        this.payrollId = payrollId; 
    }
    public void setEmpId(int empId){ 
        this.empId = empId; 
    }
    public void setSalaryId(int salaryId){ 
        this.salaryId = salaryId; 
    }
    public void setWorkingDays(int workingDays){ 
        this.workingDays = workingDays; 
    }
    public void setOvertimeHours(int overtimeHours) { 
        this.overtimeHours = overtimeHours; }
    public void setPayMonth(String payMonth){ 
        this.payMonth = payMonth; 
    }
    public void setPayYear(int payYear){ 
        this.payYear = payYear; 
    }
    public void setNetSalary(double netSalary){ 
        this.netSalary = netSalary; 
    }
    public void setPaymentMethod(String m){ 
        this.paymentMethod = m; 
    }
    public void setPaymentDate(String paymentDate)  
    { this.paymentDate = paymentDate; 

    }

    public void display() {
        System.out.println("Payroll ID     : " + payrollId);
        System.out.println("Emp ID         : " + empId);
        System.out.println("Salary ID      : " + salaryId);
        System.out.println("Working Days   : " + workingDays);
        System.out.println("Overtime Hours : " + overtimeHours);
        System.out.println("Pay Month/Year : " + payMonth + " " + payYear);
        System.out.println("Net Salary     : " + netSalary);
        System.out.println("Payment Method : " + paymentMethod);
        System.out.println("Payment Date   : " + paymentDate);
    }
}
