package services;

import java.util.List;

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

    private TimecardApplication findOneInternal(int id) {
        return em.find(TimecardApplication.class, id);
    }

    private void createInternal(TimecardApplicationView apv) {

        em.getTransaction().begin();
        em.persist(TimecardApplicationConverter.toModel(apv));
        em.getTransaction().commit();

    }

}
