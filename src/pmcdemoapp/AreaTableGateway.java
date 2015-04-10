/**
 *
 * //@author n00131835
 * 
 **/
package pmcdemoapp;

//These are the libraries imported for the code to work
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

//This class is the connection between the MySQL and the code. This is where the queries are made that affects the database 
//(i.e. if a row got deleted or a row is edited, you will see this in NetBeans but for the code to work it should change how it looks on the PHPMYADMIN)
class AreaTableGateway {
    //The column in the PHPMYADMIN is being initialised here
    private static final String TABLE_NAME = "area";
    private static final String COLUMN_AREAID = "AreaID";
    private static final String COLUMN_AREANAME = "AreaName";
    private static final String COLUMN_FACILITIES = "Facilities";

    private Connection mConnection;
    
    public AreaTableGateway(Connection connection) {
        mConnection = connection;
    }
    
    //@author n00131835

    public int insertArea(String a1, String a2) throws SQLException { //this query will insert/create a row in the MySQL database
        Area p = null;
        
        String query;   // the SQL query to execute
        PreparedStatement stmt;    // the java.sql.PreparedStatement object used to execute the SQL query
        int numRowsAffected; // initialising the variable for the number of rows that will and should be affected in the MySQL
        int areaID = -1;
        
        // the required SQL INSERT statement with place holders for the values to be inserted into the database
        query = "INSERT INTO " + TABLE_NAME + " (" +
                COLUMN_AREANAME + ", " +
                COLUMN_FACILITIES +
                ") VALUES (?, ?, ?, ?, ?, ?, ?)"; //the ? are placeholders
        
        // create a PreparedStatement object to execute the query and insert the values into the query
        stmt = mConnection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        stmt.setString (1, a1);
        stmt.setString (2, a2);

        //  execute the query and make sure that one and only one row was inserted into the database
        numRowsAffected = stmt.executeUpdate();
        if (numRowsAffected == 1) {
            // if one row was inserted, retrieve the id assigned to that row and create a Programmer object to return
            ResultSet keys = stmt.getGeneratedKeys();
            keys.next();
            
            areaID = keys.getInt(1);
        }
        
        // return the Area object created or null if there was a problem
        return areaID;  
    } 
    
    public List<Area> getAreas() throws SQLException {
        String query;       // the SQL query to execute
        Statement stmt;     // the java.sql.Statement object used to execute the SQL query
        ResultSet rs;       // the java.sql.ResultSet representing the result of SQL query 
        List<Area> areas;   // the java.util.List containing the Programmer objects created for each row in the result of the query
        
        //initiliasing variables from the Area Class
        int areaID;             // the id of a area
        String areaname, facilities; 
        Area a;       // a Area object created from a row in the result of the query

        // execute an SQL SELECT statement to get a java.util.ResultSet representing
        // the results of the SELECT statement
        query = "SELECT * FROM " + TABLE_NAME; //query being made.
        stmt = this.mConnection.createStatement();
        rs = stmt.executeQuery(query);
        
        // iterate through the result set, extracting the data from each row
        // and storing it in a Area object, which is inserted into an initially
        // empty ArrayList
        
        areas = new ArrayList<Area>();
        while (rs.next()) {
            areaID = rs.getInt(COLUMN_AREAID);
            areaname = rs.getString(COLUMN_AREANAME);
            facilities = rs.getString(COLUMN_FACILITIES);
            
            a = new Area(areaID, areaname, facilities);
            areas.add(a);
        }
        return areas;
    }
    
    //@author n00131835
    
    public boolean updateArea(Area a) throws SQLException { //returns true or false if area has been updated
        String query;   // the SQL query to execute
        PreparedStatement stmt;    // the java.sql.PreparedStatement object used to execute the SQL query
        int numRowsAffected;
        
        // the required SQL INSERT statement with place holders for the values to be inserted into the database
        query = "UPDATE " + TABLE_NAME + " SET " +
                COLUMN_AREANAME + " = ?, " +
                COLUMN_FACILITIES + " = ? " +
                " WHERE " + COLUMN_AREAID + " = ? ";
        
        // create a PreparedStatement object to execute the query and insert the values into the query
        stmt = mConnection.prepareStatement(query);
        stmt.setString (1, a.getAreaName());
        stmt.setString (2, a.getFacilities());
        stmt.setInt (3, a.getAreaID());

        //  execute the query and make sure that one and only one row was inserted into the database
        numRowsAffected = stmt.executeUpdate();
        
        // return the Area object created or null if there was a problem
        return (numRowsAffected == 1);  
    
    }
    
    public boolean deleteArea(int areaID) throws SQLException{ //returns true or false if area has been deleted
        String query; //SQL query to execute
        PreparedStatement stmt; 
        int numRowsAffected; // initialising the variable for the number of rows that will and should be affected in the MySQL
        
        // the required SQL DELETE statement with place holders for the id of the row to be remove from the database
        query = "DELETE FROM " + TABLE_NAME + " WHERE " + COLUMN_AREAID + " = ?"; // the query code that will delete a row, in this case i used the AreaID to be selected.
        
        // create a PreparedStatement object to execute the query and insert the id into the query
        stmt = mConnection.prepareStatement(query);
        stmt.setInt(1, areaID);
        
        // execute the query
        numRowsAffected = stmt.executeUpdate();
        
        // return the true if one and only one row was deleted from the database
        return (numRowsAffected == 1);
    }
    
    //@author n00131835
}
