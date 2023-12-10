package org.example;

import java.util.Date;

/*
    @desc: class for payroll data
 */
public class EmployeePayrollData {
    public int employeeId;
    public String employeeName;
    public String phoneNumber;
    public String address;
    public String department;
    public String gender;
    public Float basicPay;
    public Float deductions;
    public Float taxablePay;
    public Float tax;
    public Float netPay;
    public Date startDate;
    public String city;
    public String state;

    /*
     * @desc: constructor for class
     * @params: id, name, salary
     * @return: none
     */
    public EmployeePayrollData(Integer employeeId, String employeeName, String phoneNumber, String department, String address, String gender, String city, String state, Date startDate, Float basicPay, Float tax, Float deductions, Float taxablePay, Float netPay){
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.department = department;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.gender = gender;
        this.city = city;
        this.state = state;
        this.startDate = startDate;
        this.basicPay = basicPay;
        this.deductions = deductions;
        this.tax = tax;
        this.taxablePay = taxablePay;
        this.netPay = netPay;
    }

    /*
     * @desc: override function for toString function
     * @params: none
     * @return: String
     */
    public String toString(){
        return "id: " + employeeId + ", name: " + employeeName + ", salary: " + basicPay;
    }
}
