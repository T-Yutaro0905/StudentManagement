package raisetech.student.management.repository;

import java.util.List;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import raisetech.student.management.data.Student;
import raisetech.student.management.data.StudentsCourses;

@Mapper
public interface StudentRepository {

  @Select("SELECT * FROM students")
  List<Student> searchstudentList();

  @Select("SELECT * FROM students_courses")
  List<StudentsCourses> searchstudentsCourseList();

  @Insert("INSERT INTO students(name, furigana, nickname, mail_address, address, age, gender, remark, isDeleted)"
  + "VALUES (#{name}, #{furigana}, #{nickname}, #{mailAddress}, #{address}, #{age}, #{gender}, #{remark}, false)")
  @Options(useGeneratedKeys = true, keyProperty = "id")
  void registerStudent(Student student);
}