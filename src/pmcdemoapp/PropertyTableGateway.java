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
class PropertyTableGateway {
    //The column in the PHPMYADMIN is being initialised here
    private static final String TABLE_NAME = "property";
    private static final String COLUMN_PROPERTYID = "PropertyID";
    private static final String COLUMN_ADDRESS1 = "Address1";
    private static final String COLUMN_ADDRESS2 = "Address2";
    private static final String COLUMN_TOWN = "Town";
    private static final String COLUMN_COUNTY = "County";
    private static final String COLUMN_AREAIDP = "AreaID";
    private static final String COLUMN_DESCRIPTION = "Description";
    private static final String COLUMN_RENT = "Rent";
    private static final String COLUMN_BEDROOMS = "Bedrooms";

    private Connection mConnection;
    
    public PropertyTableGateway(Connection connection) {
        mConnection = connection;
    }
    
    //@author n00131835

    public List<Property> getProperties() throws SQLException {
        String query;       // the SQL query to execute
        Statement stmt;     // the java.sql.Statement object used to execute the SQL query
        ResultSet rs;       // the java.sql.ResultSet representing the result of SQL query 
        List<Property> properties;   // the java.util.List containing the Programmer objects created for each row in the result of the query
        //initiliasing variables from the Property Class
        int propertyID;             // the id of a property
        String address1, address2, town, county, description;
        int rent, areaId, bedrooms;
        Property p;       // a Property object created from a row in the result of the query

        // execute an SQL SELECT statement to get a java.util.ResultSet representing
        // the results of the SELECT statement
        query = "SELECT * FROM " + TABLE_NAME; //query being made.
        stmt = this.mConnection.createStatement();
        rs = stmt.executeQuery(query);
        
        // iterate through the result set, extracting the data from each row
        // and storing it in a Property object, which is inserted into an initially
        // empty ArrayList
        
        properties = new ArrayList<Property>();
        while (rs.next()) {
            propertyID = rs.getInt(COLUMN_PROPERTYID);
            address1 = rs.getString(COLUMN_ADDRESS1);
            address2 = rs.getString(COLUMN_ADDRESS2);
            town = rs.getString(COLUMN_TOWN);
            county = rs.getString(COLUMN_COUNTY);
            areaId = rs.getInt(COLUMN_AREAIDP);
            description = rs.getString(COLUMN_DESCRIPTION);
            rent = rs.getInt(COLUMN_RENT);
            bedrooms = rs.getInt(COLUMN_BEDROOMS);
            
            p = new Property(propertyID, address1, address2, town, county, areaId, description, rent, bedrooms);
            properties.add(p);
        }
        return properties;
    }
    
    public boolean deleteProperty(int propertyID) throws SQLException{ //returns true or false if property has been deleted
        String query; //SQL query to execute
        PreparedStatement stmt; 
        int numRowsAffected; // initialising the variable for the number of rows that will and should be affected in the MySQL
        
        query = "DELETE FROM " + TABLE_NAME + " WHERE " + COLUMN_PROPERTYID + " = ?"; // the query code that will delete a row, in this case i used the PropertyID to be selected.
               
        stmt = mConnection.prepareStatement(query);
        stmt.setInt(1, propertyID);
        
        numRowsAffected = stmt.executeUpdate();
        
        return (numRowsAffected == 1);
    }
    
    public boolean updateProperty(Property p) throws SQLException { //returns true or false if property has been updated
        String query;   // the SQL query to execute
        PreparedStatement stmt;    // the java.sql.PreparedStatement object used to execute the SQL query
        int numRowsAffected;
        
        // the required SQL INSERT statement with place holders for the values to be inserted into the database
        query = "UPDATE " + TABLE_NAME + " SET " +
                COLUMN_ADDRESS1 + " = ?, " +
                COLUMN_ADDRESS2 + " = ?, " +
                COLUMN_TOWN + " = ?, " +
                COLUMN_COUNTY + " = ?, " +
                COLUMN_AREAIDP + " = ?, " +
                COLUMN_DESCRIPTION + " = ?, " +
                COLUMN_RENT + " = ?, " +
                COLUMN_BEDROOMS + " = ? " +
                " WHERE " + COLUMN_PROPERTYID + " = ? ";
        
        // create a PreparedStatement object to execute the query and insert the values into the query
        stmt = mConnection.prepareStatement(query);
        stmt.setString (1, p.getAddress1());
        stmt.setString (2, p.getAddress2());
        stmt.setString (3, p.getTown());
        stmt.setString (4, p.getCounty());
        stmt.setInt (5, p.getAreaId());
        stmt.setString (6, p.getDescription());
        stmt.setInt (7, p.getRent());
        stmt.setInt (8, p.getBedrooms());
        stmt.setInt (9, p.getPropertyID());

        //  execute the query and make sure that one and only one row was inserted into the database
        numRowsAffected = stmt.executeUpdate();
        
        // return the Property object created or null if there was a problem
        return (numRowsAffected == 1);  
    
    }
    
    //@author n00131835
    
    public int insertProperty(String a1, String a2, String tn, String ct, int aIDp, String d, int r, int b) throws SQLException { //this query will insert/create a row in the MySQL database
        Property p = null;
        
        String query;   // the SQL query to execute
        PreparedStatement stmt;    // the java.sql.PreparedStatement object used to execute the SQL query
        int numRowsAffected; // initialising the variable for the number of rows that will and should be affected in the MySQL
        int propertyID = -1;
        
        // the required SQL INSERT statement with place holders for the values to be inserted into the database
        query = "INSERT INTO " + TABLE_NAME + " (" +
                COLUMN_ADDRESS1 + ", " +
                COLUMN_ADDRESS2 + ", " +
                COLUMN_TOWN + ", " +
                COLUMN_COUNTY + ", " +
                COLUMN_AREAIDP + ", " +
                COLUMN_DESCRIPTION + ", " +
                COLUMN_RENT + ", " +
                COLUMN_BEDROOMS +
                ") VALUES (?, ?, ?, ?, ?, ?, ?, ?)"; //the ? are placeholders
        
        // create a PreparedStatement object to execute the query and insert the values into the query
        stmt = mConnection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        stmt.setString (1, a1);
        stmt.setString (2, a2);
        stmt.setString (3, tn);
        stmt.setString (4, ct);
        stmt.setInt (5, aIDp);
        stmt.setString (6, d);
        stmt.setInt (7, r);
        stmt.setInt (8, b);

        //  execute the query and make sure that one and only one row was inserted into the database
        numRowsAffected = stmt.executeUpdate();
        if (numRowsAffected == 1) {
            // if one row was inserted, retrieve the id assigned to that row and create a Programmer object to return
            ResultSet keys = stmt.getGeneratedKeys();
            keys.next();
            
            propertyID = keys.getInt(1);
        }
        
        // return the Property object created or null if there was a problem
        return propertyID;  
    } 
}
