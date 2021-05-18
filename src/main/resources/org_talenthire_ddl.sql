-- DML Queries
create table if not exists org_department (
	id INT AUTO_INCREMENT PRIMARY KEY,
	dept_name VARCHAR(100) NOT NULL,
	dept_chargecode	VARCHAR(100),
	dept_description VARCHAR(255)
);

create table if not exists org_employee (
	id INT AUTO_INCREMENT PRIMARY KEY,
	emp_name VARCHAR(100) NOT NULL,
	emp_designation	VARCHAR(100),
	emp_contact_no VARCHAR(20) NOT NULL,
	emp_doj	DATE,
	emp_department_id INT REFERENCES org_department(id)
);

create table if not exists org_hr_portal_cred (
	id INT AUTO_INCREMENT PRIMARY KEY,
	hr_portal_name VARCHAR(100) NOT NULL,
	hr_portal_username VARCHAR(100) NOT NULL,
	hr_portal_password VARCHAR(255) NOT NULL,
	hr_portal_userrole VARCHAR(100) NOT NULL
);

create table if not exists org_project (
	id INT AUTO_INCREMENT PRIMARY KEY,
	project_name VARCHAR(255),
	project_account	VARCHAR(255),
	project_skillset VARCHAR(255)
);

create table if not exists org_interview (
	id INT AUTO_INCREMENT PRIMARY KEY,
	interview_date DATE,
	interview_slot VARCHAR(100),
	interview_status VARCHAR(100),
	interview_project_id INT REFERENCES org_project(id)
);

create table if not exists org_candidate (
	id INT AUTO_INCREMENT PRIMARY KEY,
	cnd_name VARCHAR(100) NOT NULL,
	cnd_dob	DATE NOT NULL,
	cnd_gender VARCHAR(1),
	cnd_aadhar VARCHAR(20) NOT NULL,
	cnd_contact_no VARCHAR(20) NOT NULL,
	cnd_interview_id INT REFERENCES org_interview(id)
);
