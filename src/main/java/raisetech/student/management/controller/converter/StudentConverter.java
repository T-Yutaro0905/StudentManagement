package raisetech.student.management.controller.converter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;
import raisetech.student.management.data.Student;
import raisetech.student.management.data.StudentsCourses;
import raisetech.student.management.domain.StudentDetail;

@Component
public class StudentConverter {

  public List<StudentDetail> convertStudentDetails(List<Student> students,
      List<StudentsCourses> studentsCourses) {
    List<StudentDetail> studentDetails = new ArrayList<>();
    students.forEach(student -> {
      StudentDetail studentDetail = new StudentDetail();
      studentDetail.setStudent(student);

      List<StudentsCourses> convertStudentsCourses = studentsCourses.stream()
          .filter(studentCourse -> student.getId() !=null && student.getId().equals(studentCourse.getStudentId()))
          .collect(Collectors.toList());

      studentDetail.setStudentsCourses(convertStudentsCourses);
      studentDetails.add(studentDetail);
    });
    return studentDetails;
  }
}
