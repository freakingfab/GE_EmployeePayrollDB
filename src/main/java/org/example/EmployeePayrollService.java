package org.example;

import java.util.ArrayList;
import java.util.List;

/*
 * @desc: class for service in employee payroll
 */
public class EmployeePayrollService {
    public List<EmployeePayrollData> employeePayrollList;

    /*
     * @desc: constructor for class
     * @params: none
     * @return: none
     */
    public EmployeePayrollService(){
        this.employeePayrollList = new ArrayList<>();
    }

    /*
     * @desc: parameterised constructor for class
     * @params: List<EmployeePayrollData>
     * @return: none
     */
    public EmployeePayrollService(List<EmployeePayrollData> employeePayrollList){
        this.employeePayrollList = employeePayrollList;
    }

    /*
        @desc: function to print data
        @params: none
        @return: none
     */
    public void printEmployeePayroll(){
        System.out.println("Printing employee payrolls from list:");
        employeePayrollList.forEach(emp -> System.out.println(emp.toString()));
    }
}
