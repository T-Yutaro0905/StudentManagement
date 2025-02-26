package raisetech.student.management.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import raisetech.student.management.data.CourseApplication;
import raisetech.student.management.data.Student;
import raisetech.student.management.data.StudentCourse;

@MybatisTest
class StudentRepositoryTest {

  @Autowired
  private StudentRepository sut;

  @Test
  void 受講生の全件検索が行えること() {
    List<Student> actual = sut.searchStudentList();
    assertThat(actual.size()).isEqualTo(5);
    assertThat(actual.getFirst().getName()).isEqualTo("山田太郎");
  }

  @Test
  void 受講生の単一検索が行えること() {
    Student actual = sut.searchStudent("1");
    assertThat(actual.getName()).isEqualTo("山田太郎");
  }

  @Test
  void 受講生コース情報の全体検索が行えること() {
    List<StudentCourse> actual = sut.searchStudentCourseList();
    assertThat(actual.size()).isEqualTo(5);
    assertThat(actual.getLast().getStudentId()).isEqualTo("5");
  }

  @Test
  void 受講生コース情報の単一検索が行えること() {
    List<StudentCourse> actual = sut.searchStudentCourse("1");
    assertThat(actual.size()).isEqualTo(1);
    assertThat(actual.getFirst().getId()).isEqualTo("1");
  }

  @Test
  void 申し込み状況の全件検索が行えること() {
    List<CourseApplication> actual = sut.searchCourseApplicationList();
    assertThat(actual.size()).isEqualTo(4);
  }

  @Test
  void 受講生の登録が行えること() {
    Student student = createStudent();

    sut.registerStudent(student);

    List<Student> actual = sut.searchStudentList();

    assertThat(actual.size()).isEqualTo(6);
  }

  private static Student createStudent() {
    Student student = new Student();
    student.setName("江並公史");
    student.setKanaName("エナミコウジ");
    student.setNickname("エナミ");
    student.setMailAddress("test@example.com");
    student.setAddress("奈良");
    student.setAge(36);
    student.setGender("男");
    student.setRemark("");
    student.setDeleted(false);
    return student;
  }

  @Test
  void 受講生コース情報の登録が行えること() {
    StudentCourse studentCourse = new StudentCourse();
    studentCourse.setStudentId("1");
    studentCourse.setCourseName("Javaコース");
    studentCourse.setStartDate(LocalDateTime.now());
    studentCourse.setEndDate(LocalDateTime.now().plusYears(1));

    sut.registerStudentCourse(studentCourse);

    List<StudentCourse> actual = sut.searchStudentCourseList();
    assertThat(actual.size()).isEqualTo(6);
  }

  @Test
  void 申し込み状況の登録が行えること() {
    CourseApplication courseApplication = new CourseApplication();
    courseApplication.setCourseId("1");
    courseApplication.setStudentId("1");
    courseApplication.setStatus("仮申込");

    sut.registerCourseApplication(courseApplication);

    List<CourseApplication> actual = sut.searchCourseApplicationList();

    assertThat(actual.size()).isEqualTo(5);
  }

  @Test
  void 受講生の更新が行えること() {
    Student student = createStudent();

    sut.updateStudent(student);

    Student actual = sut.searchStudent("1");
    assertThat(actual.getName()).isEqualTo("山田太郎");
  }

  @Test
  void 受講生コース情報のコース名の更新が行えること() {
    StudentCourse studentCourse = new StudentCourse();
    studentCourse.setId("1");
    studentCourse.setStudentId("1");
    studentCourse.setCourseName("AWSコース");

    sut.updateStudentCourse(studentCourse);

    StudentCourse actual = sut.searchStudentCourseTest("1");
    assertThat(actual.getCourseName()).isEqualTo("AWSコース");
  }

  @Test
  void 申し込み状況の更新が行えること() {
    CourseApplication courseApplication = new CourseApplication();
    courseApplication.setId("1");
    courseApplication.setCourseId("1");
    courseApplication.setStudentId("1");
    courseApplication.setStatus("受講中");

    sut.updateCourseApplication(courseApplication);

    List<CourseApplication> actual = sut.searchCourseApplicationList();
    assertThat(actual.getFirst().getStatus()).isEqualTo("受講中");
  }
}