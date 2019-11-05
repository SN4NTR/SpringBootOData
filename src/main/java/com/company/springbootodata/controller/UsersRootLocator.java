package com.company.springbootodata.controller;

import com.company.springbootodata.service.UsersODataJPAServiceFactory;
import org.apache.olingo.odata2.api.ODataServiceFactory;
import org.apache.olingo.odata2.core.rest.ODataRootLocator;

import javax.ws.rs.Path;

@Path("/")
public class UsersRootLocator extends ODataRootLocator {

    private UsersODataJPAServiceFactory serviceFactory;

    public UsersRootLocator(UsersODataJPAServiceFactory serviceFactory) {
        this.serviceFactory = serviceFactory;
    }

    @Override
    public ODataServiceFactory getServiceFactory() {
        return serviceFactory;
    }
}
