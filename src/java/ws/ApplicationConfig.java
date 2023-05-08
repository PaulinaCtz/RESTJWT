/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ws;

import filtros.JwtFilter;
import java.util.Set;
import javax.ws.rs.core.Application;

/**
 *
 * @author Paulina Cortez Alamilla.
 */
@javax.ws.rs.ApplicationPath("webresources")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        resources.add(RecursoCliente.class);
        resources.add(AuthService.class);
        resources.add(JwtFilter.class);
        return resources;
    }

    /**
     * Do not modify addRestResourceClasses() method.
     * It is automatically populated with
     * all resources defined in the project.
     * If required, comment out calling this method in getClasses().
     */
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(filtros.JwtFilter.class);
        resources.add(ws.AuthService.class);
        resources.add(ws.RecursoCliente.class);
    }
    
}
