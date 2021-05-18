package com.myorg.talenthire.talenthiredba.dao;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.myorg.talenthire.pojo.model.Interview;

public interface InterviewRepository extends JpaRepository<Interview, Integer> {
	public List<Interview> findByInterviewDateOrderByInterviewSlot(LocalDate interviewDate);
	public List<Interview> findByInterviewDateBetween(LocalDate startDate, LocalDate endDate);
	@Query(value="select c.candidateInterview.interviewDate, count(c.id) as interviewCount from Candidate c inner join c.candidateInterview "
			+ "group by c.candidateInterview.interviewDate order by count(c.id) desc")
	public List<Object[]> getTotalInterview();
	@Query(value="select i.interview_date, i.interview_slot, count(c.id) as cnd_count  from org_interview i inner join org_candidate c on c.cnd_interview_id = i.id "
			+ "group by i.interview_date, i.interview_slot "
			+ "order by cnd_count desc", nativeQuery=true)
	public List<Object[]> getTotalInterviewSlotWise();
}
