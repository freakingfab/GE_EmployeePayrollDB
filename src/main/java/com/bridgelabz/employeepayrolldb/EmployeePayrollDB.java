
package com.bridgelabz.employeepayrolldb;

import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
/*
    @desc: provides method for dealing with MySQl
 */
public class EmployeePayrollDB{

    private Connection connection;
    /*
        @desc: constructor for class
        @params: Connection object
        @return: none
     */
    public EmployeePayrollDB(Connection connection){
        this.connection = connection;
    }
    /*
        @desc: function for retrieving the data
        @param: none
        @return: List<EmployeePayrollData>
     */
    public List<EmployeePayrollData> retrieveEmployeePayrollData() {
        List<EmployeePayrollData> employeePayrollList = new ArrayList<>();
        String sqlQuery = "select * from employee emp join payroll pay on emp.employeeid=pay.employeeid";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    int employeeId = resultSet.getInt("EmployeeID");
                    String employeeName = resultSet.getString("EmployeeName");
                    String phoneNumber = resultSet.getString("PhoneNumber");
                    String address = resultSet.getString("address");
                    String gender = resultSet.getString("gender");
                    String city = resultSet.getString("city");
                    String state = resultSet.getString("country");
                    Date startDate = resultSet.getDate("startDate");
                    Float basicPay = resultSet.getFloat("BasicPay");
                    Float tax = resultSet.getFloat("tax");
                    Float deductions = resultSet.getFloat("deductions");
                    Float taxablePay = resultSet.getFloat("taxablepay");
                    Float netPay = resultSet.getFloat("netpay");
                    EmployeePayrollData payrollData = new EmployeePayrollData(employeeId, employeeName, phoneNumber, "ENG", address, gender, city, state, startDate, basicPay, tax, deductions, taxablePay, netPay);
                    employeePayrollList.add(payrollData);
                }
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return employeePayrollList;
    }

    /*
        @desc: function for upating user
        @params: name, basicpay
        @return: void
     */
    public void updateEmployeeSalary(String employeeName, Float newSalary) {
            String sqlUpdate = "UPDATE payroll pay join employee emp on pay.employeeid=emp.employeeid SET pay.BasicPay = ? WHERE emp.EmployeeName = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sqlUpdate)) {
                preparedStatement.setFloat(1, newSalary);
                preparedStatement.setString(2, employeeName);
                int rowsAffected = preparedStatement.executeUpdate();

                if (rowsAffected > 0) {
                    System.out.println("Salary updated successfully in the database.");
                } else {
                    System.out.println("Employee not found or no changes made.");
                }
            }
         catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /*
        @desc: give data of user with satrt date after given date
        @param: date
        @return: List<EmployeePayrollData>
     */
    public List<EmployeePayrollData> retrieveDataAfterDate(Date date) {
        List<EmployeePayrollData> employeePayrollList = new ArrayList<>();
        String sqlQuery = "select * from employee emp join payroll pay on emp.employeeid=pay.employeeid where emp.startdate BETWEEN CAST(? AS DATE) AND DATE(NOW());";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)) {
            preparedStatement.setDate(1, date);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    int employeeId = resultSet.getInt("EmployeeID");
                    String employeeName = resultSet.getString("EmployeeName");
                    String phoneNumber = resultSet.getString("PhoneNumber");
                    String address = resultSet.getString("address");
                    String gender = resultSet.getString("gender");
                    String city = resultSet.getString("city");
                    String state = resultSet.getString("country");
                    Date startDate = resultSet.getDate("startDate");
                    Float basicPay = resultSet.getFloat("BasicPay");
                    Float tax = resultSet.getFloat("tax");
                    Float deductions = resultSet.getFloat("deductions");
                    Float taxablePay = resultSet.getFloat("taxablepay");
                    Float netPay = resultSet.getFloat("netpay");
                    EmployeePayrollData payrollData = new EmployeePayrollData(employeeId, employeeName, phoneNumber, "ENG", address, gender, city, state, startDate, basicPay, tax, deductions, taxablePay, netPay);
                    employeePayrollList.add(payrollData);
                }
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return employeePayrollList;
    }

    /*
        @desc: function to print average of salary for gender male
        @param:none
        @return: void
     */
    public void getAverageSalaryMale(){
        String sqlQuery = "select AVG(pay.basicpay) from employee emp join payroll pay on emp.employeeid=pay.employeeid where emp.gender='M';";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                System.out.println("Average Salary: "+ resultSet.getDouble(1));
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }
}