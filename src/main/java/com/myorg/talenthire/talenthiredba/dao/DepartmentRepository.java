package com.myorg.talenthire.talenthiredba.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.myorg.talenthire.pojo.model.Department;

public interface DepartmentRepository extends JpaRepository<Department, Integer> {
	@Query(value = "select e.empDepartment.deptName, e.empDepartment.deptDescription, count(e.id) as empCount "
			+ "from Employee e inner join e.empDepartment group by e.empDepartment.deptName, e.empDepartment.deptDescription "
			+ "order by empCount desc")
	public List<Object> getTopThreeDepartment();
}
