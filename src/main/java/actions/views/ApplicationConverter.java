package actions.views;

import java.util.ArrayList;
import java.util.List;

import models.TimecardApplication;

public class ApplicationConverter {

    public static TimecardApplication toModel(TimecardApplicationView apv) {
        return new TimecardApplication(
                apv.getId(),
                EmployeeConverter.toModel(apv.getEmployee()),
                AttendanceConverter.toModel(apv.getAttendance()),
                apv.getApplicationDate(),
                apv.getTypeFlag(),
                apv.getTime(),
                apv.getAppContent(),
                apv.getComment(),
                apv.getAppFlag());
    }

    public static TimecardApplicationView toView(TimecardApplication ap) {
        if(ap == null) {
            return null;
        }
        return new TimecardApplicationView(
                ap.getId(),
                EmployeeConverter.toView(ap.getEmployee()),
                AttendanceConverter.toView(ap.getAttendance()),
                ap.getApplicationDate(),
                ap.getTypeFlag(),
                ap.getTime(),
                ap.getAppContent(),
                ap.getComment(),
                ap.getAppFlag());
    }

    public static List<TimecardApplicationView> toViewList(List<TimecardApplication> list) {
        List<TimecardApplicationView> evs = new ArrayList<>();

        for(TimecardApplication ap : list) {
            evs.add(toView(ap));
        }
        return evs;
    }

    public static void copyViewToModel(TimecardApplication ap, TimecardApplicationView apv) {
        ap.setId(apv.getId());
        ap.setEmployee(EmployeeConverter.toModel(apv.getEmployee()));
        ap.setAttendance(AttendanceConverter.toModel(apv.getAttendance()));
        ap.setApplicationDate(apv.getApplicationDate());
        ap.setTypeFlag(apv.getTypeFlag());
        ap.setTime(apv.getTime());
        ap.setAppContent(apv.getAppContent());
        ap.setComment(apv.getComment());
        ap.setAppFlag(apv.getAppFlag());
    }

}
