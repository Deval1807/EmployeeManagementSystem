CREATE SCHEMA `ems` ;

CREATE TABLE Departments (
    department_id INT AUTO_INCREMENT PRIMARY KEY,
    department_name VARCHAR(40) NOT NULL UNIQUE
);

CREATE TABLE Employees (
    emp_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(30) NOT NULL,
    department_id INT,
    phone VARCHAR(10) NOT NULL UNIQUE,
    joining_date DATE NOT NULL,
    salary DECIMAL(10, 2) NOT NULL,
    FOREIGN KEY (department_id) REFERENCES Departments(department_id)
);

CREATE TABLE Projects (
    project_id INT AUTO_INCREMENT PRIMARY KEY,
    project_name VARCHAR(50) NOT NULL UNIQUE,
    start_date DATE,
    end_date DATE
);

CREATE TABLE Employee_Projects (
    emp_id INT,
    project_id INT,
    assigned_date DATE,
    PRIMARY KEY (emp_id, project_id),
    FOREIGN KEY (emp_id) REFERENCES Employees(emp_id),
    FOREIGN KEY (project_id) REFERENCES Projects(project_id)
);
