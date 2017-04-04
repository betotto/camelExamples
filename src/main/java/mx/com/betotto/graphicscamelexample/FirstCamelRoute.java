/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.betotto.graphicscamelexample;

import javax.inject.Inject;

import javax.inject.Named;
import org.apache.camel.CamelContext;
import org.apache.camel.Header;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.cdi.ContextName;
/**
 *
 * @author roberto
 */
public class FirstCamelRoute {
    
    @ContextName("hello")
    public static class HelloRoute extends RouteBuilder {

        @Override
        public void configure() {
            rest("/say/")
                .produces("text/plain")
                .get("hello")
                    .route()
                    .transform().constant("Hello World!")
                    .endRest()
                .get("hello/{name}")
                    .route()
                    .bean("hello")
                    .log("${body}");
        }
    }

    @Named("hello")
    public static class Hello {

        @Inject
        CamelContext context;

        public String hello(@Header("name") String name) {
            return "Hello " + name + ", I'm " + context + "!";
        }
    }
}