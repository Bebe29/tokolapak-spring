package com.cimb.tokolapak.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cimb.tokolapak.dao.EmployeeAddressRepo;
import com.cimb.tokolapak.dao.EmployeeRepo;
import com.cimb.tokolapak.entity.Employee;
import com.cimb.tokolapak.entity.EmployeeAddress;
import com.cimb.tokolapak.service.EmployeeService;

@RestController
@RequestMapping("/employee")
public class EmpolyeeController {
	@Autowired
	private EmployeeRepo employeeRepo;

	@Autowired
	private EmployeeService employeeService;

	@Autowired
	private EmployeeAddressRepo employeeAddressRepo;

	@PostMapping
	public Employee addEmployee(@RequestBody Employee employee) {
		return employeeRepo.save(employee);
	}

	@GetMapping
	public Iterable<Employee> getEmployee() {
		return employeeRepo.findAll();
	}

	@DeleteMapping("/{id}")
	public void deleteEmployeeById(@PathVariable int id) {
		Optional<Employee> employee = employeeRepo.findById(id);

		if (employee.get() == null)
			throw new RuntimeException("Employee not found");

		employeeService.deleteEmployee(employee.get());
	}

	@DeleteMapping("/address/{id}")
	public void deleteEmployeeAddressById(@PathVariable int id) {
		Optional<EmployeeAddress> employeeAddress = employeeAddressRepo.findById(id);

		if (employeeAddress.get() == null)
			throw new RuntimeException("Employee address not found");

		employeeService.deleteEmployeeAddress(employeeAddress.get());

	}

	@PatchMapping
	public Employee updateEmployee(@RequestBody Employee employee) {
		// public Employee updateEmployeeAddress(@RequestBody Employee employee,
		// EmployeeAddress employeeAddress) {
		// employeeAddress.setEmployee(employee);
		// employeeAddress.getEmployee().setEmployeeAddress(employeeAddress);

		return employeeRepo.save(employee);
	}

	@PostMapping("/address")
	public EmployeeAddress addEmployeeAddress(@RequestBody EmployeeAddress employeeAddress) {
		return employeeAddressRepo.save(employeeAddress);
	}
}
