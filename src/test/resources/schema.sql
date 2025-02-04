CREATE TABLE IF NOT EXISTS students
(
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    kanaName VARCHAR(50) NOT NULL,
    nickname VARCHAR(50),
    mail_address VARCHAR(50) NOT NULL,
    address VARCHAR(50),
    age INT NOT NULL,
    gender VARCHAR(10),
    remark TEXT,
    is_deleted boolean
);

CREATE TABLE IF NOT EXISTS students_courses
(
    id INT AUTO_INCREMENT PRIMARY KEY,
    student_id INT NOT NULL,
    course_name VARCHAR(50) NOT NULL,
    start_date TIMESTAMP,
    end_date TIMESTAMP,
    FOREIGN KEY (student_id) REFERENCES students(id)
);

CREATE TABLE IF NOT EXISTS course_application
(
    id INT AUTO_INCREMENT PRIMARY KEY,
    course_id INT NOT NULL,
    student_id INT NOT NULL,
    status VARCHAR(100) NOT NULL,
    FOREIGN KEY (course_id) REFERENCES students_courses(id),
    FOREIGN KEY (student_id) REFERENCES students(id)
);
