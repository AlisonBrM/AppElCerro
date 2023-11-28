/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Red;
import org.apache.commons.dbcp2.BasicDataSource;
import java.sql.Connection;
import java.sql.SQLException;
/**
 *
 * @author Alison Martinez
 */
public class BaseDeDatos {
    private static BasicDataSource bs = new BasicDataSource();
    private static BaseDeDatos instance;
    private final static String URL = "jdbc:mysql://54.147.25.136:3306/supermecado?useSSL=false&useTimezone=true&serverTimezone=UTC&allowPublicKeyRetrieval=true";
    private final static String USERNAME = "test";
    private static String PASSWORD = "test1_*";
    
    private BaseDeDatos(){
        bs.setUrl(URL);
        bs.setUsername(USERNAME);
        bs.setPassword(PASSWORD);
        bs.setMinIdle(5);
        bs.setMaxIdle(10);
        bs.setMaxOpenPreparedStatements(100);
        
    }
    
    public static synchronized BaseDeDatos getInstance(){
        if(instance == null){
            instance = new BaseDeDatos();
        }
        return instance;
    }
    
    public Connection getConnection() throws SQLException {
        return bs.getConnection();
    }
}
