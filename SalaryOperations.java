import java.sql.*;
import java.util.ArrayList;

public class SalaryOperations {

    public void addSalary(Salary salary) throws DatabaseException {

        String sql = "INSERT INTO Salary (emp_id, basic_pay, hra, da, bonus, tax, deductions) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?)";

        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = DBConnection.getConnection();
            ps   = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            ps.setInt(1, salary.getEmpId());
            ps.setDouble(2, salary.getBasicPay());
            ps.setDouble(3, salary.getHra());
            ps.setDouble(4, salary.getDa());
            ps.setDouble(5, salary.getBonus());
            ps.setDouble(6, salary.getTax());
            ps.setDouble(7, salary.getDeductions());

            ps.executeUpdate();

            ResultSet keys = ps.getGeneratedKeys();
            if (keys.next()) {
                salary.setSalaryId(keys.getInt(1));
            }

            System.out.println("Salary record added! Salary ID = " + salary.getSalaryId());

        } catch (SQLException e) {
            throw new DatabaseException("Failed to add salary: " + e.getMessage());
        } finally {
            try { if (ps != null) ps.close(); } catch (SQLException e) { }
        }
    }
    public Salary getSalaryByEmpId(int empId) throws DatabaseException {

        String sql = "SELECT * FROM Salary WHERE emp_id = ?";

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = DBConnection.getConnection();
            ps   = conn.prepareStatement(sql);
            ps.setInt(1, empId);
            rs = ps.executeQuery();

            if (rs.next()) {
                return new Salary(
                    rs.getInt("salary_id"),
                    rs.getInt("emp_id"),
                    rs.getDouble("basic_pay"),
                    rs.getDouble("hra"),
                    rs.getDouble("da"),
                    rs.getDouble("bonus"),
                    rs.getDouble("tax"),
                    rs.getDouble("deductions")
                );
            } else {
                System.out.println("No salary record found for emp_id = " + empId);
                return null;
            }

        } catch (SQLException e) {
            throw new DatabaseException("Failed to fetch salary: " + e.getMessage());
        } finally {
            try { if (rs != null) rs.close(); } catch (SQLException e) { }
            try { if (ps != null) ps.close(); } catch (SQLException e) { }
        }
    }

    // ── SELECT ALL ────────────────────────────────────────────────────────────
    public ArrayList<Salary> getAllSalaries() throws DatabaseException {

        ArrayList<Salary> list = new ArrayList<Salary>();
        String sql = "SELECT * FROM Salary";

        Connection conn = null;
        Statement stmt  = null;
        ResultSet rs    = null;

        try {
            conn = DBConnection.getConnection();
            stmt = conn.createStatement();
            rs   = stmt.executeQuery(sql);

            while (rs.next()) {
                Salary s = new Salary(
                    rs.getInt("salary_id"),
                    rs.getInt("emp_id"),
                    rs.getDouble("basic_pay"),
                    rs.getDouble("hra"),
                    rs.getDouble("da"),
                    rs.getDouble("bonus"),
                    rs.getDouble("tax"),
                    rs.getDouble("deductions")
                );
                list.add(s);
            }

        } catch (SQLException e) {
            throw new DatabaseException("Failed to fetch all salaries: " + e.getMessage());
        } finally {
            try { if (rs   != null) rs.close();  } catch (SQLException e) { }
            try { if (stmt != null) stmt.close(); } catch (SQLException e) { }
        }

        return list;
    }

    // ── UPDATE ────────────────────────────────────────────────────────────────
    public void updateSalary(Salary salary) throws DatabaseException {

        String sql = "UPDATE Salary SET basic_pay=?, hra=?, da=?, bonus=?, tax=?, deductions=? " +
                     "WHERE salary_id=?";

        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = DBConnection.getConnection();
            ps   = conn.prepareStatement(sql);

            ps.setDouble(1, salary.getBasicPay());
            ps.setDouble(2, salary.getHra());
            ps.setDouble(3, salary.getDa());
            ps.setDouble(4, salary.getBonus());
            ps.setDouble(5, salary.getTax());
            ps.setDouble(6, salary.getDeductions());
            ps.setInt(7, salary.getSalaryId());

            int rows = ps.executeUpdate();
            System.out.println(rows > 0 ? "Salary updated." : "Salary record not found.");

        } catch (SQLException e) {
            throw new DatabaseException("Failed to update salary: " + e.getMessage());
        } finally {
            try { if (ps != null) ps.close(); } catch (SQLException e) { }
        }
    }
}
