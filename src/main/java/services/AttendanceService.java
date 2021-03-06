package services;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

import javax.persistence.NoResultException;

import actions.views.AttendanceConverter;
import actions.views.AttendanceView;
import actions.views.EmployeeConverter;
import actions.views.EmployeeView;
import constants.JpaConst;
import models.Attendance;
import models.validators.AttendanceValidator;


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

    public AttendanceView findOne(EmployeeView employee, LocalDate attendanceDate) {
        Attendance a = null;
        try {
            a = em.createNamedQuery(JpaConst.Q_ATT_GET_BY_EMP_AND_DATE, Attendance.class)
                    .setParameter(JpaConst.JPQL_PARM_EMPLOYEE, EmployeeConverter.toModel(employee))
                    .setParameter(JpaConst.JPQL_PARM_DATE, attendanceDate)
                    .getSingleResult();
        } catch (NoResultException ex) {
        }
            return AttendanceConverter.toView(a);
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

    public List<String> workfin(AttendanceView av) {

        List<String> errors = AttendanceValidator.validate(av);

        if (errors.size() == 0) {

        LocalTime lt = LocalTime.now();
        av.setFinish(lt);
        workfinInternal(av);
    }
        return errors;

    }

    public List<String> update(AttendanceView av) {

        List<String> errors = AttendanceValidator.validate(av);

        if (errors.size() == 0) {

        workfinInternal(av);
    }
        return errors;

    }

    private Attendance findOneInternal(int id) {
        return em.find(Attendance.class, id);
    }

    private void createInternal(AttendanceView av) {

        em.getTransaction().begin();
        em.persist(AttendanceConverter.toModel(av));
        em.getTransaction().commit();

    }

    public void workfinInternal(AttendanceView av) {

        em.getTransaction().begin();
        Attendance a = findOneInternal(av.getId());
        AttendanceConverter.copyViewToModel(a, av);
        em.getTransaction().commit();
    }

    public Boolean validateStart(EmployeeView ev, LocalDate attendanceDate) {

        boolean isValidAttendance = false;
        if (ev != null && attendanceDate != null ) {
            AttendanceView av = findOne(ev, attendanceDate);

            if (av != null && av.getId() != null) {

                isValidAttendance = true;
            }
        }

        return isValidAttendance;
    }

    public LocalTime actTime(AttendanceView av) {


        LocalTime start = av.getStart();
        LocalTime finish = av.getFinish();

        LocalTime startA = start.truncatedTo(ChronoUnit.MINUTES);
        LocalTime finishA = finish.truncatedTo(ChronoUnit.MINUTES);

        Duration actTime = Duration.between(startA, finishA);
        LocalTime actTimeForm = LocalTime.MIDNIGHT.plus(actTime);


        if(actTimeForm.getHour() <= 6 && actTimeForm.getMinute() < 15) {
            av.setActualTime(actTimeForm);
        } else if(actTimeForm.getHour() >= 8 ) {
            av.setActualTime(actTimeForm.minusHours(1));
        } else {
            av.setActualTime(actTimeForm.minusMinutes(45));
        }

        workfinInternal(av);

        return actTimeForm;
    }

}
