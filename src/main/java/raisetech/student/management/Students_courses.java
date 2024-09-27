package raisetech.student.management;

import java.sql.Timestamp;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Students_courses {

 private String id;
 private String student_id;
 private String course;
 private Timestamp start_time;
 private Timestamp end_time;
}
