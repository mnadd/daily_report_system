package actions;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import javax.servlet.ServletException;

import actions.views.AttendanceView;
import actions.views.EmployeeView;
import actions.views.TimecardApplicationView;
import constants.AttributeConst;
import constants.ForwardConst;
import constants.JpaConst;
import constants.MessageConst;
import services.AttendanceService;
import services.TimecardApplicationService;

public class TimecardApplicationAction extends ActionBase {

    private TimecardApplicationService service;
    private AttendanceService attservice;

    @Override
    public void process() throws ServletException, IOException {

        service = new TimecardApplicationService();
        attservice = new AttendanceService();

        invoke();
        service.close();
        attservice.close();
    }

    public void index() throws ServletException, IOException {

        int page = getPage();
        List<TimecardApplicationView> timecardApplications = service.getPerPage(page);

        long timecardApplicationsCount = service.countAll();

        putRequestScope(AttributeConst.APPLICATIONS, timecardApplications);
        putRequestScope(AttributeConst.APP_COUNT, timecardApplicationsCount);
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

        TimecardApplicationView apv = new TimecardApplicationView();
        apv.setTimecardApplicationDate(LocalDate.now());
        putRequestScope(AttributeConst.APPLICATION, apv);

        forward(ForwardConst.FW_APP_NEW);
    }


    public void create() throws ServletException, IOException {

        if(checkToken()) {
            LocalDate day = null;
            LocalTime time = null;

            EmployeeView ev = (EmployeeView) getSessionScope(AttributeConst.LOGIN_EMP);
            day =  LocalDate.parse(getRequestParam(AttributeConst.APP_DATE));
            AttendanceView av = attservice.findOne(ev, day);
            time =  LocalTime.parse(getRequestParam(AttributeConst.APP_TIME));

            TimecardApplicationView apv = new TimecardApplicationView(
                    null,
                    ev,
                    av,
                    day,
                    toNumber(getRequestParam(AttributeConst.APP_TYPE_FLAG)),
                    time,
                    getRequestParam(AttributeConst.APP_CONTENT),
                    null,
                    toNumber(getRequestParam(AttributeConst.APP_APPROVE)));

           List<String> errors = service.create(apv);

            if(errors.size() > 0) {
                putRequestScope(AttributeConst.TOKEN, getTokenId());
                putRequestScope(AttributeConst.APPLICATION, apv);
                putRequestScope(AttributeConst.ERR, errors);

                forward(ForwardConst.FW_APP_NEW);
            }else {
                putSessionScope(AttributeConst.FLUSH, MessageConst.I_REGISTERED.getMessage());
                redirect(ForwardConst.ACT_ATT, ForwardConst.CMD_INDEX);
            }
        }


    }

}