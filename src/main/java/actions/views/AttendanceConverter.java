package actions.views;

import java.util.ArrayList;
import java.util.List;

import models.Attendance;

public class AttendanceConverter {

    public static Attendance toModel(AttendanceView av) {
        return new Attendance(
                av.getId(),
                EmployeeConverter.toModel(av.getEmployee()),
                av.getAttendanceDate(),
                av.getIn(),
                av.getOut(),
                av.getPermitFlag());

    }

    public static AttendanceView toView ( Attendance a) {
        if(a == null) {
            return null;
        }

        return new AttendanceView(
                a.getId(),
                EmployeeConverter.toView(a.getEmployee()),
                a.getAttendanceDate(),
                a.getIn(),
                a.getOut(),
                a.getPermitFlag());
    }

    public static List<AttendanceView> toViewList(List<Attendance> list) {
        List<AttendanceView> evs = new ArrayList<>();

        for(Attendance a : list) {
            evs.add(toView(a));
        }
        return evs;
    }

    public static void copyViewToModel(Attendance a, AttendanceView av) {
        a.setId(av.getId());
        a.setEmployee(EmployeeConverter.toModel(av.getEmployee()));
        a.setAttendanceDate(av.getAttendanceDate());
        a.setIn(av.getIn());
        a.setOut(av.getOut());
        a.setPermitFlag(av.getPermitFlag());
    }

}
