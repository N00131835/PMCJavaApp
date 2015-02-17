/**
 *
 * //@author n00131835
 * 
 **/
package pmcdemoapp;

//These are the libraries imported for the code to work
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Model {

    private static Model instance = null;

    public static synchronized Model getInstance() {
        if (instance == null) {
            instance = new Model();
        }
        return instance;
    }

    private List<Property> properties;
    private PropertyTableGateway gateway;

    //this will connect to the DBConnection class that would be able to connect to the database phpmyadmin
    private Model() {

        try {
            Connection conn = DBConnection.getInstance();
            gateway = new PropertyTableGateway(conn);
            
            this.properties = this.gateway.getProperties();
        } 
        catch (ClassNotFoundException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        } 
        catch (SQLException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //@author n00131835
        
    public List<Property> getProperties() {
        return new ArrayList<Property>(this.properties);
    }
    
    //the code below will be able to add a property 
    public void addProperty(Property p) {
        try {
            gateway.insertProperty(p.getAddress1(), p.getAddress2(), p.getTown(), p.getCounty(), p.getDescription(), p.getRent(), p.getBedrooms());
            this.properties.add(p);
        } 
        catch (SQLException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //the code below will be able to delete a property 
    public boolean removeProperty(Property p) {
        boolean removed = false;
        
        try {
            removed = this.gateway.deleteProperty(p.getPropertyID());
            if (removed) {
                removed = this.properties.remove(p);
            }
        }
        catch (SQLException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE , null, ex);
        }   
        return removed;
    }
    
    //@author n00131835
    
    //the code below will be able to update a property 
    public boolean updateProperty(Property p) {
        boolean updated = false;
        
        try {
            updated = this.gateway.updateProperty(p);
        }
        catch (SQLException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE , null, ex);
        }   
        return updated;
    }
    
    //the code below will be able to find the property by its PropertyID
    public Property findPropertyById(int propertyID) {
        Property p = null;
        int i = 0;
        boolean found = false;
        while (i < this.properties.size() && !found) {
            p = this.properties.get(i);
            if (p.getPropertyID() == propertyID) {
                found = true;
            } else {
                i++;
            }
        }
        if (!found) {
            p = null;
        }
        return p;
    }
}
