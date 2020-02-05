package com.company.springbootodata.service;

import com.company.springbootodata.controller.EntityManagerFilter;
import org.apache.olingo.odata2.api.processor.ODataContext;
import org.apache.olingo.odata2.jpa.processor.api.ODataJPAContext;
import org.apache.olingo.odata2.jpa.processor.api.ODataJPAServiceFactory;
import org.apache.olingo.odata2.jpa.processor.api.exception.ODataJPARuntimeException;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;

@Component
public class UsersODataJPAServiceFactory extends ODataJPAServiceFactory {

    private static final String PERSISTENCE_UNIT_NAME = "default";

    public UsersODataJPAServiceFactory() {
        setDetailErrors(true);
    }

    @Override
    public ODataJPAContext initializeODataJPAContext() throws ODataJPARuntimeException {
        ODataJPAContext jpaContext = getODataJPAContext();
        ODataContext oDataContext = jpaContext.getODataContext();
        HttpServletRequest request = (HttpServletRequest) oDataContext.getParameter(ODataContext.HTTP_SERVLET_REQUEST_OBJECT);
        EntityManager entityManager = (EntityManager) request.getAttribute(EntityManagerFilter.getEM_REQUEST_ATTRIBUTE());

        jpaContext.setEntityManager(new EntityManagerWrapper(entityManager));
        jpaContext.setPersistenceUnitName(PERSISTENCE_UNIT_NAME);
        jpaContext.setContainerManaged(true);

        return jpaContext;
    }
}
