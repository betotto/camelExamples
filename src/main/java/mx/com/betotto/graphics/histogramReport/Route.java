/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.betotto.graphics.histogramReport;

import javax.ws.rs.core.MediaType;
import org.apache.camel.builder.RouteBuilder;

/**
 *
 * @author roberto
 */
public class Route extends RouteBuilder {
    
    @Override
    public void configure() {
        rest()
            .post("histogramReport")
                .produces(MediaType.APPLICATION_JSON)
                .route()
                .multicast()
                .aggregationStrategy(new Aggregator())
                .to("direct:briefinqIqQuery", "direct:mongoClient", "direct:restService");
        
        from("direct:restService")
            .log("Getting sql data")
            .process(new RequestProcessor())
            .to("direct:constructReport");
        
        from("direct:briefinqIqQuery")
            .log("Getting sql data")
            .to("sql:select REQUEST_TIME from BI_AUDIT_API?dataSource=briefingiqDataSource")
            .process(new BriefiniqProcessor())
            .to("direct:constructReport");
        
        from("direct:mongoClient")
            .log("Getting mongo data")
            .to("mongodb:mongoClient?database=flatData&collection=inventory&operation=findAll")
            .process(new MongoDBProcessor())
            .to("direct:constructReport");
                
        from("direct:constructReport")
            .transform().body();
    }
}
