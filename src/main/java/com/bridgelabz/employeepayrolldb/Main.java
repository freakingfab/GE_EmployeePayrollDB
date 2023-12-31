package com.bridgelabz.employeepayrolldb;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        String jdbcUrl = "jdbc:mysql://localhost:3306/payroll_service";
        String username = "root";
        String password = "02031946";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("MySQL JDBC Driver registered.");

            // Establish a connection
            Connection connection = DriverManager.getConnection(jdbcUrl, username, password);
            System.out.println("Connection to database established.");
            EmployeePayrollDB employeePayrollServiceDB = new EmployeePayrollDB(connection);
            EmployeePayrollService employeePayrollService = new EmployeePayrollService(employeePayrollServiceDB.retrieveDataAfterDate(Date.valueOf("2022-01-01")));
            employeePayrollService.printEmployeePayroll();
            // Close the connection
            connection.close();
            System.out.println("Connection closed.");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}