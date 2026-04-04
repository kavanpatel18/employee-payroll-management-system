public class Salary {

    private int    salaryId;
    private int    empId;
    private double basicPay;
    private double hra; // house rent
    private double da; // daily allowence 
    private double bonus;
    private double tax;
    private double deductions;

    public Salary() {
    }
    public Salary(int empId, double basicPay, double hra, double da,
                  double bonus, double tax, double deductions) {
        this.empId      = empId;
        this.basicPay   = basicPay;
        this.hra        = hra;
        this.da         = da;
        this.bonus      = bonus;
        this.tax        = tax;
        this.deductions = deductions;
    }

    public Salary(int salaryId, int empId, double basicPay, double hra,
                  double da, double bonus, double tax, double deductions) {
        this(empId, basicPay, hra, da, bonus, tax, deductions);
        this.salaryId = salaryId;
    }

    public double getGrossPay() {
        return basicPay + hra + da + bonus;
    }

    public double getNetPay() {
        return getGrossPay() - tax - deductions;
    }

    public int getSalaryId(){ 
        return salaryId; 
    }
    public int getEmpId(){ 
        return empId; 
    }
    public double getBasicPay(){
        return basicPay; 
    }
    public double getHra(){ 
        return hra; 
    }
    public double getDa(){ 
        return da; 
    }
    public double getBonus(){ 
        return bonus; 
    }
    public double getTax(){ 
        return tax; 
    }
    public double getDeductions() { 
        return deductions; 
    }

    public void setSalaryId(int salaryId){ 
        this.salaryId = salaryId; 
    }
    public void setEmpId(int empId){ 
        this.empId = empId; 
    }
    public void setBasicPay(double basicPay){ 
        this.basicPay = basicPay; 
    }
    public void setHra(double hra){ 
        this.hra = hra; 
    }
    public void setDa(double da){ 
        this.da = da; 
    }
    public void setBonus(double bonus){ 
        this.bonus = bonus; 
    }
    public void setTax(double tax){ 
        this.tax = tax; 
    }
    public void setDeductions(double d){ 
        this.deductions = d; 
    }

    public void display() {
        System.out.println("Salary ID  : " + salaryId);
        System.out.println("Emp ID     : " + empId);
        System.out.println("Basic Pay  : " + basicPay);
        System.out.println("HRA        : " + hra);
        System.out.println("DA         : " + da);
        System.out.println("Bonus      : " + bonus);
        System.out.println("Tax        : " + tax);
        System.out.println("Deductions : " + deductions);
        System.out.println("Gross Pay  : " + getGrossPay());
        System.out.println("Net Pay    : " + getNetPay());
    }
}
