package actions.views;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ApplicationView {

    private Integer id;

    private EmployeeView employee;

    private AttendanceView attendance;

    private LocalDate applicationDate;

    private Integer typeFlag;

    private Integer time;

    private String Content;

    private String comment;

}
