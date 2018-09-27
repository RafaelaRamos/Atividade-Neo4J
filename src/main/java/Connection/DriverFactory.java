/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Connection;

import org.neo4j.driver.v1.AuthTokens;
import org.neo4j.driver.v1.Driver;
import org.neo4j.driver.v1.GraphDatabase;
import org.neo4j.driver.v1.Session;

/**
 *
 * @author Cliente
 */
public class DriverFactory {

    private Driver driver;
    

   
    public DriverFactory() {
        String url ="bolt://localhost:7687";
        String usuario ="neo4j";
        String senha="123";
        
        
        driver = GraphDatabase.driver(url, AuthTokens.basic(usuario, senha));

    }

    public Driver getDriver() {
        return driver;
    }
}
