package actions;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.ServletException;

import actions.views.ApplicationView;
import constants.AttributeConst;
import constants.ForwardConst;
import constants.JpaConst;
import services.ApplicationService;

public class ApplicationAction extends ActionBase {

    private ApplicationService service;

    @Override
    public void process() throws ServletException, IOException {

        service = new ApplicationService();

        invoke();
        service.close();
    }

    public void index() throws ServletException, IOException {

        int page = getPage();
        List<ApplicationView> applications = service.getPerPage(page);

        long applicationsCount = service.countAll();

        putRequestScope(AttributeConst.APPLICATIONS, applications);
        putRequestScope(AttributeConst.APP_COUNT, applicationsCount);
        putRequestScope(AttributeConst.PAGE, page);
        putRequestScope(AttributeConst.MAX_ROW, JpaConst.ROW_PER_PAGE);

        String flush = getSessionScope(AttributeConst.FLUSH);
        if (flush != null) {
            putRequestScope(AttributeConst.FLUSH, flush);
            removeSessionScope(AttributeConst.FLUSH);
        }

        forward(ForwardConst.FW_APP_INDEX);
    }

    public void entryNew() throws ServletException, IOException {

        putRequestScope(AttributeConst.TOKEN, getTokenId());

        ApplicationView apv = new ApplicationView();
        apv.setApplicationDate(LocalDate.now());
        putRequestScope(AttributeConst.APPLICATION, apv);

        forward(ForwardConst.FW_APP_NEW);
    }


   /* public void create() throws ServletException, IOException {

        if(checkToken()) {

            EmployeeView ev = (EmployeeView) getSessionScope(AttributeConst.LOGIN_EMP);
            AttendanceView av = service.findOne()
            LocalDate day = LocalDate.parse(getRequestParam(AttributeConst.APP_DATE));;

            ApplicationView apv = new ApplicationView(
                    null,
                    ev,
                    av,
                    day,
                    toNumber(getRequestParam(AttributeConst.APP_TYPE_FLAG)),
                    getRequestParam(AttributeConst.APP_TIME),
                    getRequestParam(AttributeConst.APP_CONTENT),
                    getRequestParam(AttributeConst.APP_CONTENT));
        }


    }*/

}
