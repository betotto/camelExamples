/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.betotto.graphics.histogramReport;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.ws.rs.core.MediaType;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;

/**
 *
 * @author roberto
 */
public class RequestProcessor implements Processor {

    @Override
    public void process(Exchange exchng) throws Exception {
        HttpURLConnection connection = null;
        String body = "{\"query\":\"query{getAllUsers{id,userName,email}}\",\"variables\":null}";
        
        try {
            //Create connection
            URL url = new URL("http://localhost:4000/graphql");
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type",MediaType.APPLICATION_JSON);
            
            connection.setRequestProperty("Content-Length", Integer.toString(body.getBytes().length));
            connection.setUseCaches(false);
            connection.setDoOutput(true);

            //Send request
            DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
            wr.writeBytes(body);
            wr.close();

            //Get Response  
            InputStream is = connection.getInputStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(is));
            StringBuilder response = new StringBuilder(); // or StringBuffer if Java version 5+
            String line;
            while ((line = rd.readLine()) != null) {
                response.append(line);
            }
            rd.close();
            exchng.getOut().setBody(response.toString());
        } catch (Exception e) {
            e.printStackTrace();
            exchng.getOut().setBody(null);
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
    }
}
