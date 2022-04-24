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
public class TimecardApplicationView {

    private Integer id;

    private EmployeeView employee;

    private AttendanceView attendance;

    private LocalDate applicationDate;

    private Integer typeFlag;

    private LocalTime time;

    private String appContent;

    private String comment;

    private Integer appFlag;

}
