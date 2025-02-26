package raisetech.student.management.controller.converter;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import raisetech.student.management.data.CourseApplication;
import raisetech.student.management.data.Student;
import raisetech.student.management.data.StudentCourse;
import raisetech.student.management.domain.StudentCourseStatus;
import raisetech.student.management.domain.StudentDetail;

public class StudentConverterTest {

  private StudentConverter sut;

  @BeforeEach
  void before() {
    sut = new StudentConverter();
  }

  @Test
  void 受講生のリストと受講生コース情報のリストを渡して受講生詳細のリストが作成できること() {
    Student student = createStudent();
    StudentCourse studentCourse = createStudentCourse("1");

    List<Student> studentList = List.of(student);
    List<StudentCourse> studentCourseList = List.of(studentCourse);

    List<StudentDetail> actual = sut.convertStudentDetails(studentList, studentCourseList);

    assertThat(actual.get(0).getStudent()).isEqualTo(student);
    assertThat(actual.get(0).getStudentCourseList()).isEqualTo(studentCourseList);
  }

  @Test
  void 受講生のリストと受講生コース情報のリストを渡したときに紐づかない受講生コース情報は情報は除外されること() {
    Student student = createStudent();
    StudentCourse studentCourse = createStudentCourse("3");

    List<Student> studentList = List.of(student);
    List<StudentCourse> studentCourseList = List.of(studentCourse);

    List<StudentDetail> actual = sut.convertStudentDetails(studentList, studentCourseList);

    assertThat(actual.get(0).getStudent()).isEqualTo(student);
  }

  @Test
  void 受講生のリストと受講生コース情報のリストと申し込み状況のリストを渡して申し込み状況詳細のリストが作成できること() {
    Student student = createStudent();

    StudentCourse studentCourse = createStudentCourse("1");

    CourseApplication courseApplication = new CourseApplication();
    courseApplication.setId("1");
    courseApplication.setCourseId("1");
    courseApplication.setStudentId("1");
    courseApplication.setStatus("本申込");

    List<Student> studentList = List.of(student);
    List<StudentCourse> studentCourseList = List.of(studentCourse);
    List<CourseApplication> courseApplicationList = List.of(courseApplication);

    List<StudentCourseStatus> actual = sut.convertStudentCourseStatusList(studentList, studentCourseList, courseApplicationList);

    assertThat(actual.get(0).getStudent()).isEqualTo(student);
    assertThat(actual.get(0).getStudentCourseList()).isEqualTo(studentCourseList);
    assertThat(actual.get(0).getCourseApplicationList()).isEqualTo(courseApplicationList);
  }

  private static StudentCourse createStudentCourse(String number) {
    StudentCourse studentCourse = new StudentCourse();
    studentCourse.setId("1");
    studentCourse.setStudentId("1");
    studentCourse.setCourseName("Javaコース");
    studentCourse.setStartDate(LocalDateTime.now());
    studentCourse.setEndDate(LocalDateTime.now().plusYears(1));
    return studentCourse;
  }

  private static Student createStudent() {
    Student student = new Student();
    student.setId("1");
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
}