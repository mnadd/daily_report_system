package actions.views;

import java.util.ArrayList;
import java.util.List;

import models.Application;

public class ApplicationConverter {

    public static Application toModel(ApplicationView apv) {
        return new Application(
                apv.getId(),
                EmployeeConverter.toModel(apv.getEmployee()),
                AttendanceConverter.toModel(apv.getAttendance()),
                apv.getApplicationDate(),
                apv.getTypeFlag(),
                apv.getTime(),
                apv.getAppContent(),
                apv.getComment());
    }

    public static ApplicationView toView(Application ap) {
        if(ap == null) {
            return null;
        }
        return new ApplicationView(
                ap.getId(),
                EmployeeConverter.toView(ap.getEmployee()),
                AttendanceConverter.toView(ap.getAttendance()),
                ap.getApplicationDate(),
                ap.getTypeFlag(),
                ap.getTime(),
                ap.getAppContent(),
                ap.getComment());
    }

    public static List<ApplicationView> toViewList(List<Application> list) {
        List<ApplicationView> evs = new ArrayList<>();

        for(Application ap : list) {
            evs.add(toView(ap));
        }
        return evs;
    }

    public static void copyViewToModel(Application ap, ApplicationView apv) {
        ap.setId(apv.getId());
        ap.setEmployee(EmployeeConverter.toModel(apv.getEmployee()));
        ap.setAttendance(AttendanceConverter.toModel(apv.getAttendance()));
        ap.setApplicationDate(apv.getApplicationDate());
        ap.setTypeFlag(apv.getTypeFlag());
        ap.setTime(apv.getTime());
        ap.setAppContent(apv.getAppContent());
        ap.setComment(apv.getComment());
    }

}
