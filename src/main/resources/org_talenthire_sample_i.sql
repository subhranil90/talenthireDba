-- Insert Queries
insert into org_department (dept_name, dept_chargecode, dept_description)
values ('HR', 'HR100123', 'Human Resources');
insert into org_department (dept_name, dept_chargecode, dept_description)
values ('TECH_DEV', 'TD100123', 'Technology Development');
insert into org_department (dept_name, dept_chargecode, dept_description)
values ('TECH_MGM', 'TM100123', 'Technology Management');
insert into org_department (dept_name, dept_chargecode, dept_description)
values ('BIZ_DEV', 'BD100123', 'Business Development');
insert into org_department (dept_name, dept_chargecode, dept_description)
values ('BIZ_MGM', 'BM100123', 'Business Management');

insert into org_employee (emp_name, emp_designation, emp_contact_no, emp_doj, emp_department_id)
values('Adam', 'Senior HRA', '1234567890', '2018-01-01', 2);
insert into org_employee (emp_name, emp_designation, emp_contact_no, emp_doj, emp_department_id)
values('Katherine', 'HR Manager', '1234567891', '2015-01-01', 2);
insert into org_employee (emp_name, emp_designation, emp_contact_no, emp_doj, emp_department_id)
values('Rahul', 'HR Senior Manager', '1234567882', '2014-01-01', 2);
insert into org_employee (emp_name, emp_designation, emp_contact_no, emp_doj, emp_department_id)
values('Shruti', 'Developer', '1122337890', '2019-07-01', 3);
insert into org_employee (emp_name, emp_designation, emp_contact_no, emp_doj, emp_department_id)
values('Smith', 'Dev Lead', '1122337000', '2018-02-05', 3);
insert into org_employee (emp_name, emp_designation, emp_contact_no, emp_doj, emp_department_id)
values('Sanjay', 'Senior Dev Manager', '7488567890', '2014-09-20', 5);
insert into org_employee (emp_name, emp_designation, emp_contact_no, emp_doj, emp_department_id)
values('Ritu', 'BA BFS', '7218567890', '2020-11-02', 4);
insert into org_employee (emp_name, emp_designation, emp_contact_no, emp_doj, emp_department_id)
values('Bhavna', 'Senior BA BFS', '7996327890', '2017-10-10', 4);
insert into org_employee (emp_name, emp_designation, emp_contact_no, emp_doj, emp_department_id)
values('Sourav', 'Director Business BFS', '8223327890', '2013-05-20', 1);

insert into org_project (project_name, project_account, project_skillset)
values('HF Middleware', 'DBS', 'Java Backend');
insert into org_project (project_name, project_account, project_skillset)
values('Circuit Automation', 'Cisco', 'Java Full Stack');
insert into org_project (project_name, project_account, project_skillset)
values('SP Sales Workflow', 'JP Morgan Chase', 'Dot Net Backend');
insert into org_project (project_name, project_account, project_skillset)
values('SP Sales Workflow', 'JP Morgan Chase', 'Selenium Testing');
insert into org_project (project_name, project_account, project_skillset)
values('iConnect UI Migration', 'Bank of America', 'ReactJS Developer');
insert into org_project (project_name, project_account, project_skillset)
values('iConnect UI Migration', 'Bank of America', 'Business Analyst');
insert into org_project (project_name, project_account, project_skillset)
values('iConnect UI Migration', 'Bank of America', 'Project Manager');

insert into org_interview (interview_date, interview_slot, interview_status, interview_project_id)
values('2021-02-06', '11-1', 'Completed', 1);
insert into org_interview (interview_date, interview_slot, interview_status, interview_project_id)
values('2021-02-13', '9-11', 'Postponed', 4);
insert into org_interview (interview_date, interview_slot, interview_status, interview_project_id)
values('2021-02-13', '11-1', 'Postponed', 4);
insert into org_interview (interview_date, interview_slot, interview_status, interview_project_id)
values('2021-02-20', '9-11', 'Scheduled', 5);
insert into org_interview (interview_date, interview_slot, interview_status, interview_project_id)
values('2021-02-20', '9-11', 'Scheduled', 5);


insert into org_candidate (cnd_name, cnd_dob, cnd_gender, cnd_aadhar, cnd_contact_no, cnd_interview_id)
values('Priyanka', '1995-03-15', 'F', '758842136589', '9876543212', 1);
insert into org_candidate (cnd_name, cnd_dob, cnd_gender, cnd_aadhar, cnd_contact_no, cnd_interview_id)
values('Suman', '1992-04-20', 'M', '758922136589', '9876596312', 2);
insert into org_candidate (cnd_name, cnd_dob, cnd_gender, cnd_aadhar, cnd_contact_no, cnd_interview_id)
values('Gautam', '1987-10-23', 'M', '698542136589', '9876540021', 3);
insert into org_candidate (cnd_name, cnd_dob, cnd_gender, cnd_aadhar, cnd_contact_no, cnd_interview_id)
values('Camelia', '1990-12-01', 'F', '758842136221', '8420543212', 4);
insert into org_candidate (cnd_name, cnd_dob, cnd_gender, cnd_aadhar, cnd_contact_no, cnd_interview_id)
values('Lisa', '1989-03-15', 'F', '98556136589', '9876543001', 5);





