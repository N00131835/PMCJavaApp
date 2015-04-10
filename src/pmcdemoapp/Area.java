/**
 *
 * //@author n00131835
 * 
 **/
package pmcdemoapp;

public class Area {

    //initialising the variables
    private int areaID;
    private String areaname;
    private String facilities;

    //paramitized constructor
    public Area(int areaID, String aN, String fL) {
        this.areaID = areaID;
        this.areaname = aN;
        this.facilities = fL;
    } 
    
    public Area(String aN, String fL) {
        //The AreaID is -1 because ew don't want the user to change or edit the AreaID values because it's auto-incremented in PHPMYADMIN 
        this(-1, aN, fL); 
    }
    
    // get and set methods for AreaID
    public int getAreaID() {
        return areaID;
    }

    public void setAreaID(int areaID) {
        this.areaID = areaID;
    }

    // get and set methods for Address1
    public String getAreaName() {
        return areaname;
    }

    public void setAreaName(String areaname) {
        this.areaname = areaname;
    }
     
    // get and set methods for Address2
    public String getFacilities() {
        return facilities;
    }

    public void setFacilities(String facilities) {
        this.facilities = facilities;
    }
    //@author n00131835
}
