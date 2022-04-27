package constants;

public enum AttributeConst {

    FLUSH("flush"),

    MAX_ROW("maxRow"),
    PAGE("page"),

    TOKEN("_token"),
    ERR("errors"),

    LOGIN_EMP("login_employee"),

    LOGIN_ERR("loginError"),

    EMPLOYEE("employee"),
    EMPLOYEES("employees"),
    EMP_COUNT("employees_count"),
    EMP_ID("id"),
    EMP_CODE("code"),
    EMP_PASS("password"),
    EMP_NAME("name"),
    EMP_ADMIN_FLG("admin_flag"),

    ROLE_ADMIN(1),
    ROLE_GENERAL(0),

    DEL_FLAG_TRUE(1),
    DEL_FLAG_FALSE(0),

    REPORT("report"),
    REPORTS("reports"),
    REP_COUNT("reports_count"),
    REP_ID("id"),
    REP_DATE("report_date"),
    REP_TITLE("title"),
    REP_CONTENT("content"),

    ATTENDANCE("attendance"),
    ATTENDANCES("attendances"),
    ATT_COUNT("attendance_count"),
    ATT_ID("id"),
    ATT_DATE("attendanceDate"),
    ATT_START("start"),
    ATT_FINISH("finish"),
    ATT_PERMIT_FLAG("permit_flag"),

    PERMIT_TRUE(1),
    PERMIT_FALSE(0),

    APPLICATION("timecardApplication"),
    APPLICATIONS("timecardApplications"),
    APP_COUNT("appCount"),
    APP_ID("id"),
    APP_DATE("timecardApplication_date"),
    APP_TYPE_FLAG("type_flag"),
    APP_TIME("time"),
    APP_CONTENT("appContent"),
    APP_COMMENT("comment"),
    APP_APPROVE("appApprove"),

    TYPE_START(1),
    TYPE_FINISH(0),
    APPROVE_TRUE(1),
    APPROVE_FALSE(0);

    private final String text;
    private final Integer i;

    private AttributeConst(final String text) {
        this.text = text;
        this.i = null;

    }
    private AttributeConst(final Integer i) {
        this.text = null;
        this.i = i;
    }
    public String getValue() {
        return this.text;
    }
    public Integer getIntegerValue() {
        return this.i;
    }

}
