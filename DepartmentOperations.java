import java.sql.*;
import java.util.ArrayList;

public class DepartmentOperations {

    public void addDepartment(Department dept) throws DatabaseException {

        String sql = "INSERT INTO Department (dept_name, location, manager_name, budget) VALUES (?, ?, ?, ?)";

        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = DBConnection.getConnection();
            ps   = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            ps.setString(1, dept.getDeptName());
            ps.setString(2, dept.getLocation());
            ps.setString(3, dept.getManagerName());
            ps.setDouble(4, dept.getBudget());

            ps.executeUpdate();

            // Get auto-generated ID back
            ResultSet keys = ps.getGeneratedKeys();
            if (keys.next()) {
                dept.setDeptId(keys.getInt(1));
            }

            System.out.println("Department added successfully! ID = " + dept.getDeptId());

        } catch (SQLException e) {
            throw new DatabaseException("Failed to add department: " + e.getMessage());
        } finally {
            try { if (ps != null) ps.close(); } catch (SQLException e) { }
        }
    }

    public ArrayList<Department> getAllDepartments() throws DatabaseException {

        ArrayList<Department> list = new ArrayList<Department>();
        String sql = "SELECT * FROM Department";

        Connection conn = null;
        Statement stmt  = null;
        ResultSet rs    = null;

        try {
            conn = DBConnection.getConnection();
            stmt = conn.createStatement();
            rs   = stmt.executeQuery(sql);

            while (rs.next()) {
                Department d = new Department(
                    rs.getInt("dept_id"),
                    rs.getString("dept_name"),
                    rs.getString("location"),
                    rs.getString("manager_name"),
                    rs.getDouble("budget")
                );
                list.add(d);
            }

        } catch (SQLException e) {
            throw new DatabaseException("Failed to fetch departments: " + e.getMessage());
        } finally {
            try { if (rs   != null) rs.close();   } catch (SQLException e) { }
            try { if (stmt != null) stmt.close();  } catch (SQLException e) { }
        }

        return list;
    }

    public void updateDepartment(Department dept) throws DatabaseException {

        String sql = "UPDATE Department SET dept_name=?, location=?, manager_name=?, budget=? WHERE dept_id=?";

        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = DBConnection.getConnection();
            ps   = conn.prepareStatement(sql);

            ps.setString(1, dept.getDeptName());
            ps.setString(2, dept.getLocation());
            ps.setString(3, dept.getManagerName());
            ps.setDouble(4, dept.getBudget());
            ps.setInt(5, dept.getDeptId());

            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Department updated successfully.");
            } else {
                System.out.println("No department found with ID: " + dept.getDeptId());
            }

        } catch (SQLException e) {
            throw new DatabaseException("Failed to update department: " + e.getMessage());
        } finally {
            try { if (ps != null) ps.close(); } catch (SQLException e) { }
        }
    }

    public void deleteDepartment(int deptId) throws DatabaseException {

        String sql = "DELETE FROM Department WHERE dept_id = ?";

        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = DBConnection.getConnection();
            ps   = conn.prepareStatement(sql);
            ps.setInt(1, deptId);

            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Department deleted successfully.");
            } else {
                System.out.println("No department found with ID: " + deptId);
            }

        } catch (SQLException e) {
            throw new DatabaseException("Failed to delete department: " + e.getMessage());
        } finally {
            try { if (ps != null) ps.close(); } catch (SQLException e) { }
        }
    }
}
