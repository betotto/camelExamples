/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.betotto.graphics.histogramReport;

import com.mongodb.MongoClient;
import javax.enterprise.inject.Produces;
import javax.inject.Named;
import javax.inject.Singleton;
import javax.sql.DataSource;

/**
 *
 * @author roberto
 */
@Singleton
public class DataSourceFactory {
    
    @Produces
    @Named("briefingiqDataSource")
    DataSource getJndiDatasourceWeblogic() {
        DataSource briefingiqDataSource = null;
        try {
            javax.naming.Context context = new javax.naming.InitialContext();
            briefingiqDataSource = (DataSource) context.lookup("jdbc/briefingiq");
        } catch(Exception ex) {
            System.out.println("perro");
        }
        return briefingiqDataSource;
    }
    
    @Produces
    @Named("mongoClient")
    MongoClient getMongoClient() {
        return new MongoClient("localhost", 27017);
    }
}
