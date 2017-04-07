package mx.com.betotto.graphics.histogramReport;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author roberto
 */
public class MongoDBProcessor  implements Processor {

    @Override
    public void process(Exchange exchng) throws Exception {
        System.out.println(exchng.getIn().getBody());
        exchng.getOut().setBody(exchng.getIn().getBody());
    }
    
}
