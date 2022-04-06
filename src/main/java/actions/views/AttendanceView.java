package actions.views;

import java.time.LocalDate;
import java.time.LocalTime;

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

    private LocalTime in;

    private LocalTime out;

    private Integer permitFlag;

}
