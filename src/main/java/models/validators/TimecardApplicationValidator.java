package models.validators;

import java.util.ArrayList;
import java.util.List;

import actions.views.TimecardApplicationView;
import constants.MessageConst;

public class TimecardApplicationValidator {

    public static List<String> validate(TimecardApplicationView apv) {
        List<String> errors = new ArrayList<String>();

        String contentError = validateContent(apv.getAppContent());
        if (!contentError.equals("")) {
            errors.add(contentError);
        }

        /*String timeError = validateTime(apv.getTime());
        if(!timeError.equals("")) {
            errors.add(timeError);
        }*/
    return errors;
    }




    private static String validateContent(String appContent) {
        if (appContent == null || appContent.equals("")) {
            return MessageConst.E_NOCONTENT.getMessage();
        }
        return "";
    }
 /*   private static String validateTime(LocalTime time) {
        if(time == null || time.equals("")) {
            return MessageConst.E_NONTIME.getMessage();
        }
        return "";
    }*/
}
