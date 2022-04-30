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
    return errors;
    }

    public static List<String> validateComm(TimecardApplicationView apv) {
        List<String> errors = new ArrayList<String>();

        String commentError = validateComment(apv.getComment());
        if (!commentError.equals("")) {
            errors.add(commentError);
        }
        return errors;
    }


    private static String validateContent(String appContent) {
        if (appContent == null || appContent.equals("")) {
            return MessageConst.E_NOCONTENT.getMessage();
        }
        return "";
    }

    private static String validateComment(String comment) {
        if (comment == null || comment.equals("")) {
            return MessageConst.E_NOCONTENT.getMessage();
        }
        return "";
    }
}
