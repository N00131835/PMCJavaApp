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
    private List<Area> areas;
    private List<Owner> owners;
    //private List<Tenant> tenants;
    
    private PropertyTableGateway propertyGateway;
    private AreaTableGateway areaGateway;
    private OwnerTableGateway ownerGateway;
    //private TenantTableGateway tenantGateway;

    //this will connect to the DBConnection class that would be able to connect to the database phpmyadmin
    private Model() {

        try {
            Connection conn = DBConnection.getInstance();
            this.propertyGateway = new PropertyTableGateway(conn);
            this.areaGateway = new AreaTableGateway(conn);
            this.ownerGateway = new OwnerTableGateway(conn);
            //this.tenantGateway = new TenantTableGateway(conn);
            
            this.properties = this.propertyGateway.getProperties();
            this.areas = this.areaGateway.getAreas();
            this.owners = this.ownerGateway.getOwners();
            //this.tenants = this.tenantGateway.getTenants();
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
            propertyGateway.insertProperty(p.getAddress1(), p.getAddress2(), p.getTown(), p.getCounty(), p.getDescription(), p.getRent(), p.getBedrooms());
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
            removed = this.propertyGateway.deleteProperty(p.getPropertyID());
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
            updated = this.propertyGateway.updateProperty(p);
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
    
    ////////////////////////////////////////////////////////////////////
    
    public List<Area> getAreas() {
        return new ArrayList<Area>(this.areas);
    }
    
    //@author n00131835
    
    //the code below will be able to update a area 
    public boolean updateArea(Area a) {
        boolean updated = false;
        
        try {
            updated = this.areaGateway.updateArea(a);
        }
        catch (SQLException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE , null, ex);
        }   
        return updated;
    }
    
    //the code below will be able to find the area by its AreaID
    public Area findAreaById(int areaID) {
        Area a = null;
        int i = 0;
        boolean found = false;
        while (i < this.areas.size() && !found) {
            a = this.areas.get(i);
            if (a.getAreaID() == areaID) {
                found = true;
            } else {
                i++;
            }
        }
        if (!found) {
            a = null;
        }
        return a;
    }
    
    ///////////////////////////////////////////////////////////////////
    
    public List<Owner> getOwners() {
        return new ArrayList<Owner>(this.owners);
    }
    
    //the code below will be able to add a owner
    public void addOwner(Owner o) {
        try {
            ownerGateway.insertOwner(o.getFirstName(), o.getLastName(), o.getAddress1(), o.getAddress2(), o.getTown(), o.getCounty(), o.getMobileNum(), o.getEmail());
            this.owners.add(o);
        } 
        catch (SQLException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //the code below will be able to delete a owner
    public boolean removeOwner(Owner o) {
        boolean removed = false;
        
        try {
            removed = this.ownerGateway.deleteOwner(o.getOwnerID());
            if (removed) {
                removed = this.owners.remove(o);
            }
        }
        catch (SQLException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE , null, ex);
        }   
        return removed;
    }
    
    //@author n00131835
    
    //the code below will be able to update a owner
    public boolean updateOwner(Owner o) {
        boolean updated = false;
        
        try {
            updated = this.ownerGateway.updateOwner(o);
        }
        catch (SQLException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE , null, ex);
        }   
        return updated;
    }
    
    //the code below will be able to find the ownerby its OwnerID
    public Owner findOwnerById(int ownerID) {
        Owner o = null;
        int i = 0;
        boolean found = false;
        while (i < this.owners.size() && !found) {
            o = this.owners.get(i);
            if (o.getOwnerID() == ownerID) {
                found = true;
            } else {
                i++;
            }
        }
        if (!found) {
            o = null;
        }
        return o;
    }
    
}