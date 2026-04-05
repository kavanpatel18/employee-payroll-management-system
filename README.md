# 💼 Employee Payroll Management System

![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white) ![MySQL](https://img.shields.io/badge/mysql-%2300f.svg?style=for-the-badge&logo=mysql&logoColor=white)

A robust, Object-Oriented Java ecosystem interacting with a MySQL relational database to manage backend corporate structures, employee salaries, and fiscal departments.

## 🚀 Key Features
- **JDBC Database Connector**: Actively interfaces with `payroll_db` for atomic CRUD operations.
- **Department Routing**: Dynamically assigns roles, budgets, and operational locations per employee configuration.
- **Financial Architecture**: Auto-calculates salary models, taxation offsets, allowances, and payroll extraction logic securely.
- **Exception Protocols**: Contains custom exception handling (`DuplicateRecordException`, `PayrollExceptions`) ensuring high systemic stability and database rollback safety.

## 🛠️ Usage
Requires an active MySQL server instances handling standard schemas `(dept_id, location, age)`. Run `Main.java` inside an enterprise IDE (IntelliJ/Eclipse) after applying SQL configurations.
