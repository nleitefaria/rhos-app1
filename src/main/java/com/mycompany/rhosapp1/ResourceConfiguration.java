package com.mycompany.rhosapp1;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import com.mycompany.rhosapp1.rws.CustomerResource;
import com.mycompany.rhosapp1.rws.ProductResource;

import java.util.HashSet;
import java.util.Set;

@ApplicationPath("/resources")
public class ResourceConfiguration extends Application {
    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }

    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(ProductResource.class);
        resources.add(CustomerResource.class);
    }
}