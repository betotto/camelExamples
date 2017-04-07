/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.betotto.graphics.histogramReport;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.converter.stream.CachedOutputStream;

/**
 *
 * @author roberto
 */
public class OutputProcessor implements Processor {

    @Override
    public void process(Exchange exchng) throws Exception {
        System.out.println(exchng.getIn().getBody());
        CachedOutputStream content = (CachedOutputStream)exchng.getIn().getBody();
        InputStream in = content.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        StringBuilder out = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            out.append(line);
        }
        System.out.println(out.toString()); 
        exchng.getOut().setBody(out.toString());
    }
    
}
