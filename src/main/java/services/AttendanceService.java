package services;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import actions.views.AttendanceConverter;
import actions.views.AttendanceView;
import actions.views.EmployeeConverter;
import actions.views.EmployeeView;
import constants.JpaConst;
import models.Attendance;


public class AttendanceService extends ServiceBase {

    public List<AttendanceView> getMinePerPage(EmployeeView employee, int page) {

        List<Attendance> attendances = em.createNamedQuery(JpaConst.Q_ATT_GET_ALL_MINE, Attendance.class)
                .setParameter(JpaConst.JPQL_PARM_EMPLOYEE, EmployeeConverter.toModel(employee))
                .setFirstResult(JpaConst.ATT_ROW_PER_PAGE * (page - 1))
                .setMaxResults(JpaConst.ATT_ROW_PER_PAGE)
                .getResultList();
        return AttendanceConverter.toViewList(attendances);
    }


    public long countAllMine(EmployeeView employee) {

        long count = (long) em.createNamedQuery(JpaConst.Q_ATT_COUNT_ALL_MINE, Long.class)
                .setParameter(JpaConst.JPQL_PARM_EMPLOYEE, EmployeeConverter.toModel(employee))
                .getSingleResult();
        return count;
    }

    public AttendanceView findOne(int id) {

        return AttendanceConverter.toView(findOneInternal(id));
    }

    public List<String> create(AttendanceView av) {

            LocalDate ld = LocalDate.now();
            av.setAttendanceDate(ld);

            LocalTime lt = LocalTime.now();
            av.setStart(lt);
            createInternal(av);
            return null;
        }
    public List<String> workFinish(AttendanceView av) {

        LocalTime lt = LocalTime.now();
        av.setFinish(lt);
        createInternal(av);
        return null;
    }



    private Attendance findOneInternal(int id) {
        return em.find(Attendance.class, id);
    }

    private void createInternal(AttendanceView av) {

        em.getTransaction().begin();
        em.persist(AttendanceConverter.toModel(av));
        em.getTransaction().commit();

    }



}
