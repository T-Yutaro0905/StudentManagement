package raisetech.student.management.controller.converter;

import static org.assertj.core.api.Assertions.assertThat;

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
    List<Student> studentList = List.of();
    List<StudentCourse> studentCourseList = List.of();

    List<StudentDetail> actual = sut.convertStudentDetails(studentList, studentCourseList);

    assertThat(actual.get(0).getStudent()).isEqualTo(studentList);
    assertThat(actual.get(0).getStudentCourseList()).isEqualTo(studentCourseList);
  }

  @Test
  void 受講生のリストと受講生コース情報のリストを渡したときに紐づかない受講生コース情報は情報は除外されること() {
    List<Student> studentList = List.of();
    List<StudentCourse> studentCourseList = List.of();

    List<StudentDetail> actual = sut.convertStudentDetails(studentList, studentCourseList);

    assertThat(actual.get(0).getStudent()).isEqualTo(studentList);
  }

  @Test
  void 受講生のリストと受講生コース情報のリストと申し込み状況のリストを渡して申し込み状況詳細のリストが作成できること() {
    Student student1 = new Student("1", "山田太郎", 28);

    StudentCourse studentCourse1 = new StudentCourse("1", "1", "Javaコース");

    CourseApplication courseApplication = new CourseApplication("1", "1", "1", "本申込");

    List<Student> studentList = List.of();
    List<StudentCourse> studentCourseList = List.of();
    List<CourseApplication> courseApplicationList = List.of(courseApplication);

    List<StudentCourseStatus> actual = sut.convertStudentCourseStatusList(studentList, studentCourseList, courseApplicationList);

    assertThat(actual.get(0).getStudent()).isEqualTo(student1);
    assertThat(actual.get(0).getStudentCourseList()).isEqualTo(studentCourseList);
    assertThat(actual.get(0).getCourseApplicationList()).isEqualTo(courseApplicationList);
  }
}