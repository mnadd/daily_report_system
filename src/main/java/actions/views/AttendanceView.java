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

    private LocalDateTime start;

    private LocalDateTime finish;

    private LocalDateTime actualTime;

    private LocalDateTime overTime;

    private Integer permitFlag;

}
