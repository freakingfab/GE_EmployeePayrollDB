-- UC1
create database payroll_service
use payroll_service

-- UC2
create table employee_payroll(
	id int auto_increment primary key,
    name text,
    salary text,
    start_date date
);

-- UC3
INSERT INTO employee_payroll (name, salary, start_date)
VALUES
	('Bob Marley', 5000.00, '2023-04-10'),
    ('Dwayne Johnson', 52000.00, '2023-05-10'),
    ('John Doe', 50000.00, '2023-01-01'),
    ('Jane Smith', 60000.00, '2023-02-15'),
    ('Bob Johnson', 55000.00, '2023-03-10');

-- UC4
select * from employee_payroll

-- UC5
select salary from employee_payroll where name='Bob Marley';

select * from employee_payroll WHERE start_date BETWEEN CAST('2023-03-01'
AS DATE) AND DATE(NOW());

-- UC6
alter table employee_payroll
add gender char;

UPDATE employee_payroll
SET gender = 'M'
WHERE name = 'John Doe' or name = 'Bob Marley';

-- UC7
SELECT SUM(salary) FROM employee_payroll
WHERE gender = 'M' GROUP BY gender;

-- UC8, UC9
drop table employee_payroll

CREATE TABLE employee_payroll(
	EmployeeID int auto_increment primary key,
	EmployeeName varchar(255), 
	PhoneNumber varchar(255),
	Address varchar(255), 
    Department varchar(255),
    Gender char,
    BasicPay float, 
    Deductions float,
    TaxablePay float,
    Tax float, 
    NetPay float,
    StartDate Date,
    City varchar(255), 
    Country varchar(255)
);

-- UC10
insert into employee_payroll(EmployeeName, PhoneNumber, Address, Department, Gender, BasicPay, Deductions, TaxablePay,Tax, NetPay, StartDate, City, Country)
values ('Gunjan', '78787878787', 'Mumbai', 'ENGG', 'M', 30000, 2000, 5000, 3000, 2000, CAST('2023-03-01' AS DATE), 'Mumbai', 'India');

select * from employee_payroll


-- UC 11 and 12
create table employee(
	EmployeeID int auto_increment primary key,
	EmployeeName varchar(255), 
	PhoneNumber varchar(255),
	Address varchar(255), 
    DepartmentID int,
    Gender char,
    StartDate Date,
    City varchar(255), 
    Country varchar(255),
    FOREIGN KEY (DepartmentID) REFERENCES department(DepartmentID)
);

create table payroll(
	BasicPay float, 
    Deductions float,
    TaxablePay float,
    Tax float, 
    NetPay float,
    EmployeeID int,
    FOREIGN KEY (EmployeeID) REFERENCES employee(EmployeeId)
)

create table department(
	departmentID int primary key
)

insert into department
values
(1),
(2);

insert into employee(EmployeeName, PhoneNumber, Address, DepartmentID, Gender, StartDate, City, Country)
values 
	('Gunjan', '78787878787', 'Mumbai', 1, 'M', CAST('2023-03-01' AS DATE), 'Mumbai', 'India'),
    ('Daksh', '78787878787', 'Mumbai', 2, 'M', CAST('2023-03-01' AS DATE), 'Mumbai', 'India');
    
insert into payroll(BasicPay, Deductions, TaxablePay,Tax, NetPay,EmployeeID)
values 
	(30000, 2000, 5000, 3000, 2000,(select employeeid from employee where employeename='Gunjan')),
    (30000, 2000, 5000, 3000, 2000,(select employeeid from employee where employeename='Daksh'));

select * from employee

select netpay
from payroll
join employee on employee.employeeid=payroll.employeeid
where employee.employeename='Daksh'

SELECT
    e.EmployeeID,
    e.EmployeeName,
    SUM(p.BasicPay) AS TotalBasicPay,
    AVG(p.NetPay) AS AverageNetPay
FROM
    employee e
JOIN
    payroll p ON e.EmployeeID = p.EmployeeID
GROUP BY
    e.EmployeeID, e.EmployeeName;

