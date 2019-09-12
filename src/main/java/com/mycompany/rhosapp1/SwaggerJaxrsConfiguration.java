package com.mycompany.rhosapp1;

import com.wordnik.swagger.config.ConfigFactory;
import com.wordnik.swagger.config.ScannerFactory;
import com.wordnik.swagger.config.SwaggerConfig;
import com.wordnik.swagger.jaxrs.config.DefaultJaxrsScanner;
import com.wordnik.swagger.jaxrs.reader.DefaultJaxrsApiReader;
import com.wordnik.swagger.reader.ClassReaders;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

@WebServlet(name = "SwaggerJaxrsConfiguration", loadOnStartup = 1)
public class SwaggerJaxrsConfiguration extends HttpServlet {

	private static final long serialVersionUID = 8647200575874543619L;

	@Override
    public void init(ServletConfig servletConfig) {
        try {
        	super.init(servletConfig);
            SwaggerConfig swaggerConfig = new SwaggerConfig();
            ConfigFactory.setConfig(swaggerConfig);
            //swaggerConfig.setBasePath("http://localhost:8080/rhos-app1/resources");
            swaggerConfig.setBasePath("/resources");
            swaggerConfig.setApiVersion("1.0.0");
            ScannerFactory.setScanner(new DefaultJaxrsScanner());
            ClassReaders.setReader(new DefaultJaxrsApiReader());
        } catch (ServletException e) {
            System.out.println(e.getMessage());
        }
    }
}