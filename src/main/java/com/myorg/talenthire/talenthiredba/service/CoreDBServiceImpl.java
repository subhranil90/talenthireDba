package com.myorg.talenthire.talenthiredba.service;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.myorg.talenthire.pojo.appexception.DBException;
import com.myorg.talenthire.pojo.model.Candidate;
import com.myorg.talenthire.pojo.model.Department;
import com.myorg.talenthire.pojo.model.Employee;
import com.myorg.talenthire.pojo.model.Interview;
import com.myorg.talenthire.pojo.model.Project;
import com.myorg.talenthire.talenthiredba.constants.DBAppConstants;
import com.myorg.talenthire.talenthiredba.dao.CandidateRepository;
import com.myorg.talenthire.talenthiredba.dao.DepartmentRepository;
import com.myorg.talenthire.talenthiredba.dao.EmployeeRepository;
import com.myorg.talenthire.talenthiredba.dao.InterviewRepository;
import com.myorg.talenthire.talenthiredba.dao.ProjectRepository;

@Service
public class CoreDBServiceImpl implements CoreDBService {
	@Autowired
	private EmployeeRepository employeeRepo;

	@Autowired
	private DepartmentRepository departmentRepo;

	@Autowired
	private ProjectRepository projectRepo;

	@Autowired
	private InterviewRepository interviewRepo;

	@Autowired
	private CandidateRepository candidateRepo;

	public CoreDBServiceImpl() {
	}

	public CoreDBServiceImpl(EmployeeRepository employeeRepo) {
		this.employeeRepo = employeeRepo;
	}

	public CoreDBServiceImpl(DepartmentRepository departmentRepo) {
		this.departmentRepo = departmentRepo;
	}

	@PostConstruct
	void checkDependencies() throws DBException {
		if (null == employeeRepo || null == departmentRepo) {
			throw new DBException("Employee / Department is not Initialized");
		}
	}

	@Override
	public List<Employee> getAllEmployee() {
		return employeeRepo.findAll(Sort.by(Sort.Direction.ASC, "empName"));
	}

	@Override
	public List<Department> getAllDepartment() {
		List<Department> deptList = departmentRepo.findAll();
		deptList.sort(new Comparator<Department>() {
			@Override
			public int compare(Department dept1, Department dept2) {
				return dept1.getDeptName().compareToIgnoreCase(dept2.getDeptName());
			}
		});
		return deptList;
	}

	@Override
	public Employee findByEmpName(String empName) {
		return employeeRepo.findByEmpName(empName);
	}

	@Override
	public Employee findById(int empId) {
		return employeeRepo.findById(empId);
	}

	@Override
	public List<Employee> findAllByEmpDesignation(String empDesignation) {
		return employeeRepo.findAllByEmpDesignation(empDesignation);
	}

	@Override
	public List<Employee> findAllByEmpDeptName(String empDept) {
		return employeeRepo.findAllByEmpDepartment(empDept);
	}

	@Override
	public List<Object> findEmployeeCountByDepartment() {
		return employeeRepo.findEmpCountByDepartment();
	}

	@Override
	public List<Object> getHighestThreeDept() {
		return departmentRepo.getTopThreeDepartment().stream().limit(3).collect(Collectors.toList());
	}

	@Override
	public List<Project> getAvailableProject() {
		return projectRepo.findAll();
	}

	@Override
	public List<Project> getAccountWiseAvailableProject(String accountName) {
		return projectRepo.findByProjectAccountOrderByProjectName(accountName);
	}

	@Override
	public List<Object> getAllProjectInterviewDetail() {
		return projectRepo.getProjectWiseInterviewStatus();
	}

	@Override
	public List<Interview> getInterviewBydate(LocalDate interviewDate) {
		return interviewRepo.findByInterviewDateOrderByInterviewSlot(interviewDate);
	}

	@Override
	public List<Interview> getInterviewDateByDateRange(LocalDate startDate, LocalDate endDate) {
		return interviewRepo.findByInterviewDateBetween(startDate, endDate);
	}

	@Override
	public List<Object> getAllInterview() {
		List<Object[]> interviewList = interviewRepo.getTotalInterview();
		List<Object> formattedInterviewList = new LinkedList<>();
		interviewList.forEach(element -> {
			Map<String, Object> interviewElement = new HashMap<>();
			interviewElement.put("Interview Date", element[0]);
			interviewElement.put("Interview Count", element[1]);
			formattedInterviewList.add(interviewElement);
		});
		return formattedInterviewList;
	}

	@Override
	public List<Object> getAllInterviewSlotWise() {
		List<Object[]> interviewList = interviewRepo.getTotalInterviewSlotWise();
		List<Object> formattedInterviewList = new LinkedList<>();
		interviewList.forEach(element -> {
			Map<String, Object> interviewElement = new HashMap<>();
			interviewElement.put("Interview Date", element[0]);
			interviewElement.put("Interview Slot", element[1]);
			interviewElement.put("Interview Count", element[2]);
			formattedInterviewList.add(interviewElement);
		});
		return formattedInterviewList;
	}

	@Override
	public List<Candidate> getByCandidateName(String candidateName) {
		List<Candidate> candidateList = candidateRepo.findByCandidateName(candidateName);
		for (Candidate candidate : candidateList) {
			candidate.setCandidateInterviewId(candidate.getCandidateInterview().getId());
		}
		return candidateList;
	}

	@Override
	public List<Object> getInterviewDetail(String candidateName, String candidateContact) {
		return candidateRepo.findInterviewDetail(candidateName, candidateContact);
	}

	@Override
	public String createNewCandidate(Candidate newCandidate) {
		String responseMessage = null;
		if (null != newCandidate) {
			if (null != newCandidate.getCandidateAadharNo()
					&& newCandidate.getCandidateAadharNo().length() >= DBAppConstants.AADHAR_NO_LENGTH
					&& null != newCandidate.getCandidateName() && null != newCandidate.getCandidateDateOfBirth()
					&& newCandidate.getCandidateInterviewId() > 0) {
				Interview newCandidateInterview = interviewRepo.findById(newCandidate.getCandidateInterviewId()).get();
				newCandidate.setCandidateInterview(newCandidateInterview);
				newCandidate = candidateRepo.save(newCandidate);
				responseMessage = String.format("Candidate Details Saved, Candidate ID: %s", newCandidate.getId());
			}
		}
		return responseMessage;
	}

	@Override
	public String updateExistingCandidate(Candidate updatedCandidate) throws DBException {
		String responseMessage = "Failed to Update Candidate Data";
		Candidate existingCandidate = null;
		if (null != updatedCandidate) {
			if (updatedCandidate.getId() > 0) {
				existingCandidate = candidateRepo.findById(updatedCandidate.getId()).get();
			} else if (updatedCandidate.getId() <= 0 && null != updatedCandidate.getCandidateAadharNo()) {
				existingCandidate = candidateRepo.findByCandidateAadharNo(updatedCandidate.getCandidateAadharNo())
						.get(0);
			} else if (updatedCandidate.getId() <= 0 && null == updatedCandidate.getCandidateAadharNo()
					&& null != updatedCandidate.getCandidateContactNo()) {
				existingCandidate = candidateRepo.findByCandidateContactNo(updatedCandidate.getCandidateContactNo())
						.get(0);
			} else {
				throw new DBException("Please provide Candidate ID / Aadhar No. / Contact No.");
			}
			updatedCandidate.setCandidateInterview(existingCandidate.getCandidateInterview());
			if (null == updatedCandidate.getCandidateAadharNo() || null == updatedCandidate.getCandidateContactNo()
					|| null == updatedCandidate.getCandidateName()) {
				BeanUtils.copyProperties(updatedCandidate, existingCandidate, "id", "candidateName",
						"candidateAadharNo", "candidateContactNo");
			} else {
				BeanUtils.copyProperties(updatedCandidate, existingCandidate, "id");
			}
			candidateRepo.save(existingCandidate);
			responseMessage = String.format("Candidate Details Saved Successfully for Candidate ID: %s",
					existingCandidate.getId());
		} else {
			throw new DBException("Candidate Data not Provided");
		}
		return responseMessage;
	}

	@Override
	@Transactional(isolation = Isolation.READ_COMMITTED)
	public String deleteExistingCandidateByNameAndAadhar(String candidateName, String candidateAadharNo) {
		String responseMessage = String.format("Failed to Delete Candidate '%s'", candidateName);
		List<Candidate> candidateList = candidateRepo.findByCandidateAadharNo(candidateAadharNo);
		if (null != candidateList && candidateList.size() > 0) {
			Candidate candidateToBeDeleted = candidateList.get(0);
			if (candidateToBeDeleted.getCandidateName().equalsIgnoreCase(candidateName)) {
				candidateRepo.delete(candidateToBeDeleted);
				responseMessage = String.format("Candidate '%s' got Deleted Successfully!", candidateName);
			}
		}
		return responseMessage;
	}
}
