
package com.bridgelabz.employeepayrolldb;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
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
}