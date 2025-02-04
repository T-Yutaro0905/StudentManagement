package raisetech.student.management.data;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Schema(description = "申し込み状況")
@Getter
@Setter
public class CourseApplication {

  @Pattern(regexp = "^\\d+$", message = "数字のみ入力してください")
  private String id;

  private String courseId;

  private String studentId;

  @NotBlank(message = "申し込み状況を入力してください")
  private String status;

  public CourseApplication(String id, String courseId, String studentId, String status) {
    this.id = id;
    this.courseId = courseId;
    this.studentId = studentId;
    this.status = status;
  }
  public  CourseApplication() {
  }
}
