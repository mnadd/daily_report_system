package actions;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;

import actions.views.AttendanceView;
import actions.views.EmployeeView;
import constants.AttributeConst;
import constants.ForwardConst;
import constants.JpaConst;
import constants.MessageConst;
import services.AttendanceService;

public class AttendanceAction extends ActionBase {

    private AttendanceService service;

    @Override
    public void process() throws ServletException, IOException {

        service = new AttendanceService();

        invoke();

        service.close();
    }

    public void index() throws ServletException, IOException {

        EmployeeView loginEmployee = (EmployeeView) getSessionScope(AttributeConst.LOGIN_EMP);

        int page = getPage();
        List<AttendanceView> attendances = service.getMinePerPage(loginEmployee, page);

        long myattendancesCount = service.countAllMine(loginEmployee);

        putRequestScope(AttributeConst.ATTENDANCES, attendances);
        putRequestScope(AttributeConst.ATT_COUNT, myattendancesCount);
        putRequestScope(AttributeConst.PAGE, page);
        putRequestScope(AttributeConst.MAX_ROW, JpaConst.ATT_ROW_PER_PAGE);

        String flush = getSessionScope(AttributeConst.FLUSH);
        if(flush != null) {
            putRequestScope(AttributeConst.FLUSH, flush);
            removeSessionScope(AttributeConst.FLUSH);
        }

        forward(ForwardConst.FW_ATT_INDEX);

    }

    public void entryNew() throws ServletException, IOException {

        putRequestScope(AttributeConst.TOKEN, getTokenId());


        AttendanceView av = new AttendanceView();
        putRequestScope(AttributeConst.ATTENDANCE, av);

        forward(ForwardConst.FW_ATT_NEW);

    }

    public void create() throws ServletException, IOException {

        if(checkToken()) {

         EmployeeView ev = (EmployeeView) getSessionScope(AttributeConst.LOGIN_EMP);

         AttendanceView av = new AttendanceView(
                null,
                ev,
                null,
                null,
                null,
                null,
                null,
                null);


            putRequestScope(AttributeConst.TOKEN,getTokenId());
            putRequestScope(AttributeConst.ATTENDANCE, av);

            if(getRequestParam(AttributeConst.ATT_START) != null) {
                putSessionScope(AttributeConst.FLUSH, MessageConst.I_STARTED.getMessage());
            } else if(getRequestParam(AttributeConst.ATT_FINISH) != null) {
                putSessionScope(AttributeConst.FLUSH, MessageConst.I_FINISHED.getMessage());
            }

            redirect(ForwardConst.ACT_ATT, ForwardConst.CMD_INDEX);
        }
    }

    public void workFinish() throws ServletException, IOException {

        if(checkToken()) {

         EmployeeView ev = (EmployeeView) getSessionScope(AttributeConst.LOGIN_EMP);

         AttendanceView av = new AttendanceView(
                null,
                ev,
                null,
                null,
                null,
                null,
                null,
                null);


            putRequestScope(AttributeConst.TOKEN,getTokenId());
            putRequestScope(AttributeConst.ATTENDANCE, av);

            if(getRequestParam(AttributeConst.ATT_START) != null) {
                putSessionScope(AttributeConst.FLUSH, MessageConst.I_STARTED.getMessage());
            } else if(getRequestParam(AttributeConst.ATT_FINISH) != null) {
                putSessionScope(AttributeConst.FLUSH, MessageConst.I_FINISHED.getMessage());
            }

            redirect(ForwardConst.ACT_ATT, ForwardConst.CMD_INDEX);
        }
    }
}
