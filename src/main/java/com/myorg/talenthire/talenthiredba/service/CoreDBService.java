package com.myorg.talenthire.talenthiredba.service;

import java.time.LocalDate;
import java.util.List;

import com.myorg.talenthire.pojo.appexception.DBException;
import com.myorg.talenthire.pojo.model.Candidate;
import com.myorg.talenthire.pojo.model.Department;
import com.myorg.talenthire.pojo.model.Employee;
import com.myorg.talenthire.pojo.model.Interview;
import com.myorg.talenthire.pojo.model.Project;

public interface CoreDBService {
	// From Employee Repository
	public List<Employee> getAllEmployee();

	public Employee findByEmpName(String empName);

	public Employee findById(int empId);

	public List<Employee> findAllByEmpDesignation(String empDesignation);

	public List<Employee> findAllByEmpDeptName(String empDept);

	public List<Object> findEmployeeCountByDepartment();

	// From Department Repository
	public List<Department> getAllDepartment();

	public List<Object> getHighestThreeDept();

	// From Project Repository
	public List<Project> getAvailableProject();

	public List<Project> getAccountWiseAvailableProject(String accountName);

	public List<Object> getAllProjectInterviewDetail();

	// From Interview Repository
	public List<Interview> getInterviewBydate(LocalDate interviewDate);

	public List<Interview> getInterviewDateByDateRange(LocalDate startDate, LocalDate endDate);

	public List<Object> getAllInterview();

	public List<Object> getAllInterviewSlotWise();

	// From Candidate Repository
	public List<Candidate> getByCandidateName(String candidateName);

	public List<Object> getInterviewDetail(String candidateName, String candidateContact);
	
	public String createNewCandidate(Candidate newCandidate);
	
	public String updateExistingCandidate(Candidate existingCandidate) throws DBException;
	
	public String deleteExistingCandidateByNameAndAadhar(String candidateName, String candidateAadharNo);
}
