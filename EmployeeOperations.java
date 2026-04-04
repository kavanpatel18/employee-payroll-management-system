import java.sql.*;
import java.util.ArrayList;

public class EmployeeOperations {

    // ── INSERT ────────────────────────────────────────────────────────────────
    public void addEmployee(Employee emp) throws DatabaseException, DuplicateRecordException {
        if (emailExists(emp.getEmail())) {
            throw new DuplicateRecordException("An employee with email '" + emp.getEmail() + "' already exists.");
        }

        String sql = "INSERT INTO Employee (emp_name, age, gender, email, phone, address, designation, hire_date, dept_id) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = DBConnection.getConnection();
            ps   = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            ps.setString(1, emp.getEmpName());
            ps.setInt(2, emp.getAge());
            ps.setString(3, emp.getGender());
            ps.setString(4, emp.getEmail());
            ps.setString(5, emp.getPhone());
            ps.setString(6, emp.getAddress());
            ps.setString(7, emp.getDesignation());
            ps.setString(8, emp.getHireDate());
            ps.setInt(9, emp.getDeptId());

            ps.executeUpdate();

            ResultSet keys = ps.getGeneratedKeys();
            if (keys.next()) {
                emp.setEmpId(keys.getInt(1));
            }

            System.out.println("Employee added! ID = " + emp.getEmpId());

        } catch (SQLException e) {
            throw new DatabaseException("Failed to add employee: " + e.getMessage());
        } finally {
            try { if (ps != null) ps.close(); } catch (SQLException e) { }
        }
    }

    public ArrayList<Employee> getAllEmployees() throws DatabaseException {

        ArrayList<Employee> list = new ArrayList<Employee>();
        String sql = "SELECT * FROM Employee";

        Connection conn = null;
        Statement stmt  = null;
        ResultSet rs    = null;

        try {
            conn = DBConnection.getConnection();
            stmt = conn.createStatement();
            rs   = stmt.executeQuery(sql);

            while (rs.next()) {
                Employee e = new Employee(
                    rs.getInt("emp_id"),
                    rs.getString("emp_name"),
                    rs.getInt("age"),
                    rs.getString("gender"),
                    rs.getString("email"),
                    rs.getString("phone"),
                    rs.getString("address"),
                    rs.getString("designation"),
                    rs.getString("hire_date"),
                    rs.getInt("dept_id")
                );
                list.add(e);
            }

        } catch (SQLException e) {
            throw new DatabaseException("Failed to fetch employees: " + e.getMessage());
        } finally {
            try { if (rs   != null) rs.close();  } catch (SQLException e) { }
            try { if (stmt != null) stmt.close(); } catch (SQLException e) { }
        }

        return list;
    }

    public Employee getEmployeeById(int empId) throws DatabaseException, EmployeeNotFoundException {

        String sql = "SELECT * FROM Employee WHERE emp_id = ?";

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = DBConnection.getConnection();
            ps   = conn.prepareStatement(sql);
            ps.setInt(1, empId);
            rs = ps.executeQuery();

            if (rs.next()) {
                return new Employee(
                    rs.getInt("emp_id"),
                    rs.getString("emp_name"),
                    rs.getInt("age"),
                    rs.getString("gender"),
                    rs.getString("email"),
                    rs.getString("phone"),
                    rs.getString("address"),
                    rs.getString("designation"),
                    rs.getString("hire_date"),
                    rs.getInt("dept_id")
                );
            } else {
                throw new EmployeeNotFoundException(empId);
            }

        } catch (SQLException e) {
            throw new DatabaseException("Failed to search employee: " + e.getMessage());
        } finally {
            try { if (rs   != null) rs.close();  } catch (SQLException e) { }
            try { if (ps   != null) ps.close();  } catch (SQLException e) { }
        }
    }

    public void updateEmployee(Employee emp) throws DatabaseException {

        String sql = "UPDATE Employee SET emp_name=?, age=?, gender=?, email=?, phone=?, " +
                     "address=?, designation=?, hire_date=?, dept_id=? WHERE emp_id=?";

        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = DBConnection.getConnection();
            ps   = conn.prepareStatement(sql);

            ps.setString(1, emp.getEmpName());
            ps.setInt(2, emp.getAge());
            ps.setString(3, emp.getGender());
            ps.setString(4, emp.getEmail());
            ps.setString(5, emp.getPhone());
            ps.setString(6, emp.getAddress());
            ps.setString(7, emp.getDesignation());
            ps.setString(8, emp.getHireDate());
            ps.setInt(9, emp.getDeptId());
            ps.setInt(10, emp.getEmpId());

            int rows = ps.executeUpdate();
            System.out.println(rows > 0 ? "Employee updated." : "Employee not found.");

        } catch (SQLException e) {
            throw new DatabaseException("Failed to update employee: " + e.getMessage());
        } finally {
            try { if (ps != null) ps.close(); } catch (SQLException e) { }
        }
    }

    public void deleteEmployee(int empId) throws DatabaseException {

        String sql = "DELETE FROM Employee WHERE emp_id = ?";

        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = DBConnection.getConnection();
            ps   = conn.prepareStatement(sql);
            ps.setInt(1, empId);

            int rows = ps.executeUpdate();
            System.out.println(rows > 0 ? "Employee deleted." : "Employee not found.");

        } catch (SQLException e) {
            throw new DatabaseException("Failed to delete employee: " + e.getMessage());
        } finally {
            try { if (ps != null) ps.close(); } catch (SQLException e) { }
        }
    }

    private boolean emailExists(String email) throws DatabaseException {
        String sql = "SELECT emp_id FROM Employee WHERE email = ?";

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = DBConnection.getConnection();
            ps   = conn.prepareStatement(sql);
            ps.setString(1, email);
            rs = ps.executeQuery();
            return rs.next(); // true if a row is found

        } catch (SQLException e) {
            throw new DatabaseException("Error checking email: " + e.getMessage());
        } finally {
            try { if (rs != null) rs.close(); } catch (SQLException e) { }
            try { if (ps != null) ps.close(); } catch (SQLException e) { }
        }
    }
}
