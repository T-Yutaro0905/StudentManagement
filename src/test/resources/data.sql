INSERT INTO students (name, kanaName, nickname, mail_address, address, age, gender, remark, is_deleted)
VALUES ('山田太郎', 'ヤマダタロウ', 'やまちゃん', 'Yamachan@example.com', '東京', 28, '男', NULL, 0),
       ('田中花子', 'タナカハナコ', 'ハナ', 'Hana@example.com', '大阪', 34, '女', NULL, 0),
       ('川村友斗', 'カワムラユウト', 'ユッティ', 'Yutty@example.co', '北海道', 26, '男', NULL, 0),
       ('佐藤浩二', 'サトウコウジ', 'こうちゃん', 'Koji.Sato@example.com', '福岡', 22, '男', NULL, 0),
       ('鈴木愛理', 'スズキアイリ', 'あいりん', 'Airi.Suzuki@example.jp', '愛知', 29, '女', NULL, 0);

INSERT INTO students_courses (student_id, course_name, start_date, end_date)
VALUES (1, 'Javaコース', '2024-01-10', '2024-05-10'),
       (2, 'Excelコース', '2024-02-15', '2024-06-15'),
       (3, 'Wordコース', '2024-03-20', '2024-07-20'),
       (4, 'Englishコース', '2024-04-01', '2024-08-01'),
       (5, 'Javaコース', '2024-05-05', '2024-09-05');

INSERT INTO course_application (course_id, student_id, status)
VALUES (1, 1, '仮申込'),
       (2, 2, '本申込'),
       (3, 3, '受講中'),
       (4, 4, '受講終了');