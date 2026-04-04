class DatabaseException extends Exception {

    public DatabaseException(String message) {
        super(message);
    }
}

class EmployeeNotFoundException extends Exception {

    public EmployeeNotFoundException(int empId) {
        super("Employee not found with ID: " + empId);
    }
}

class DuplicateRecordException extends Exception {

    public DuplicateRecordException(String message) {
        super(message);
    }
}
