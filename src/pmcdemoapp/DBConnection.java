/**
 *
 * //@author n00131835
 * 
 **/
package pmcdemoapp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//This class represents a connection to the database.
//Tjhis is the second time I have done this!
public class DBConnection {

    private static Connection sConnection;

    //Implement the DBConnection class as Singleton
    public static Connection getInstance() throws ClassNotFoundException, SQLException {
        String host, db, user, password;
        
        host = "daneel";
        db = "n00131835"; //database name 
        user = "N00131835"; //my username
        password = "N00131835"; //my password
        
        if (sConnection == null || sConnection.isClosed()) {
            String url = "jdbc:mysql://" + host + "/" + db;
            Class.forName("com.mysql.jdbc.Driver");
            sConnection = DriverManager.getConnection(url, user, password);
        }
        
        //@author n00131835

        return sConnection;
    }
}
