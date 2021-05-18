package com.myorg.talenthire.talenthiredba.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myorg.talenthire.pojo.appexception.DBException;
import com.myorg.talenthire.pojo.model.Candidate;
import com.myorg.talenthire.pojo.model.Department;
import com.myorg.talenthire.pojo.model.Employee;
import com.myorg.talenthire.pojo.model.Interview;
import com.myorg.talenthire.pojo.model.Project;
import com.myorg.talenthire.talenthiredba.service.CoreDBService;

@RestController
@RequestMapping(value = "/thdb")
public class CoreDBController {
	@Autowired
	private CoreDBService coreDBService;
	private static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd";

	public CoreDBController() {
	}

	public CoreDBController(CoreDBService coreDBService) {
		this.coreDBService = coreDBService;
	}

	@GetMapping(value = "/getAllEmp")
	public List<Employee> getAllEmp() {
		return coreDBService.getAllEmployee();
	}

	@GetMapping(value = "/getAllDept")
	public List<Department> getAllDept() {
		return coreDBService.getAllDepartment();
	}

	@GetMapping(value = "/getEmpById/{empId}")
	public Employee getEmpById(@PathVariable int empId) {
		return coreDBService.findById(empId);
	}

	@GetMapping(value = "/getEmpByName/{empName}")
	public Employee getEmpByName(@PathVariable String empName) {
		return coreDBService.findByEmpName(empName);
	}

	@GetMapping(value = "/getEmpByDesignation/{empDesignation}")
	public List<Employee> getEmpByDesignation(@PathVariable String empDesignation) {
		return coreDBService.findAllByEmpDesignation(empDesignation);
	}

	@GetMapping(value = "/getEmpByDeptName/{empDept}")
	public List<Employee> getEmpByDeptName(@PathVariable String empDept) {
		return coreDBService.findAllByEmpDeptName(empDept);
	}

	@GetMapping(value = "/getEmpCountByDept")
	public List<Object> getEmpCountByDept() {
		return coreDBService.findEmployeeCountByDepartment();
	}

	@GetMapping(value = "/getTopThreeDept")
	public List<Object> getTopThreeDept() {
		return coreDBService.getHighestThreeDept();
	}

	@GetMapping(value = "/getAllProjects")
	public List<Project> getAllProjects() {
		return coreDBService.getAvailableProject();
	}

	@GetMapping(value = "/getProjectByAccount/{accountName}")
	public List<Project> getAccountWiseAvailableProject(@PathVariable String accountName) {
		return coreDBService.getAccountWiseAvailableProject(accountName);
	}

	@GetMapping(value = "/getInterviewsForAllProjects")
	public List<Object> getInterviewsForAllProject() {
		return coreDBService.getAllProjectInterviewDetail();
	}

	@GetMapping(value = "/getInterviewForSelectedDate/{interviewDate}")
	public List<Interview> getInterviewForSelectedDate(@PathVariable String interviewDate) {
		LocalDate selectedDate = LocalDate.parse(interviewDate,
				DateTimeFormatter.ofPattern(DEFAULT_DATE_FORMAT, Locale.US));
		return coreDBService.getInterviewBydate(selectedDate);
	}

	@GetMapping(value = "/getInterviewForDateRange/{startDate}/{endDate}")
	public List<Interview> getInterviewForDateRange(@PathVariable(value = "startDate") String startDate,
			@PathVariable(value = "endDate") String endDate) {
		LocalDate beginDate = LocalDate.parse(startDate, DateTimeFormatter.ofPattern(DEFAULT_DATE_FORMAT, Locale.US))
				.atStartOfDay().toLocalDate();
		LocalDate finalDate = LocalDate.parse(endDate, DateTimeFormatter.ofPattern(DEFAULT_DATE_FORMAT, Locale.US))
				.atStartOfDay().toLocalDate();
		return coreDBService.getInterviewDateByDateRange(beginDate, finalDate);
	}

	@GetMapping(value = "/getInterviewList")
	public List<Object> getInterviewList() {
		return coreDBService.getAllInterview();
	}

	@GetMapping(value = "/getInterviewListPerSlot")
	public List<Object> getInterviewListPerSlot() {
		return coreDBService.getAllInterviewSlotWise();
	}

	@GetMapping(value = "/searchCandidateByName/{candidateName}")
	public List<Candidate> searchCandidateByName(@PathVariable(value = "candidateName") String candidateName) {
		return coreDBService.getByCandidateName(candidateName);
	}

	@GetMapping(value = "/searchCandidateInterview/{candidateName}/{candidateContact}")
	public List<Object> getInterviewDetail(@PathVariable(value = "candidateName") String candidateName,
			@PathVariable(value = "candidateContact") String candidateContact) {
		return coreDBService.getInterviewDetail(candidateName, candidateContact);
	}
	
	@PostMapping(value = "/updt/addNewCandidate")
	public String addNewCandidate(@RequestBody Candidate newCandidate) {
		return coreDBService.createNewCandidate(newCandidate);
	}
	
	@PutMapping(value = "/updt/updateCandidateData")
	public String updateCandidateData(@RequestBody Candidate updatedCandidate) throws DBException {
		return coreDBService.updateExistingCandidate(updatedCandidate);
	}
	
	@DeleteMapping(value = "/updt/removeCandidate/{candidateName}/{candidateAadharNo}")
	public String removeCandidate(@PathVariable(value = "candidateName") String candidateName, @PathVariable(value = "candidateAadharNo") String candidateAadharNo) {
		return coreDBService.deleteExistingCandidateByNameAndAadhar(candidateName, candidateAadharNo);
	}
}
