/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.betotto.graphics.histogramReport;

import javax.ws.rs.core.MediaType;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.cdi.ContextName;

/**
 *
 * @author roberto
 */
public class Route extends RouteBuilder{
    
    @Override
    public void configure() {
        rest()
            .post("histogramReport")
                .produces(MediaType.APPLICATION_JSON)
                .to("sql:select REQUEST_TIME from BI_AUDIT_API?dataSource=briefingiqDataSource")
                .route()
                .transform().body();
    }
}
