package models.validators;

import java.util.ArrayList;
import java.util.List;

import actions.views.ApplicationView;
import constants.MessageConst;

public class ApplicationValidator {

    public static List<String> validate(ApplicationView apv) {
        List<String> errors = new ArrayList<String>();

        String contentError = validateContent(apv.getAppContent());
        if (!contentError.equals("")) {
            errors.add(contentError);
        }
    return errors;
    }

    private static String validateContent(String appContent) {
        if (appContent == null || appContent.equals("")) {
            return MessageConst.E_NOCONTENT.getMessage();
        }
        return "";
    }
}
