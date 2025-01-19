package raisetech.student.management.domain;

import jakarta.validation.Valid;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import raisetech.student.management.data.CourseApplication;
import raisetech.student.management.data.Student;
import raisetech.student.management.data.StudentCourse;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudentCourseStatus {

  @Valid
  public Student student;

  @Valid
  public List<StudentCourse> studentCourseList;

  @Valid
  public List<CourseApplication> courseApplicationList;
}
