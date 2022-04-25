package actions.views;

import java.util.ArrayList;
import java.util.List;

import constants.AttributeConst;
import constants.JpaConst;
import models.TimecardApplication;

public class TimecardApplicationConverter {

    public static TimecardApplication toModel(TimecardApplicationView apv) {
        return new TimecardApplication(
                apv.getId(),
                EmployeeConverter.toModel(apv.getEmployee()),
                AttendanceConverter.toModel(apv.getAttendance()),
                apv.getTimecardApplicationDate(),
                apv.getTypeFlag() == null
                        ? null
                        : apv.getTypeFlag() == AttributeConst.TYPE_START.getIntegerValue()
                            ? JpaConst.TYPE_START
                            : JpaConst.TYPE_FINISH,
                apv.getTime(),
                apv.getAppContent(),
                apv.getComment(),
                apv.getAppApprove() == null
                        ? null
                        : apv.getAppApprove() ==  AttributeConst.APPROVE_TRUE.getIntegerValue()
                            ? JpaConst.APPROVE_TRUE
                            : JpaConst.APPROVE_FALSE);
    }

    public static TimecardApplicationView toView(TimecardApplication ap) {
        if(ap == null) {
            return null;
        }
        return new TimecardApplicationView(
                ap.getId(),
                EmployeeConverter.toView(ap.getEmployee()),
                AttendanceConverter.toView(ap.getAttendance()),
                ap.getTimecardApplicationDate(),
                ap.getTypeFlag()== null
                        ? null
                        : ap.getTypeFlag() == JpaConst.TYPE_START
                            ? AttributeConst.TYPE_START.getIntegerValue()
                            : AttributeConst.TYPE_FINISH.getIntegerValue(),
                ap.getTime(),
                ap.getAppContent(),
                ap.getComment(),
                ap.getAppApprove()== null
                ? null
                : ap.getAppApprove() ==  JpaConst.APPROVE_TRUE
                    ? AttributeConst.APPROVE_TRUE.getIntegerValue()
                    : AttributeConst.APPROVE_FALSE.getIntegerValue());
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
        ap.setTimecardApplicationDate(apv.getTimecardApplicationDate());
        ap.setTypeFlag(apv.getTypeFlag());
        ap.setTime(apv.getTime());
        ap.setAppContent(apv.getAppContent());
        ap.setComment(apv.getComment());
        ap.setAppApprove(apv.getAppApprove());
    }

}
