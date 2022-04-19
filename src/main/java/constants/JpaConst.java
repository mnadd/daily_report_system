package constants;

public interface JpaConst {

    String PERSISTENCE_UNIT_NAME = "daily_report_system";

    int ROW_PER_PAGE = 15;
    int ATT_ROW_PER_PAGE = 31;

    String TABLE_EMP = "employees";

    String EMP_COL_ID = "id";
    String EMP_COL_CODE = "code";
    String EMP_COL_NAME = "name";
    String EMP_COL_PASS = "password";
    String EMP_COL_ADMIN_FLAG = "admin_flag";
    String EMP_COL_CREATED_AT = "created_at";
    String EMP_COL_UPDATED_AT = "updated_at";
    String EMP_COL_DELETE_FLAG = "delete_flag";

    int ROLE_ADMIN = 1;
    int ROLE_GENERAL = 0;
    int EMP_DEL_TRUE = 1;
    int EMP_DEL_FALSE = 0;


    String TABLE_REP = "reports";

    String REP_COL_ID = "id";
    String REP_COL_EMP = "employee_id";
    String REP_COL_REP_DATE = "report_date";
    String REP_COL_TITLE = "title";
    String REP_COL_CONTENT = "content";
    String REP_COL_CREATED_AT = "created_at";
    String REP_COL_UPDATED_AT = "updated_at";

    String TABLE_ATT = "attendances";

    String ATT_COL_ID = "id";
    String ATT_COL_EMP = "employee_id";
    String ATT_COL_ATT_DATE = "attendance_date";
    String ATT_COL_START = "start";
    String ATT_COL_FINISH = "finish";
    String ATT_COL_ACT_TIME = "actual_time";
    String ATT_COL_OVER_TIME = "over_time";
    String ATT_COL_PERMIT_FLAG = "permit_flag";

    int PERMIT_TRUE = 1;
    int PERMIT_FALSE = 0;

    String TABLE_APP = "applications";

    String APP_COL_ID = "id";
    String APP_COL_EMP = "employee_id";
    String APP_COL_ATT = "attendance_id";
    String APP_COL_APP_DATE = "application_date";
    String APP_COL_TYPE_FLAG = "type_flag";
    String APP_COL_TIME = "time";
    String APP_COL_CONTENT = "appContent";
    String APP_COL_COMMENT = "comment";

    int TYPE_START = 1;
    int TYPE_FINISH = 0;


    String ENTITY_EMP = "employee";
    String ENTITY_REP = "report";
    String ENTITY_ATT = "attendance";
    String ENTITY_APP = "application";


    String JPQL_PARM_CODE = "code";
    String JPQL_PARM_PASSWORD = "password";
    String JPQL_PARM_EMPLOYEE = "employee";
    String JPQL_PARM_DATE = "attendance_date";


    String Q_EMP_GET_ALL = ENTITY_EMP + ".getAll";
    String Q_EMP_GET_ALL_DEF = "SELECT e FROM Employee AS e ORDER BY e.id DESC";

    String Q_EMP_COUNT = ENTITY_EMP + ".count";
    String Q_EMP_COUNT_DEF = "SELECT COUNT(e) FROM Employee AS e";

    String Q_EMP_GET_BY_CODE_AND_PASS = ENTITY_EMP + ".getByCodeAndPass";
    String Q_EMP_GET_BY_CODE_AND_PASS_DEF = "SELECT e FROM Employee AS e WHERE e.deleteFlag = 0 AND e.code = :" + JPQL_PARM_CODE + " AND e.password = :" + JPQL_PARM_PASSWORD;

    String Q_EMP_COUNT_REGISTERED_BY_CODE = ENTITY_EMP + ".countRegisteredByCode";
    String Q_EMP_COUNT_REGISTERED_BY_CODE_DEF = "SELECT COUNT(e) FROM Employee AS e WHERE e.code = :" + JPQL_PARM_CODE;

    String Q_REP_GET_ALL = ENTITY_REP + ".getAll";
    String Q_REP_GET_ALL_DEF = "SELECT r FROM Report AS r ORDER BY r.id DESC";

    String Q_REP_COUNT = ENTITY_REP + ".count";
    String Q_REP_COUNT_DEF = "SELECT COUNT(r) FROM Report AS r";

    String Q_REP_GET_ALL_MINE = ENTITY_REP + ".getAllMine";
    String Q_REP_GET_ALL_MINE_DEF = "SELECT r FROM Report AS r WHERE r.employee = :" + JPQL_PARM_EMPLOYEE + " ORDER BY r.id DESC";

    String Q_REP_COUNT_ALL_MINE = ENTITY_REP + ".countAllMine";
    String Q_REP_COUNT_ALL_MINE_DEF = "SELECT COUNT(r) FROM Report AS r WHERE r.employee = :" + JPQL_PARM_EMPLOYEE;

    String Q_ATT_GET_ALL = ENTITY_ATT + ".getAll";
    String Q_ATT_GET_ALL_DEF = "SELECT a FROM Attendance AS a ORDER BY a.id";

    String Q_ATT_GET_COUNT =ENTITY_ATT + ".count";
    String Q_ATT_GET_COUNT_DEF = "SELECT COUNT(a) FROM Attendance AS a";

    String Q_ATT_GET_ALL_MINE = ENTITY_ATT + ".getAllMine";
    String Q_ATT_GET_ALL_MINE_DEF = "SELECT a FROM Attendance AS a WHERE a.employee = :" + JPQL_PARM_EMPLOYEE + " ORDER BY a.id";

    String Q_ATT_COUNT_ALL_MINE = ENTITY_ATT + ".countAllMine";
    String Q_ATT_COUNT_ALL_MINE_DEF = "SELECT COUNT(a) FROM Attendance AS a WHERE a.employee = :" + JPQL_PARM_EMPLOYEE;

    String Q_ATT_GET_BY_EMP_AND_DATE = ENTITY_ATT + ".getByEmpAndDate";
    String Q_ATT_GET_BY_EMP_AND_DATE_DEF = "SELECT a FROM Attendance AS a WHERE a.employee = :" + JPQL_PARM_EMPLOYEE + " AND a.attendanceDate = :" + JPQL_PARM_DATE;

    String Q_APP_GET_ALL = ENTITY_APP + ".getAll";
    String Q_APP_GET_ALL_DEF = "SELECT ap FROM Application AS ap ORDER BY ap.id DESC";

    String Q_APP_GET_COUNT = ENTITY_APP + ".count";
    String Q_APP_GET_COUNT_DEF = "SELECT COUNT(ap) FROM Application AS ap";


}
