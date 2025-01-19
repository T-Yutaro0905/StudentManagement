INSERT INTO students (name, kanaName, nickname, mail_address, address, age, gender, remark, is_deleted)
VALUES ('YamadaTaro', 'YamadaTaro', 'Yamachan', 'Yamachan@example.com', 'TokyoShibuya', 28, 'Male', NULL, 0),
       ('TanakaHanako', 'TanakaHanako', 'Hana', 'Hana@example.com', 'OsakaNaniwa', 34, 'Female', NULL, 0),
       ('KawamuraYuto', 'KawamuraYuto', 'Yutty', 'Yutty@example.co', 'HokkaidoSapporo', 26, 'Male', NULL, 0),
       ('SatoKoji', 'SatoKoji', 'Ko-chan', 'Koji.Sato@example.com', 'FukuokaHakata', 22, 'Male', NULL, 0),
       ('SuzukiAiri', 'SuzukiAiri', 'Airin', 'Airi.Suzuki@example.jp', 'NagoyaAtsuta', 29, 'Female', NULL, 0);

INSERT INTO students_courses (student_id, course_name, start_date, end_date)
VALUES (1, 'Javacourse', '2024-01-10', '2024-05-10'),
       (2, 'Excelcourse', '2024-02-15', '2024-06-15'),
       (3, 'Wordcourse', '2024-03-20', '2024-07-20'),
       (4, 'Englishcourse', '2024-04-01', '2024-08-01'),
       (5, 'Javacourse', '2024-05-05', '2024-09-05'),
       (12, 'Javacourse', '2024-10-26', '2025-10-26'),
       (13, 'AWScourse', '2024-11-06', '2025-11-06'),
       (14, 'AWScourse', '2024-11-06', '2025-11-06'),
       (15, 'AWScourse', '2024-11-08', '2025-11-08'),
       (16, 'AWScourse', '2024-11-08', '2025-11-08');

INSERT INTO course_applications (course_id, student_id, status)
VALUES (1, 1, 'karimoushikomi'),
       (2, 2, 'honmoushikomi'),
       (3, 3, 'jyukoutyu'),
       (4, 4, 'jyukousyuuryou');