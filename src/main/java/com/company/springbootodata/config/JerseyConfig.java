package com.company.springbootodata.config;

import com.company.springbootodata.service.UsersODataJPAServiceFactory;
import org.apache.olingo.odata2.api.ODataServiceFactory;
import org.apache.olingo.odata2.core.rest.ODataRootLocator;
import org.apache.olingo.odata2.core.rest.app.ODataApplication;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.Path;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.core.Context;
import javax.ws.rs.ext.Provider;
import java.io.IOException;

@Component
@ApplicationPath("/odata")
public class JerseyConfig extends ResourceConfig {

    public JerseyConfig(UsersODataJPAServiceFactory serviceFactory, EntityManagerFactory emf) {

        ODataApplication app = new ODataApplication();

        app.getClasses()
                .forEach(c -> {
                    if (!ODataRootLocator.class.isAssignableFrom(c)) {
                        register(c);
                    }
                });

        register(new UsersRootLocator(serviceFactory));
        register(new EntityManagerFilter(emf));
    }

    @Provider
    public static class EntityManagerFilter implements ContainerRequestFilter, ContainerResponseFilter {

        public static final String EM_REQUEST_ATTRIBUTE = EntityManagerFilter.class.getName() + "_ENTITY_MANAGER";

        private final EntityManagerFactory emf;

        @Context
        private HttpServletRequest httpRequest;

        public EntityManagerFilter(EntityManagerFactory emf) {
            this.emf = emf;
        }

        @Override
        public void filter(ContainerRequestContext ctx) throws IOException {
            EntityManager em = this.emf.createEntityManager();
            httpRequest.setAttribute(EM_REQUEST_ATTRIBUTE, em);

            if (!"GET".equalsIgnoreCase(ctx.getMethod())) {
                em.getTransaction()
                        .begin();
            }
        }

        @Override
        public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext) throws IOException {

            EntityManager em = (EntityManager) httpRequest.getAttribute(EM_REQUEST_ATTRIBUTE);

            if (!"GET".equalsIgnoreCase(requestContext.getMethod())) {
                EntityTransaction t = em.getTransaction();
                if (t.isActive()) {
                    if (!t.getRollbackOnly()) {
                        t.commit();
                    }
                }
            }

            em.close();
        }
    }

    @Path("/")
    public static class UsersRootLocator extends ODataRootLocator {

        private UsersODataJPAServiceFactory serviceFactory;

        public UsersRootLocator(UsersODataJPAServiceFactory serviceFactory) {
            this.serviceFactory = serviceFactory;
        }

        @Override
        public ODataServiceFactory getServiceFactory() {
            return this.serviceFactory;
        }
    }
}
