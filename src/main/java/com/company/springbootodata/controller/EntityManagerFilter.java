package com.company.springbootodata.controller;

import lombok.Getter;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.core.Context;
import javax.ws.rs.ext.Provider;
import java.io.IOException;

@Provider
public class EntityManagerFilter implements ContainerRequestFilter, ContainerResponseFilter {

    @Getter
    private static final String EM_REQUEST_ATTRIBUTE = EntityManagerFilter.class.getName() + "_ENTITY_MANAGER";

    private final EntityManagerFactory entityManagerFactory;

    @Context
    private HttpServletRequest httpRequest;

    public EntityManagerFilter(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    @Override
    public void filter(ContainerRequestContext ctx) throws IOException {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        httpRequest.setAttribute(EM_REQUEST_ATTRIBUTE, entityManager);

        if (!"GET".equalsIgnoreCase(ctx.getMethod())) {
            entityManager.getTransaction().begin();
        }
    }

    @Override
    public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext) throws IOException {
        EntityManager entityManager = (EntityManager) httpRequest.getAttribute(EM_REQUEST_ATTRIBUTE);

        if (!"GET".equalsIgnoreCase(requestContext.getMethod())) {
            EntityTransaction transaction = entityManager.getTransaction();

            if (transaction.isActive()) {
                if (!transaction.getRollbackOnly()) {
                    transaction.commit();
                }
            }
        }

        entityManager.close();
    }
}
