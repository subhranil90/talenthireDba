package com.myorg.talenthire.talenthiredba.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.myorg.talenthire.pojo.model.Project;

public interface ProjectRepository extends JpaRepository<Project, Integer> {
	public List<Project> findByProjectAccountOrderByProjectName(String accountName);	
	@Query(value = "select p.project_account, p.project_name, i.interview_status, count(i.interview_status) as status_count from org_project p "
			+ "inner join org_interview i on i.interview_project_id = p.id "
			+ "group by p.project_account,p.project_name,i.interview_status " + "order by p.project_account", nativeQuery=true)
	public List<Object> getProjectWiseInterviewStatus();
}
