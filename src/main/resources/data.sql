-- Recruiter 1
INSERT INTO recruiter (id, first_name, middle_name, last_name, email, department)
VALUES (1, 'Aisha', 'Oluwatoyin', 'Suleiman', 'aisha.suleiman@quickhire.com', 'Tech');

INSERT INTO job (id, recruiter_id, title, mandatory_requirement, desirable_requirement, minimum_salary, description)
VALUES (1, 1, 'Java Backend Engineer', 'Java + Spring Boot', 'Docker, PostgreSQL', '₦400,000', 'Work on microservices backend systems.');

INSERT INTO post (id, recruiter_id, job_id, post_status)
VALUES (1, 1, 1, 'ACTIVE');

-- Recruiter 2
INSERT INTO recruiter (id, first_name, middle_name, last_name, email, department)
VALUES (2, 'Emeka', 'John', 'Okafor', 'emeka.okafor@quickhire.com', 'Design');

INSERT INTO job (id, recruiter_id, title, mandatory_requirement, desirable_requirement, minimum_salary, description)
VALUES (2, 2, 'UI/UX Designer', 'Figma + Design Thinking', 'Prototyping tools', '₦300,000', 'Work on product design and user flows.');

INSERT INTO post (id, recruiter_id, job_id, post_status)
VALUES (2, 2, 2, 'ACTIVE');
