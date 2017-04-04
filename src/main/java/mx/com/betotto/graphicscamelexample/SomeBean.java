/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.betotto.graphicscamelexample;

import javax.inject.Named;
import javax.inject.Singleton;

/**
 *
 * @author roberto
 */
@Singleton
@Named("counterBean")
public class SomeBean {

    private int counter;

    public String someMethod(String body) {
        return "Saying Hello World " + ++counter + " times";
    }

}