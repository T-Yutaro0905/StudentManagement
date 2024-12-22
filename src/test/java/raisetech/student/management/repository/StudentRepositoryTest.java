package raisetech.student.management.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import raisetech.student.management.data.Student;
import raisetech.student.management.data.StudentCourse;

@MybatisTest
class StudentRepositoryTest {

  @Autowired
  private StudentRepository sut;

  @Test
  void 受講生の全件検索が行えること() {
    List<Student> actual = sut.search();
    assertThat(actual.size()).isEqualTo(5);

    assertThat(actual.get(0).getName()).isEqualTo("YamadaTaro");
    assertThat(actual.get(0).getKanaName()).isEqualTo("YamadaTaro");
    assertThat(actual.get(0).getNickname()).isEqualTo("Yamachan");
    assertThat(actual.get(0).getMailAddress()).isEqualTo("Yamachan@example.com");
    assertThat(actual.get(0).getAddress()).isEqualTo("TokyoShibuya");
    assertThat(actual.get(0).getAge()).isEqualTo(28);
    assertThat(actual.get(0).getGender()).isEqualTo("Male");
  }

  @Test
  void 受講生の単一検索が行えること() {
    String id = "1";
    Student actual = sut.searchStudent(id);
    assertThat(actual.getId()).isEqualTo(id);

  }

  @Test
  void 受講生コース情報の全体検索が行えること() {
    List<StudentCourse> actual = sut.searchStudentCourseList();
    assertThat(actual.size()).isEqualTo(10);
  }

  @Test
  void 受講生コース情報の単一検索が行えること() {
    String id = "1";
    List<StudentCourse> actual = sut.searchStudentCourse(id);
    assertThat(actual.size()).isEqualTo(1);
  }

  @Test
  void 受講生の登録が行えること() {
    Student student = createStudent();

    sut.registerStudent(student);

    List<Student> actual = sut.search();

    assertThat(actual.size()).isEqualTo(6);
  }

  private static Student createStudent() {
    Student student = new Student();
    student.setName("江並公史");
    student.setKanaName("エナミコウジ");
    student.setNickname("エナミ");
    student.setMailAddress("test@example.com");
    student.setAddress("奈良県");
    student.setAge(36);
    student.setGender("男性");
    student.setRemark("");
    student.setDeleted(false);
    return student;
  }

  @Test
  void 受講生コース情報の登録が行えること() {
    StudentCourse studentCourse = new StudentCourse();
    studentCourse.setStudentId("1");
    studentCourse.setCourseName("JavaCourse");
    studentCourse.setStartDate(LocalDateTime.now());
    studentCourse.setEndDate(LocalDateTime.now().plusYears(1));

    sut.registerStudentCourse(studentCourse);

    List<StudentCourse> actual = sut.searchStudentCourseList();
    assertThat(actual.size()).isEqualTo(11);
  }

  @Test
  void 受講生の更新が行えること() {
    Student student = createStudent();

    sut.updateStudent(student);

    Student actual = sut.searchStudent("1");
    assertThat(actual.getName()).isEqualTo("YamadaTaro");
  }

  @Test
  void 受講生コース情報のコース名の更新が行えること() {
    StudentCourse studentCourse = new StudentCourse();
    studentCourse.setId("1");
    studentCourse.setStudentId("1");
    studentCourse.setCourseName("AWScourse");

    sut.updateStudentCourse(studentCourse);

    StudentCourse updateStudentCourse = sut.searchStudentCourseList().get(0);
    assertThat(updateStudentCourse.getCourseName()).isEqualTo("AWScourse");
  }
}