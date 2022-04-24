package actions;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.ServletException;

import actions.views.AttendanceView;
import actions.views.EmployeeView;
import constants.AttributeConst;
import constants.ForwardConst;
import constants.MessageConst;
import services.AttendanceService;
import services.EmployeeService;

public class AttendanceAction extends ActionBase {

    private AttendanceService service;
    private EmployeeService empService;

    @Override
    public void process() throws ServletException, IOException {

        service = new AttendanceService();
        empService = new EmployeeService();

        invoke();

        service.close();
        empService.close();
    }

    public void index() throws ServletException, IOException {

        EmployeeView loginEmployee = (EmployeeView) getSessionScope(AttributeConst.LOGIN_EMP);

        int page = getPage();
        List<AttendanceView> attendances = service.getMinePerPage(loginEmployee, page);

        long myattendancesCount = service.countAllMine(loginEmployee);

        putRequestScope(AttributeConst.ATTENDANCES, attendances);
        putRequestScope(AttributeConst.ATT_COUNT, myattendancesCount);
        putRequestScope(AttributeConst.PAGE, page);

        String flush = getSessionScope(AttributeConst.FLUSH);
        if(flush != null) {
            putRequestScope(AttributeConst.FLUSH, flush);
            removeSessionScope(AttributeConst.FLUSH);
        }
        forward(ForwardConst.FW_ATT_INDEX);
    }

    public void entryNew() throws ServletException, IOException {

        putRequestScope(AttributeConst.TOKEN, getTokenId());
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

             service.create(av);
             putRequestScope(AttributeConst.TOKEN,getTokenId());
             putRequestScope(AttributeConst.ATTENDANCE, av);

             putSessionScope(AttributeConst.FLUSH, MessageConst.I_STARTED.getMessage());

             redirect(ForwardConst.ACT_ATT, ForwardConst.CMD_INDEX);
        }
    }

    public void workfin() throws ServletException, IOException {

        if(checkToken()) {
            EmployeeView ev = (EmployeeView) getSessionScope(AttributeConst.LOGIN_EMP);
            LocalDate attendanceDate = (toLocalDate(getRequestParam(AttributeConst.ATT_DATE)));
            AttendanceView av = service.findOne(ev, attendanceDate);


            List<String> errors = service.workfin(av);

            if (errors.size() > 0) {
                putRequestScope(AttributeConst.TOKEN, getTokenId());
                putRequestScope(AttributeConst.ATTENDANCE, av);
                putRequestScope(AttributeConst.ERR, errors);
                putSessionScope(AttributeConst.FLUSH, MessageConst.E_START.getMessage());

                forward(ForwardConst.FW_ATT_NEW);
            }else {
                putSessionScope(AttributeConst.FLUSH, MessageConst.I_FINISHED.getMessage());

                redirect(ForwardConst.ACT_ATT, ForwardConst.CMD_INDEX);

            }
        }

        }

}