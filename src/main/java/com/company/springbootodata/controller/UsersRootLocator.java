package com.company.springbootodata.controller;

import com.company.springbootodata.service.UsersODataJPAServiceFactory;
import lombok.AllArgsConstructor;
import org.apache.olingo.odata2.api.ODataServiceFactory;
import org.apache.olingo.odata2.core.rest.ODataRootLocator;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.Path;

@Path("/")
@AllArgsConstructor(onConstructor_ = @Autowired)
public class UsersRootLocator extends ODataRootLocator {

    private UsersODataJPAServiceFactory serviceFactory;

    @Override
    public ODataServiceFactory getServiceFactory() {
        return serviceFactory;
    }
}
