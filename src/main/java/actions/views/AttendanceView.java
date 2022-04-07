package actions.views;

import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AttendanceView {

    private Integer id;

    private EmployeeView employee;

    private LocalDate attendanceDate;

    private LocalDateTime in;

    private LocalDateTime out;

    private Integer permitFlag;

}
