import java.sql.*;
import java.util.ArrayList;

public class PayrollOperations {

    // ── INSERT ────────────────────────────────────────────────────────────────
    public void addPayroll(Payroll payroll) throws DatabaseException {

        String sql = "INSERT INTO Payroll (emp_id, salary_id, working_days, overtime_hours, " +
                     "pay_month, pay_year, net_salary, payment_method, payment_date) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = DBConnection.getConnection();
            ps   = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            ps.setInt(1, payroll.getEmpId());
            ps.setInt(2, payroll.getSalaryId());
            ps.setInt(3, payroll.getWorkingDays());
            ps.setInt(4, payroll.getOvertimeHours());
            ps.setString(5, payroll.getPayMonth());
            ps.setInt(6, payroll.getPayYear());
            ps.setDouble(7, payroll.getNetSalary());
            ps.setString(8, payroll.getPaymentMethod());
            ps.setString(9, payroll.getPaymentDate());

            ps.executeUpdate();

            ResultSet keys = ps.getGeneratedKeys();
            if (keys.next()) {
                payroll.setPayrollId(keys.getInt(1));
            }

            System.out.println("Payroll record added! Payroll ID = " + payroll.getPayrollId());

        } catch (SQLException e) {
            throw new DatabaseException("Failed to add payroll: " + e.getMessage());
        } finally {
            try { if (ps != null) ps.close(); } catch (SQLException e) { }
        }
    }

    public ArrayList<Payroll> getPayrollByEmployee(int empId) throws DatabaseException {

        ArrayList<Payroll> list = new ArrayList<Payroll>();
        String sql = "SELECT * FROM Payroll WHERE emp_id = ?";

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = DBConnection.getConnection();
            ps   = conn.prepareStatement(sql);
            ps.setInt(1, empId);
            rs = ps.executeQuery();

            while (rs.next()) {
                Payroll p = new Payroll(
                    rs.getInt("payroll_id"),
                    rs.getInt("emp_id"),
                    rs.getInt("salary_id"),
                    rs.getInt("working_days"),
                    rs.getInt("overtime_hours"),
                    rs.getString("pay_month"),
                    rs.getInt("pay_year"),
                    rs.getDouble("net_salary"),
                    rs.getString("payment_method"),
                    rs.getString("payment_date")
                );
                list.add(p);
            }

        } catch (SQLException e) {
            throw new DatabaseException("Failed to fetch payroll records: " + e.getMessage());
        } finally {
            try { if (rs != null) rs.close(); } catch (SQLException e) { }
            try { if (ps != null) ps.close(); } catch (SQLException e) { }
        }

        return list;
    }

    public void printFullPayrollReport() throws DatabaseException {

        String sql = "SELECT e.emp_id, e.emp_name, d.dept_name, e.designation, " +
                     "       s.basic_pay, s.hra, s.da, s.bonus, s.tax, s.deductions, " +
                     "       p.working_days, p.overtime_hours, p.pay_month, p.pay_year, " +
                     "       p.net_salary, p.payment_method, p.payment_date " +
                     "FROM Payroll p " +
                     "JOIN Employee   e ON p.emp_id    = e.emp_id " +
                     "JOIN Department d ON e.dept_id   = d.dept_id " +
                     "JOIN Salary     s ON p.salary_id = s.salary_id " +
                     "ORDER BY e.emp_name";

        Connection conn = null;
        Statement stmt  = null;
        ResultSet rs    = null;

        try {
            conn = DBConnection.getConnection();
            stmt = conn.createStatement();
            rs   = stmt.executeQuery(sql);

            System.out.println("\n========== FULL PAYROLL REPORT ==========");
            System.out.printf("%-5s %-15s %-12s %-18s %-10s %-5s %-4s %-10s %-10s %-12s%n",
                "ID", "Name", "Department", "Designation",
                "BasicPay", "Days", "OT", "NetSalary", "Method", "PaidOn");
            System.out.println("=".repeat(105));

            while (rs.next()) {
                System.out.printf("%-5d %-15s %-12s %-18s %-10.0f %-5d %-4d %-10.0f %-10s %-12s  [%s %d]%n",
                    rs.getInt("emp_id"),
                    rs.getString("emp_name"),
                    rs.getString("dept_name"),
                    rs.getString("designation"),
                    rs.getDouble("basic_pay"),
                    rs.getInt("working_days"),
                    rs.getInt("overtime_hours"),
                    rs.getDouble("net_salary"),
                    rs.getString("payment_method"),
                    rs.getString("payment_date"),
                    rs.getString("pay_month"),
                    rs.getInt("pay_year")
                );
            }

            System.out.println("=".repeat(105));

        } catch (SQLException e) {
            throw new DatabaseException("Failed to generate report: " + e.getMessage());
        } finally {
            try { if (rs   != null) rs.close();  } catch (SQLException e) { }
            try { if (stmt != null) stmt.close(); } catch (SQLException e) { }
        }
    }

    public void addPayrollWithTransaction(Payroll payroll) throws DatabaseException {

        String insertSQL = "INSERT INTO Payroll (emp_id, salary_id, working_days, overtime_hours, " +
                           "pay_month, pay_year, net_salary, payment_method, payment_date) " +
                           "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = DBConnection.getConnection();

            conn.setAutoCommit(false);

            ps = conn.prepareStatement(insertSQL, Statement.RETURN_GENERATED_KEYS);

            ps.setInt(1, payroll.getEmpId());
            ps.setInt(2, payroll.getSalaryId());
            ps.setInt(3, payroll.getWorkingDays());
            ps.setInt(4, payroll.getOvertimeHours());
            ps.setString(5, payroll.getPayMonth());
            ps.setInt(6, payroll.getPayYear());
            ps.setDouble(7, payroll.getNetSalary());
            ps.setString(8, payroll.getPaymentMethod());
            ps.setString(9, payroll.getPaymentDate());

            ps.executeUpdate();
            conn.commit();

            ResultSet keys = ps.getGeneratedKeys();
            if (keys.next()) {
                payroll.setPayrollId(keys.getInt(1));
            }

            System.out.println("Payroll saved with transaction. ID = " + payroll.getPayrollId());

        } catch (SQLException e) {
            try {
                if (conn != null) conn.rollback();
                System.out.println("Transaction rolled back due to error.");
            } catch (SQLException ex) {
                System.out.println("Rollback failed: " + ex.getMessage());
            }
            throw new DatabaseException("Transaction failed: " + e.getMessage());

        } finally {
            try {
                if (conn != null) conn.setAutoCommit(true); // restore default
                if (ps   != null) ps.close();
            } catch (SQLException e) { }
        }
    }
}
