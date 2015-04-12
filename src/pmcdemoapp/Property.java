/**
 *
 * //@author n00131835
 * 
 **/
package pmcdemoapp;

public class Property {

    //initialising the variables
    private int propertyID;
    private String address1;
    private String address2;
    private String town;
    private String county;
    private int areaId;
    private String description;
    private int rent;
    private int bedrooms;

    //paramitized constructor
    public Property(int propertyID, String a1, String a2, String tn, String ct, int aIDp, String d, int r, int b) {
        this.propertyID = propertyID;
        this.address1 = a1;
        this.address2 = a2;
        this.town = tn;
        this.county = ct;
        this.areaId = aIDp;
        this.description = d;
        this.rent = r;
        this.bedrooms = b;
    } 
    
    public Property(String a1, String a2, String tn, String ct, int aIDp, String d, int r, int b) {
        //The PropertyID is -1 because we don't want the user to change or edit the PropertyID values because it's auto-incremented in PHPMYADMIN 
        this(-1, a1, a2, tn, ct, aIDp, d, r, b); 
    }
    
    // get and set methods for PropertyID
    public int getPropertyID() {
        return propertyID;
    }

    public void setPropertyID(int propertyID) {
        this.propertyID = propertyID;
    }

    // get and set methods for Address1
    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }
     
    // get and set methods for Address2
    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }
    
    //@author n00131835
    
    // get and set methods for Town
    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }
    
    // get and set methods for County
    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    // get and set methods for AreaId
    public int getAreaId() {
        return areaId;
    }

    public void setAreaId(int areaId) {
        this.areaId = areaId;
    }

    // get and set methods for Description
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    // get and set methods for Rent
    public int getRent() {
        return rent;
    }

    public void setRent(int rent) {
        this.rent = rent;
    }

    // get and set methods for Bedrooms
    public int getBedrooms() {
        return bedrooms;
    }

    public void setBedrooms(int bedrooms) {
        this.bedrooms = bedrooms;
    }
    //@author n00131835
}
