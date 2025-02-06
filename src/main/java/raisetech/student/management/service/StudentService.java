package raisetech.student.management.service;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import raisetech.student.management.controller.converter.StudentConverter;
import raisetech.student.management.data.CourseApplication;
import raisetech.student.management.data.Student;
import raisetech.student.management.data.StudentCourse;
import raisetech.student.management.domain.StudentCourseStatus;
import raisetech.student.management.domain.StudentDetail;
import raisetech.student.management.repository.StudentRepository;

/**
 * 受講生情報を取り扱うサービスです。
 * 受講生の検索や登録・更新処理を行います。
 */
@Service
public class StudentService {

  private StudentRepository repository;
  private StudentConverter converter;

  @Autowired
  public StudentService(StudentRepository repository, StudentConverter converter) {
    this.repository = repository;
    this.converter = converter;
  }

  /**
   * 受講生詳細の一覧検索です。全件検索を行うので、条件指定は行いません。
   *
   * @return 受講生詳細一覧（全件）
   */
  public List<StudentDetail> searchStudentList() {
    List<Student> studentList = repository.searchStudentList();
    List<StudentCourse> studentCourseList = repository.searchStudentCourseList();
    return converter.convertStudentDetails(studentList, studentCourseList);
  }

  /**
   * 受講生詳細検索です。IDに紐づく受講生情報を取得したあと、その受講生に紐づく受講生コース情報を取得して設定します。
   *
   * @param id 受講生ID
   * @return 受講生詳細
   */
  public StudentDetail searchStudent(String id) {
    Student student = repository.searchStudent(id);
    List<StudentCourse> studentCourse = repository.searchStudentCourse(student.getId());
    return new StudentDetail(student, studentCourse);
  }

  /**
   * 申し込み状況の全件検索
   * 条件指定なし
   *
   * @return 申し込み状況の一覧
   */
  public List<StudentCourseStatus> searchStudentCourseStatusList() {
    List<Student> studentList = repository.searchStudentList();
    List<StudentCourse> studentCourseList = repository.searchStudentCourseList();
    List<CourseApplication> courseApplicationList = repository.searchCourseApplicationList();
    return converter.convertStudentCourseStatusList(studentList, studentCourseList, courseApplicationList);
  }

  /**
   * 申し込み状況詳細の検索
   *
   * @param id 受講生ID
   * @return 申し込み状況
   */
  public List<StudentCourseStatus> searchStudentCourseStatus(String id) {
    Student student = repository.searchStudent(id);
    List<Student> studentList = List.of(student);
    List<StudentCourse> studentCourseList = repository.searchStudentCourseList();
    List<CourseApplication> courseApplicationList = repository.searchCourseApplicationList();
    return converter.convertStudentCourseStatusList(studentList, studentCourseList, courseApplicationList);
  }

  /**
   * 受講生詳細の登録を行います。 受講生と受講生コース情報を個別に登録し、受講生コース情報には受講生情報を紐づける値とコース開始日、コース終了日を設定します。
   *
   * @param studentDetail 　受講生詳細
   * @return　登録情報を付与した受講生詳細
   */
  @Transactional
  public StudentDetail registerStudent(StudentDetail studentDetail) {
    Student student = studentDetail.getStudent();

    repository.registerStudent(student);
    for (StudentCourse studentCourse : studentDetail.getStudentCourseList()) {
      initStudentsCourse(studentCourse, student);
      repository.registerStudentCourse(studentCourse);
    }
    return studentDetail;
  }

  /**
   * 申し込み状況詳細登録
   *
   * @param studentCourseStatus　申し込み状況詳細
   * @return　登録情報を付与した受講生の申し込み
   */
  @Transactional
  public StudentCourseStatus registerStudentCourseStatus(StudentCourseStatus studentCourseStatus) {
    Student student = studentCourseStatus.getStudent();

    repository.registerStudent(student);
    for (StudentCourse studentCourse : studentCourseStatus.getStudentCourseList()) {
      initStudentsCourse(studentCourse, student);
      repository.registerStudentCourse(studentCourse);

      for (CourseApplication courseApplication : studentCourseStatus.getCourseApplicationList()) {
        initCourseApplication(courseApplication, studentCourse);
        repository.registerCourseApplication(courseApplication);
      }
    }
    return studentCourseStatus;
  }

  /**
   * 受講生コース情報を登録する際の初期情報を設定する。
   *
   * @param studentCourse 　受講生コース情報
   * @param student       　受講生
   */
  private void initStudentsCourse(StudentCourse studentCourse, Student student) {
    LocalDateTime now = LocalDateTime.now();

    studentCourse.setStudentId(student.getId());
    studentCourse.setStartDate(now);
    studentCourse.setEndDate(now.plusYears(1));
  }

  /**
   * 申し込み状況を登録する際の初期情報を設定
   *
    * @param courseApplication 申し込み状況
   * @param studentCourse 受講生コース情報
   */
  private void initCourseApplication(CourseApplication courseApplication, StudentCourse studentCourse) {
    courseApplication.setCourseId(studentCourse.getId());
    courseApplication.setStudentId(studentCourse.getStudentId());
  }

  /**
   * 受講生詳細の更新を行います。　受講生と受講生コース情報をそれぞれ更新します。
   *
   * @param studentDetail 　受講生詳細
   */
  @Transactional
  public void updateStudent(StudentDetail studentDetail) {
    repository.updateStudent(studentDetail.getStudent());
    for (StudentCourse studentCourse : studentDetail.getStudentCourseList()) {
      repository.updateStudentCourse(studentCourse);
    }
  }

  /**
   * 申し込み状況詳細の更新
   *
   * @param studentCourseStatus 申し込み状況詳細
   */
  @Transactional
  public void updateStudentCourseStatus(StudentCourseStatus studentCourseStatus) {
    repository.updateStudent(studentCourseStatus.getStudent());
    for (StudentCourse studentCourse : studentCourseStatus.getStudentCourseList()) {
      repository.updateStudentCourse(studentCourse);
      for (CourseApplication courseApplication : studentCourseStatus.getCourseApplicationList()) {
        repository.updateCourseApplication(courseApplication);
      }
    }
  }
}
