package raisetech.student.management.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import raisetech.student.management.data.Student;
import raisetech.student.management.data.StudentCourse;
import raisetech.student.management.domain.StudentCourseStatus;
import raisetech.student.management.domain.StudentDetail;
import raisetech.student.management.service.StudentService;

@WebMvcTest(StudentController.class)
class StudentControllerTest {

  @Autowired
  MockMvc mockMvc;

  @MockBean
  private StudentService service;

  private Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

  @Test
  void 受講生詳細の一覧検索が実行できて空のリストが返ってくること() throws Exception {
    when(service.searchStudentList()).thenReturn(List.of((new StudentDetail())));

    mockMvc.perform(MockMvcRequestBuilders.get("/studentList"))
        .andExpect(status().isOk())
        .andExpect(content().json("[{\"student\":null,\"studentCourseList\":null}]"));

    verify(service, times(1)).searchStudentList();
  }

  @Test
  void 受講生詳細の単一検索が実行できて対象となる受講生のリストが返ってくること() throws Exception {
    String id = "999";
    mockMvc.perform(MockMvcRequestBuilders.get("/student/{id}", id))
        .andExpect(status().isOk());

    verify(service, times(1)).searchStudent(id);
  }

  @Test
  void 申し込み状況詳細の全件検索が実行できて空のリストが返ってくること() throws Exception {
    when(service.searchStudentCourseStatusList()).thenReturn(List.of((new StudentCourseStatus())));

    mockMvc.perform(MockMvcRequestBuilders.get("/studentCourseStatusList"))
        .andExpect(status().isOk())
        .andExpect(content().json("[{\"student\":null,\"studentCourseList\":null,\"courseApplicationList\":null}]"));

    verify(service, times(1)).searchStudentCourseStatusList();
  }

  @Test
  void 申し込み状況の検索が実行できて空で返ってくること() throws Exception {
    String id = "999";
    mockMvc.perform(MockMvcRequestBuilders.get("/studentCourseStatus/{id}", id))
        .andExpect(status().isOk());

    verify(service, times(1)).searchStudentCourseStatus(id);
  }

  @Test
  void 受講生詳細の新規登録が実行できて空のリストが返ってくること() throws Exception {
    // リクエストデータは適切に構築して入力チェックの検証も兼ねている。
    // 本来であれば帰りは登録されたデータが入るが、モック化すると意味がないため、レスポンスは作らない。
    mockMvc.perform(MockMvcRequestBuilders.post("/registerStudent").contentType(MediaType.APPLICATION_JSON).content(
        """
              {
               "student" : {\s
                  "name" : "江並公史",\s
                  "kanaName" : "エナミコウジ",\s
                  "nickname" : "コウジ",\s
                  "mailAddress" : "test@example.com",\s
                  "address" : "奈良",\s
                  "age" : "37",\s
                  "gender" : "男性",\s
                  "remark" : ""
               },
               "studentCourseList" : [
               {
                  "courseName" : "Javacourse"
               }
              ]
             }
            """
    )).andExpect(status().isOk());

    verify(service, times(1)).registerStudent(any());
  }

  @Test
  void 申し込み状況の登録が実行できて空で返ってくること() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.post("/registerStudentCourseStatus")
            .contentType(MediaType.APPLICATION_JSON).content(
                """
                              {
                              "student" : {\s
                                    "name" : "江並公史",\s
                                    "kanaName" : "エナミコウジ",\s
                                    "nickname" : "コウジ",\s
                                    "mailAddress" : "test@example.com",\s
                                    "address" : "奈良",\s
                                    "age" : "37",\s
                                    "gender" : "男性",\s
                                    "remark" : ""
                    },
                    "studentCourseList" : [
                    {
                    "courseName" : "Javacourse"
                    }
                    ],
                    "courseApplicationList" : [
                    {
                    "status" : "karimoushikomi"
                    }
                    ]
                    }
                    """
            ))
        .andExpect(status().isOk());

    verify(service, times(1)).registerStudentCourseStatus(any());
  }

  @Test
  void 受講生詳細の更新が実行できて空のリストが返ってくること() throws Exception {
    // リクエストデータは適切に構築して入力チェックの検証も兼ねている。
    mockMvc.perform(MockMvcRequestBuilders.put("/updateStudent")
        .contentType(MediaType.APPLICATION_JSON).content(
        """
            {
               "student" : {
                  "id" : "12",
                  "name" : "江並公史",
                  "kanaName" : "エナミコウジ",
                  "nickname" : "コウジ",
                  "mailAddress" : "test@example.com",
                  "address" : "奈良",
                  "age" : "37",
                  "gender" : "男性",
                  "remark" : ""
               },
               "studentCourseList" : [
               {
                  "id" : "15",
                  "studentId" : "12",
                  "courseName" : "JavaCourse",
                  "startDate" : "2024-04-27T10:50:39.833614",
                  "endDate" : "2024-04-27T10:50:39.833614"
               }
              ]
             }
            """
    )).andExpect(status().isOk());

    verify(service, times(1)).updateStudent(any());
  }

  @Test
  void 申し込み状況の更新が実行できて空で返ってくること() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.put("/updateStudentCourseStatus")
        .contentType(MediaType.APPLICATION_JSON).content(
        """
            {
               "student" : {
                  "id" : "1",
                  "name" : "YamadaTaro",
                  "kanaName" : "YamadaTaro",
                  "nickname" : "Yamachan",
                  "mailAddress" : "Yamachan@example.com",
                  "address" : "TokyoShibuya",
                  "age" : "28",
                  "gender" : "Male",
                  "remark" : ""
               },
               "studentCourseList" : [
               {
                  "id" : "1",
                  "studentId" : "1",
                  "courseName" : "JavaCourse",
                  "startDate" : "2024-01-10T10:50:39.833614",
                  "endDate" : "2024-05-10T10:50:39.833614"
               }
              ],
              "courseApplicationList" : [
              {
              "id" : "1",
              "courseId" : "1",
              "statusId" : "1",
              "status" : "karimousikomi"
             }
             ]
             }
            """
    )).andExpect(status().isOk());

    verify(service, times(1)).updateStudentCourseStatus(any());
  }

  @Test
  void 受講生詳細の例外APIが実行できてステータスが400で返ってくること() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.get("/exception"))
        .andExpect(status().is4xxClientError())
        .andExpect(content().string("このAPIは現在利用できません。古いURLとなっています。"));
  }

  @Test
  void 受講生詳細の受講生で適切な値を入力した時に入力チェックに異常が発生しないこと() {
    Student student = new Student();
    student.setId("1");
    student.setName("江並公史");
    student.setKanaName("エナミコウジ");
    student.setNickname("エナミ");
    student.setMailAddress("test@example.com");
    student.setAddress("奈良県");
    student.setGender("男性");

    Set<ConstraintViolation<Student>> violations = validator.validate(student);

    assertThat(violations.size()).isEqualTo(0);
  }

  @Test
  void 受講生詳細の受講生でIDに数字以外を用いた時に入力チェックに掛かること() {
    Student student = new Student();
    student.setId("テストです。");
    student.setName("江並公史");
    student.setKanaName("エナミコウジ");
    student.setNickname("エナミ");
    student.setMailAddress("test@example.com");
    student.setAddress("奈良県");
    student.setGender("男性");

    Set<ConstraintViolation<Student>> violations = validator.validate(student);

    assertThat(violations.size()).isEqualTo(1);
    assertThat(violations).extracting("message")
        .containsOnly("数字のみ入力するようにしてください。");
  }

  @Test
  void 受講生詳細の受講生コース情報で適切な値を入力した時に入力チェックに異常が発生しないこと() {
    StudentCourse studentCourse = new StudentCourse();
    studentCourse.setId("1");
    studentCourse.setStudentId("1");
    studentCourse.setCourseName("Java");
    studentCourse.setStartDate(LocalDateTime.now());
    studentCourse.setEndDate(LocalDateTime.now().plusYears(1));

    Set<ConstraintViolation<StudentCourse>> violations = validator.validate(studentCourse);

    assertThat(violations.size()).isEqualTo(0);
  }

  @Test
  void 受講生詳細の受講生コース情報でIDに数字以外を用いた時に入力チェックに異常が発生しないこと() {
    StudentCourse studentCourse = new StudentCourse();
    studentCourse.setId("テストです。");
    studentCourse.setStudentId("1");
    studentCourse.setCourseName("Java");
    studentCourse.setStartDate(LocalDateTime.now());
    studentCourse.setEndDate(LocalDateTime.now().plusYears(1));

    Set<ConstraintViolation<StudentCourse>> violations = validator.validate(studentCourse);

    assertThat(violations.size()).isEqualTo(1);
    assertThat(violations).extracting("message")
        .containsOnly("数字のみ入力するようにしてください。");
  }
}