package constants;

public enum ForwardConst {

    ACT("action"),
    ACT_TOP("Top"),
    ACT_EMP("Employee"),
    ACT_REP("Report"),
    ACT_AUTH("Auth"),
    ACT_ATT("Attendance"),
    ACT_APP("TimecardApplication"),

    CMD("command"),
    CMD_NONE(""),
    CMD_INDEX("index"),
    CMD_SHOW("show"),
    CMD_SHOW_LOGIN("showLogin"),
    CMD_LOGIN("login"),
    CMD_LOGOUT("logout"),
    CMD_NEW("entryNew"),
    CMD_CREATE("create"),
    CMD_EDIT("edit"),
    CMD_UPDATE("update"),
    CMD_DESTROY("destroy"),
    CMD_WORKFIN("workfin"),
    CMD_APPROVE("approve"),
    CMD_APPRPVEFALSE("approveFalse"),
    CMD_APPINDEX("appIndex"),

    FW_ERR_UNKNOWN("error/unknown"),
    FW_TOP_INDEX("topPage/index"),
    FW_LOGIN("login/login"),
    FW_EMP_INDEX("employees/index"),
    FW_EMP_SHOW("employees/show"),
    FW_EMP_NEW("employees/new"),
    FW_EMP_EDIT("employees/edit"),
    FW_REP_INDEX("reports/index"),
    FW_REP_SHOW("reports/show"),
    FW_REP_NEW("reports/new"),
    FW_REP_EDIT("reports/edit"),

    FW_ATT_INDEX("attendance/index"),
    FW_ATT_NEW("attendance/new"),

    FW_APP_INDEX("timecardApplication/index"),
    FW_APP_APPINDEX("timecardApplication/appindex"),
    FW_APP_NEW("timecardApplication/new"),
    FW_APP_SHOW("timecardApplication/show"),
    FW_APP_EDIT("timecardApplication/edit");

    private final String text;

    private ForwardConst(final String text) {
        this.text = text;
    }

    public String getValue() {
        return this.text;
    }

}
