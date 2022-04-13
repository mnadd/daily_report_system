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

    private LocalTime start;

    private LocalTime finish;

    private LocalTime actualTime;

    private LocalTime overTime;

    private Integer permitFlag;

}
