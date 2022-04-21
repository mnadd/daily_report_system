package models.validators;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import actions.views.AttendanceView;
import constants.MessageConst;

public class AttendanceValidator {

    public static List<String> validate(AttendanceView av) {
        List<String> errors = new ArrayList<String>();

        String startError = validateStart(av.getStart());
        if(!startError.equals("")) {
            errors.add(startError);
        }

        return errors;

    }

    private static String validateStart(LocalTime start) {
        if(start == null) {
            return MessageConst.E_START.getMessage();
        }
        return "";
    }
}

