/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.verum.spa.restServices;

import java.util.Set;
import javax.ws.rs.core.Application;

/**
 *
 * @author ruben
 */
@javax.ws.rs.ApplicationPath("webresources")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }

    /**
     * Do not modify addRestResourceClasses() method.
     * It is automatically populated with
     * all resources defined in the project.
     * If required, comment out calling this method in getClasses().
     */
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(com.verum.spa.restServices.BranchRest.class);
        resources.add(com.verum.spa.restServices.CustomerREST.class);
        resources.add(com.verum.spa.restServices.EmployeeREST.class);
        resources.add(com.verum.spa.restServices.GenericResource.class);
        resources.add(com.verum.spa.restServices.ProductRest.class);
        resources.add(com.verum.spa.restServices.RoomRest.class);
        resources.add(com.verum.spa.restServices.TreatmentRest.class);
    }
    
}
