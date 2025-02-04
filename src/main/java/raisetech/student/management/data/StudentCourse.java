package raisetech.student.management.data;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Schema(description = "受講生コース情報")
@Getter
@Setter
public class StudentCourse {

  private String id;
  private String studentId;
  private String courseName;
  private LocalDateTime startDate;
  private LocalDateTime endDate;

  public  StudentCourse(String id, String studentId, String courseName) {
    this.id = id;
    this.studentId = studentId;
    this.courseName = courseName;
  }

  public StudentCourse() {
  }

  public void setId(String id) {
    this.id = id;
  }

  public void setStudentId(String studentId) {
    this.studentId = studentId;
  }

  public void setCourseName(String courseName) {
    this.courseName = courseName;
  }

  public void setStartDate(LocalDateTime startDate) {
    this.startDate = startDate;
  }

  public void setEndDate(LocalDateTime endDate) {
    this.endDate = endDate;
  }
}
