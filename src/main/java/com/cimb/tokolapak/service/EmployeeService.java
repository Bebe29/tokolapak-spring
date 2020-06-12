package com.cimb.tokolapak.service;

import com.cimb.tokolapak.entity.Employee;
import com.cimb.tokolapak.entity.EmployeeAddress;

public interface EmployeeService {
	public Employee addEmployee(Employee employee);

	public Iterable<Employee> getEmployee();

	public void deleteEmployee(Employee employee);

	public void deleteEmployeeAddress(EmployeeAddress employeeAddress);

	public Employee updateEmployee(Employee employee);

	public EmployeeAddress addEmployeeAddres(EmployeeAddress employeeAddress);
}
