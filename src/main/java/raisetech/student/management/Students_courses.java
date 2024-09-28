package raisetech.student.management;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Students_courses {

  private String id;
  private String student_id;
  private String course;
  private LocalDateTime start_time;
  private LocalDateTime end_time;
}
