package services;

import java.util.List;

import actions.views.ApplicationConverter;
import actions.views.ApplicationView;
import constants.JpaConst;
import models.Application;
import models.validators.ApplicationValidator;

public class ApplicationService extends ServiceBase {

    public List<ApplicationView> getPerPage(int page) {
        List<Application> applications = em.createNamedQuery(JpaConst.Q_APP_GET_ALL, Application.class)
                .setFirstResult(JpaConst.ROW_PER_PAGE * (page - 1))
                .setMaxResults(JpaConst.ROW_PER_PAGE)
                .getResultList();

        return ApplicationConverter.toViewList(applications);
    }

    public long countAll() {
        long appCount = (long) em.createNamedQuery(JpaConst.Q_APP_GET_COUNT, Long.class)
                .getSingleResult();

        return appCount;
    }

    public ApplicationView findOne(int id) {
        return ApplicationConverter.toView(findOneInternal(id));
    }

    public List<String> create(ApplicationView apv) {
        List<String> errors = ApplicationValidator.validate(apv);
        if (errors.size() == 0) {
            createInternal(apv);
        }
        return errors;
    }

    private Application findOneInternal(int id) {
        return em.find(Application.class, id);
    }

    private void createInternal(ApplicationView apv) {

        em.getTransaction().begin();
        em.persist(ApplicationConverter.toModel(apv));
        em.getTransaction().commit();

    }

}
