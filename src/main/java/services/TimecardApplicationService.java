package services;

import java.util.List;

import actions.views.EmployeeConverter;
import actions.views.EmployeeView;
import actions.views.TimecardApplicationConverter;
import actions.views.TimecardApplicationView;
import constants.JpaConst;
import models.TimecardApplication;
import models.validators.TimecardApplicationValidator;

public class TimecardApplicationService extends ServiceBase {

    public List<TimecardApplicationView> getPerPage(int page) {
        List<TimecardApplication> timecardApplications = em.createNamedQuery(JpaConst.Q_APP_GET_ALL, TimecardApplication.class)
                .setFirstResult(JpaConst.ROW_PER_PAGE * (page - 1))
                .setMaxResults(JpaConst.ROW_PER_PAGE)
                .getResultList();

        return TimecardApplicationConverter.toViewList(timecardApplications);
    }

    public long countAll() {
        long appCount = (long) em.createNamedQuery(JpaConst.Q_APP_GET_COUNT, Long.class)
                .getSingleResult();

        return appCount;
    }

    public List<TimecardApplicationView> getMinePerPage(EmployeeView employee, int page) {

        List<TimecardApplication> timecardApplications = em.createNamedQuery(JpaConst.Q_APP_GET_ALL_MINE, TimecardApplication.class)
                .setParameter(JpaConst.JPQL_PARM_EMPLOYEE, EmployeeConverter.toModel(employee))
                .setFirstResult(JpaConst.ROW_PER_PAGE * (page - 1))
                .setMaxResults(JpaConst.ROW_PER_PAGE)
                .getResultList();
        return TimecardApplicationConverter.toViewList(timecardApplications);
    }

    public long countAllMine(EmployeeView employee) {
        long count = (long) em.createNamedQuery(JpaConst.Q_APP_COUNT_ALL_MINE, Long.class)
                .setParameter(JpaConst.JPQL_PARM_EMPLOYEE, EmployeeConverter.toModel(employee))
                .getSingleResult();
        return count;
    }

    public TimecardApplicationView findOne(int id) {
        return TimecardApplicationConverter.toView(findOneInternal(id));
    }

    public List<String> create(TimecardApplicationView apv) {
        List<String> errors = TimecardApplicationValidator.validate(apv);
        if (errors.size() == 0) {
            createInternal(apv);
        }
        return errors;
    }

    public List<String> update(TimecardApplicationView apv) {
        List<String> errors = TimecardApplicationValidator.validate(apv);
        if (errors.size() == 0) {
            approveInternal(apv);
        }
        return errors;
    }

    public List<String> approveFalse(TimecardApplicationView apv) {
        List<String> errors = TimecardApplicationValidator.validateComm(apv);
        if (errors.size() == 0) {
            approveInternal(apv);
        }
        return errors;
    }

    public void approve(Integer id) {

       TimecardApplicationView savedApp = findOne(id);

        savedApp.setAppApprove(JpaConst.APPROVE_TRUE);
        approveInternal(savedApp);
    }

    private TimecardApplication findOneInternal(int id) {
        return em.find(TimecardApplication.class, id);
    }

    private void createInternal(TimecardApplicationView apv) {

        em.getTransaction().begin();
        em.persist(TimecardApplicationConverter.toModel(apv));
        em.getTransaction().commit();

    }

    private void approveInternal(TimecardApplicationView apv) {

        em.getTransaction().begin();
        TimecardApplication ap = findOneInternal(apv.getId());
        TimecardApplicationConverter.copyViewToModel(ap, apv);
        em.getTransaction().commit();

    }

}
