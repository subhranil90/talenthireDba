package com.myorg.talenthire.talenthiredba.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.myorg.talenthire.pojo.model.Candidate;

public interface CandidateRepository extends JpaRepository<Candidate, Integer> {
	public List<Candidate> findByCandidateName(String candidateName);
	public List<Candidate> findByCandidateContactNo(String candidateContactNo);
	public List<Candidate> findByCandidateAadharNo(String candidateAadharNo);

	@Query(value="select c.candidateName, c.candidateDateOfBirth, c.candidateInterview.interviewDate, "
			+ "c.candidateInterview.interviewStatus, c.candidateInterview.interviewProject.projectName, "
			+ "c.candidateInterview.interviewProject.projectAccount, c.candidateInterview.interviewProject.projectSkillset "
			+ "from Candidate c inner join c.candidateInterview inner join c.candidateInterview.interviewProject "
			+ "where c.candidateName = :candidateName and c.candidateContactNo = :candidateContact")
	public List<Object> findInterviewDetail(@Param(value = "candidateName") String candidateName, @Param(value = "candidateContact") String candidateContact);
}
