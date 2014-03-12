package com.faiez;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;


public class MvcInitializer implements WebApplicationInitializer {

    public void onStartup(ServletContext servletContext) throws ServletException {

        AnnotationConfigWebApplicationContext mvcContext= new AnnotationConfigWebApplicationContext();
        mvcContext.register(WebConfig.class);

        ServletRegistration.Dynamic register=
                servletContext.addServlet("distpatcher", new DispatcherServlet(mvcContext));
        register.addMapping("/*");
        register.setLoadOnStartup(1);


    }
}
