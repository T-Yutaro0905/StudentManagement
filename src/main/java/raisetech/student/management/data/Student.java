package raisetech.student.management.data;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import java.util.Objects;
import lombok.Getter;
import lombok.Setter;

@Schema(description = "受講生")
@Getter
@Setter
public class Student {

  @Pattern(regexp = "^\\d+$", message = "数字のみ入力するようにしてください。")
  private String id;

  @NotBlank
  private String name;

  @NotBlank
  private String kanaName;

  @NotBlank
  private String nickname;

  @NotBlank
  private String mailAddress;

  @NotBlank
  private String address;

  private int age;

  private String gender;

  private String remark;

  private boolean isDeleted = false;

  @Override
  public boolean equals(Object other) {
    if (this == other) return true;
    if (other == null || getClass() != other.getClass()) return false;
    Student otherstudent = (Student) other;
    return age == otherstudent.age &&
        Objects.equals(name, otherstudent.name) &&
        Objects.equals(kanaName, otherstudent.kanaName) &&
        Objects.equals(nickname, otherstudent.nickname) &&
        Objects.equals(mailAddress, otherstudent.mailAddress) &&
        Objects.equals(address, otherstudent.address) &&
        Objects.equals(gender, otherstudent.gender);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, kanaName, nickname, mailAddress, address, age, gender);
  }

  public Student(String id, String name, int age) {
    this.id = id;
    this.name = name;
    this.age = age;
  }

  public Student() {
  }

  public void setId(String id) {
    this.id = id;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setKanaName(String kanaName) {
    this.kanaName = kanaName;
  }

  public void setNickname(String nickname) {
    this.nickname = nickname;
  }

  public void setMailAddress(String mailAddress) {
    this.mailAddress = mailAddress;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public void setGender(String gender) {
    this.gender = gender;
  }
}
