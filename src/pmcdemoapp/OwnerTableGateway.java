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
class OwnerTableGateway {
    //The column in the PHPMYADMIN is being initialised here
    private static final String TABLE_NAME = "owner";
    private static final String COLUMN_OWNERID = "OwnerID";
    private static final String COLUMN_FIRSTNAME = "FirstName";
    private static final String COLUMN_LASTNAME = "LastName";
    private static final String COLUMN_ADDRESS1 = "Address1";
    private static final String COLUMN_ADDRESS2 = "Address2";
    private static final String COLUMN_TOWN = "Town";
    private static final String COLUMN_COUNTY = "County";
    private static final String COLUMN_MOBILENUM = "MobileNum";
    private static final String COLUMN_EMAIL = "Email";

    private Connection mConnection;
    
    public OwnerTableGateway(Connection connection) {
        mConnection = connection;
    }
    
    //@author n00131835

    public List<Owner> getOwners() throws SQLException {
        String query;       // the SQL query to execute
        Statement stmt;     // the java.sql.Statement object used to execute the SQL query
        ResultSet rs;       // the java.sql.ResultSet representing the result of SQL query 
        List<Owner> owners;   // the java.util.List containing the Programmer objects created for each row in the result of the query
        //initiliasing variables from the Owner Class
        int ownerID;             // the id of a owner
        String firstname, lastname, address1, address2, town, county, email;
        int mobilenum;
        Owner o;       // a Owner object created from a row in the result of the query

        // execute an SQL SELECT statement to get a java.util.ResultSet representing
        // the results of the SELECT statement
        query = "SELECT * FROM " + TABLE_NAME; //query being made.
        stmt = this.mConnection.createStatement();
        rs = stmt.executeQuery(query);
        
        // iterate through the result set, extracting the data from each row
        // and storing it in a Owner object, which is inserted into an initially
        // empty ArrayList
        
        owners = new ArrayList<Owner>();
        while (rs.next()) {
            ownerID = rs.getInt(COLUMN_OWNERID);
            firstname = rs.getString(COLUMN_FIRSTNAME);
            lastname = rs.getString(COLUMN_LASTNAME);
            address1 = rs.getString(COLUMN_ADDRESS1);
            address2 = rs.getString(COLUMN_ADDRESS2);
            town = rs.getString(COLUMN_TOWN);
            county = rs.getString(COLUMN_COUNTY);
            mobilenum = rs.getInt(COLUMN_MOBILENUM);
            email = rs.getString(COLUMN_EMAIL);
            
            o = new Owner(ownerID, firstname, lastname, address1, address2, town, county, mobilenum, email);
            owners.add(o);
        }
        return owners;
    }
    
    public boolean deleteOwner(int ownerID) throws SQLException{ //returns true or false if owner has been deleted
        String query; //SQL query to execute
        PreparedStatement stmt; 
        int numRowsAffected; // initialising the variable for the number of rows that will and should be affected in the MySQL
        
        query = "DELETE FROM " + TABLE_NAME + " WHERE " + COLUMN_OWNERID + " = ?"; // the query code that will delete a row, in this case i used the OwnerID to be selected.
               
        stmt = mConnection.prepareStatement(query);
        stmt.setInt(1, ownerID);
        
        numRowsAffected = stmt.executeUpdate();
        
        return (numRowsAffected == 1);
    }
    
    public boolean updateOwner(Owner o) throws SQLException { //returns true or false if owner has been updated
        String query;   // the SQL query to execute
        PreparedStatement stmt;    // the java.sql.PreparedStatement object used to execute the SQL query
        int numRowsAffected;
        
        // the required SQL INSERT statement with place holders for the values to be inserted into the database
        query = "UPDATE " + TABLE_NAME + " SET " +
                COLUMN_FIRSTNAME + " = ?, " +
                COLUMN_LASTNAME + " = ?, " +
                COLUMN_ADDRESS1 + " = ?, " +
                COLUMN_ADDRESS2 + " = ?, " +
                COLUMN_TOWN + " = ?, " +
                COLUMN_COUNTY + " = ?, " +
                COLUMN_MOBILENUM + " = ?, " +
                COLUMN_EMAIL + " = ? " +
                " WHERE " + COLUMN_OWNERID + " = ? ";
        
        // create a PreparedStatement object to execute the query and insert the values into the query
        stmt = mConnection.prepareStatement(query);
        stmt.setString (1, o.getFirstName());
        stmt.setString (2, o.getLastName());
        stmt.setString (3, o.getAddress1());
        stmt.setString (4, o.getAddress2());
        stmt.setString (5, o.getTown());
        stmt.setString (6, o.getCounty());
        stmt.setInt (7, o.getMobileNum());
        stmt.setString (8, o.getEmail());
        stmt.setInt (9, o.getOwnerID());

        //  execute the query and make sure that one and only one row was inserted into the database
        numRowsAffected = stmt.executeUpdate();
        
        // return the Owner object created or null if there was a problem
        return (numRowsAffected == 1);  
    
    }
    
    //@author n00131835
    
    public int insertOwner(String fnO, String lnO, String a1O, String a2O, String tnO, String ctO, int mnO, String eO) throws SQLException { //this query will insert/create a row in the MySQL database
        Owner o = null;
        
        String query;   // the SQL query to execute
        PreparedStatement stmt;    // the java.sql.PreparedStatement object used to execute the SQL query
        int numRowsAffected; // initialising the variable for the number of rows that will and should be affected in the MySQL
        int ownerID = -1;
        
        // the required SQL INSERT statement with place holders for the values to be inserted into the database
        query = "INSERT INTO " + TABLE_NAME + " (" +
                COLUMN_FIRSTNAME + ", " +
                COLUMN_LASTNAME + ", " +
                COLUMN_ADDRESS1 + ", " +
                COLUMN_ADDRESS2 + ", " +
                COLUMN_TOWN + ", " +
                COLUMN_COUNTY + ", " +
                COLUMN_MOBILENUM + ", " +
                COLUMN_EMAIL +
                ") VALUES (?, ?, ?, ?, ?, ?, ?, ?)"; //the ? are placeholders
        
        // create a PreparedStatement object to execute the query and insert the values into the query
        stmt = mConnection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        stmt.setString (1, fnO);
        stmt.setString (2, lnO);
        stmt.setString (3, a1O);
        stmt.setString (4, a2O);
        stmt.setString (5, tnO);
        stmt.setString (6, ctO);
        stmt.setInt (7, mnO);
        stmt.setString (8, eO);

        //  execute the query and make sure that one and only one row was inserted into the database
        numRowsAffected = stmt.executeUpdate();
        if (numRowsAffected == 1) {
            // if one row was inserted, retrieve the id assigned to that row and create a Programmer object to return
            ResultSet keys = stmt.getGeneratedKeys();
            keys.next();
            
            ownerID = keys.getInt(1);
        }
        
        // return the Owner object created or null if there was a problem
        return ownerID;  
    } 
}
