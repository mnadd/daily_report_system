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
import services.EmployeeService;
import services.TimecardApplicationService;

public class TimecardApplicationAction extends ActionBase {

    private TimecardApplicationService service;
    private AttendanceService attservice;
    private EmployeeService empService;

    @Override
    public void process() throws ServletException, IOException {

        service = new TimecardApplicationService();
        attservice = new AttendanceService();
        empService = new EmployeeService();

        invoke();
        service.close();
        attservice.close();
        empService.close();
    }

    public void index() throws ServletException, IOException {

        int page = getPage();
        List<TimecardApplicationView> timecardApplications = service.getPerPage(page);

        long appCount = service.countAll();

        putRequestScope(AttributeConst.APPLICATIONS, timecardApplications);
        putRequestScope(AttributeConst.APP_COUNT, appCount);
        putRequestScope(AttributeConst.PAGE, page);
        putRequestScope(AttributeConst.MAX_ROW, JpaConst.ROW_PER_PAGE);

        String flush = getSessionScope(AttributeConst.FLUSH);
        if (flush != null) {
            putRequestScope(AttributeConst.FLUSH, flush);
            removeSessionScope(AttributeConst.FLUSH);
        }

        forward(ForwardConst.FW_APP_INDEX);
    }

    public void appIndex() throws ServletException, IOException {

        EmployeeView loginEmployee = (EmployeeView) getSessionScope(AttributeConst.LOGIN_EMP);

        int page = getPage();
        List<TimecardApplicationView> TimecardApplications = service.getMinePerPage(loginEmployee, page);

        long myTimecardApplicationsCount = service.countAllMine(loginEmployee);

        putRequestScope(AttributeConst.APPLICATIONS,TimecardApplications);
        putRequestScope(AttributeConst.APP_COUNT, myTimecardApplicationsCount);
        putRequestScope(AttributeConst.PAGE, page);
        putRequestScope(AttributeConst.MAX_ROW, JpaConst.ROW_PER_PAGE);

        String flush = getSessionScope(AttributeConst.FLUSH);
        if(flush != null) {
            putRequestScope(AttributeConst.FLUSH, flush);
            removeSessionScope(AttributeConst.FLUSH);
        }
        forward(ForwardConst.FW_APP_APPINDEX);

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

            EmployeeView ev = (EmployeeView) getSessionScope(AttributeConst.LOGIN_EMP);
            LocalDate day =  LocalDate.parse(getRequestParam(AttributeConst.APP_DATE));
            AttendanceView av = attservice.findOne(ev, day);
            LocalTime time =  LocalTime.parse(getRequestParam(AttributeConst.APP_TIME));

            TimecardApplicationView apv = new TimecardApplicationView(
                    null,
                    ev,
                    av,
                    day,
                    toNumber(getRequestParam(AttributeConst.APP_TYPE_FLAG)),
                    time,
                    getRequestParam(AttributeConst.APP_CONTENT),
                    null,
                    toNumber(getRequestParam(AttributeConst.APP_APPROVE)),
                    null);


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

    public void show() throws ServletException, IOException {

        TimecardApplicationView apv = service.findOne(toNumber(getRequestParam(AttributeConst.APP_ID)));

        if(apv == null) {
            forward(ForwardConst.FW_ERR_UNKNOWN);
        } else {
            putRequestScope(AttributeConst.APPLICATION, apv);
            forward(ForwardConst.FW_APP_SHOW);
    }
        String flushError = getSessionScope(AttributeConst.FLUSHERROR);
        if(flushError != null) {
            putRequestScope(AttributeConst.FLUSHERROR, flushError);
            removeSessionScope(AttributeConst.FLUSHERROR);
        }
    }

    public void edit() throws ServletException, IOException {
        TimecardApplicationView apv = service.findOne(toNumber(getRequestParam(AttributeConst.APP_ID)));
        EmployeeView ev =(EmployeeView) getSessionScope(AttributeConst.LOGIN_EMP);

        if(apv == null || ev.getId() != apv.getEmployee().getId()) {
            forward(ForwardConst.FW_ERR_UNKNOWN);
        } else {
            putRequestScope(AttributeConst.TOKEN, getTokenId());
            putRequestScope(AttributeConst.APPLICATION, apv);

            forward(ForwardConst.FW_APP_EDIT);
        }


    }

    public void update() throws ServletException, IOException {
        if(checkToken()) {
            TimecardApplicationView apv = service.findOne(toNumber(getRequestParam(AttributeConst.APP_ID)));

            apv.setTimecardApplicationDate(toLocalDate(getRequestParam(AttributeConst.APP_DATE)));
            apv.setTypeFlag(toNumber(getRequestParam(AttributeConst.APP_TYPE_FLAG)));
            apv.setTime(toLocalTime(getRequestParam(AttributeConst.APP_TIME)));
            apv.setAppContent(getRequestParam(AttributeConst.APP_CONTENT));

            List<String> errors = service.update(apv);

            if(errors.size() > 0) {
                putRequestScope(AttributeConst.TOKEN, getTokenId());
                putRequestScope(AttributeConst.APPLICATION, apv);
                putRequestScope(AttributeConst.ERR, errors);

                forward(ForwardConst.FW_APP_EDIT);
            }else {
                putSessionScope(AttributeConst.FLUSH, MessageConst.I_UPDATED.getMessage());
                redirect(ForwardConst.ACT_ATT, ForwardConst.CMD_INDEX);
            }
        }
    }

    public void approve() throws ServletException, IOException {

            TimecardApplicationView apv =service.findOne(toNumber(getRequestParam(AttributeConst.APP_ID)));

            LocalTime time = apv.getTime();

            LocalDate day = apv.getTimecardApplicationDate();
            EmployeeView e = apv.getEmployee();
            AttendanceView av = attservice.findOne(e, day);

            if(apv.getTypeFlag() == JpaConst.TYPE_START) {
                av.setStart(time);
            } else {
                av.setFinish(time);
            }

            attservice.update(av);
            service.approve(toNumber(getRequestParam(AttributeConst.APP_ID)));
            putSessionScope(AttributeConst.FLUSH, MessageConst.I_APPROVED.getMessage());
            redirect(ForwardConst.ACT_APP, ForwardConst.CMD_INDEX);

    }


    public void approveFalse() throws ServletException, IOException {

            TimecardApplicationView apv = service.findOne(toNumber(getRequestParam(AttributeConst.APP_ID)));
            apv.setComment(getRequestParam(AttributeConst.APP_COMMENT));

            List<String> errors = service.approveFalse(apv);

            if(errors.size() > 0) {
                putRequestScope(AttributeConst.TOKEN, getTokenId());
                putRequestScope(AttributeConst.APPLICATION, apv);
                putRequestScope(AttributeConst.ERR, errors);
                putSessionScope(AttributeConst.FLUSHERROR, MessageConst.E_COMMENT.getMessage());

                forward(ForwardConst.FW_APP_SHOW);
            }else {
                putSessionScope(AttributeConst.FLUSH, MessageConst.I_APPROVEFALSE.getMessage());
                redirect(ForwardConst.ACT_APP, ForwardConst.CMD_INDEX);
            }
        }
}






