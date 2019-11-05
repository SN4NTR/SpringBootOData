package com.company.springbootodata.config;

import com.company.springbootodata.controller.EntityManagerFilter;
import com.company.springbootodata.controller.UsersRootLocator;
import com.company.springbootodata.service.UsersODataJPAServiceFactory;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManagerFactory;
import javax.ws.rs.ApplicationPath;

@Component
@ApplicationPath("/odata")
public class JerseyConfig extends ResourceConfig {

    public JerseyConfig(UsersODataJPAServiceFactory serviceFactory, EntityManagerFactory entityManagerFactory) {
        register(new UsersRootLocator(serviceFactory));
        register(new EntityManagerFilter(entityManagerFactory));
    }
}
