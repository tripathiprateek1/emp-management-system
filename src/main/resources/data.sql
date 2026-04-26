-- Employees
INSERT INTO employee (name, email, password, designation, date_of_joining, role)
VALUES
('Admin User', 'admin@gmail.com', '$2a$10$abc123', 'Admin', '2022-01-01', 'ADMIN'),
('Manager One', 'manager@gmail.com', '$2a$10$abc123', 'Manager', '2022-02-01', 'MANAGER'),
('Employee One', 'emp@gmail.com', '$2a$10$abc123', 'Developer', '2023-01-01', 'EMPLOYEE');

-- Projects
INSERT INTO project (project_name, description, start_date, end_date, manager_id, project_status)
VALUES
('EMS Project', 'Employee system', '2024-01-01', '2024-06-01', 2, 'IN_PROGRESS');

-- Assignments
INSERT INTO project_assignment (employee_id, project_id, assigned_date)
VALUES
(3, 1, '2024-01-10');