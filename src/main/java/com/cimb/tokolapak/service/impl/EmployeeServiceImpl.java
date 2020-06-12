package com.cimb.tokolapak.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cimb.tokolapak.dao.EmployeeAddressRepo;
import com.cimb.tokolapak.dao.EmployeeRepo;
import com.cimb.tokolapak.entity.Employee;
import com.cimb.tokolapak.entity.EmployeeAddress;
import com.cimb.tokolapak.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	@Autowired
	private EmployeeRepo employeeRepo;

	@Autowired
	private EmployeeAddressRepo employeeAddressRepo;

	@Override
	public Employee addEmployee(Employee employee) {
		return employeeRepo.save(employee);
	}

	@Override
	public Iterable<Employee> getEmployee() {
		return employeeRepo.findAll();
	}

	@Override
	public void deleteEmployee(Employee employee) {
		employeeRepo.delete(employee);
	}

	@Override
	public void deleteEmployeeAddress(EmployeeAddress employeeAddress) {
		employeeAddress.getEmployee().setEmployeeAddress(null); // putuskan hubungan dari employee ke address
		employeeAddress.setEmployee(null); // putuskan hubungan dari address ke employee

		employeeAddressRepo.delete(employeeAddress);
	}

	@Override
	public Employee updateEmployee(Employee employee) {
		return employeeRepo.save(employee);
	}

	@Override
	public EmployeeAddress addEmployeeAddres(EmployeeAddress employeeAddress) {
		return employeeAddressRepo.save(employeeAddress);
	}

}
