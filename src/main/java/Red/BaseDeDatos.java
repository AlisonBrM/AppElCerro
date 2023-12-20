/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Red;
import org.apache.commons.dbcp2.BasicDataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
/**
 *
 * @author Alison Martinez
 */
public class BaseDeDatos {
    private static BasicDataSource bs = new BasicDataSource();
    private static BaseDeDatos instance;
    //private final static String URL = "jdbc:mysql://localhost:3306/supermecado?useSSL=false&useTimezone=true&serverTimezone=UTC&allowPublicKeyRetrieval=true";
    private final static String URL = "jdbc:mysql://3.95.231.187:3306/supermecado?useSSL=false&useTimezone=true&serverTimezone=UTC&allowPublicKeyRetrieval=true";
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
    public static void close(Connection con) throws SQLException{
        con.close();
    }
    
    public static void close(Statement stm) throws SQLException{
        stm.close();
    }
    
    public static void close(ResultSet res) throws SQLException{
        res.close();
    }
    
    public static void close(PreparedStatement ps) throws SQLException{
        ps.close();
    }
}
