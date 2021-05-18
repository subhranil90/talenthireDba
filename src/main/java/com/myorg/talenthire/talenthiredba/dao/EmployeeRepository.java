package com.myorg.talenthire.talenthiredba.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.myorg.talenthire.pojo.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
	public Employee findByEmpName(String empName);

	public Employee findById(int empId);

	public List<Employee> findAllByEmpDesignation(String empDesignation);

	@Query(value = "select e from Employee e inner join e.empDepartment where e.empDepartment.deptName = ?1")
	public List<Employee> findAllByEmpDepartment(String empDepartment);

	@Query(value = "select d.dept_name, d.dept_description, count(e.id) dept_total_member from org_department d inner join org_employee e on e.emp_department_id = d.id group by d.dept_name, d.dept_description order by dept_total_member desc", nativeQuery = true)
	public List<Object> findEmpCountByDepartment();
}
