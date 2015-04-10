/**
 *
 * //@author n00131835
 * 
 **/
package pmcdemoapp;

public class Owner {

    //initialising the variables
    private int ownerID;
    private String firstname;
    private String lastname;
    private String address1;
    private String address2;
    private String town;
    private String county;
    private int mobilenum;
    private String email;

    //paramitized constructor
    public Owner(int ownerID, String fnO, String lnO, String a1O, String a2O, String tnO, String ctO, int mnO, String eO) {
        this.ownerID = ownerID;
        this.firstname = fnO;
        this.lastname = lnO;
        this.address1 = a1O;
        this.address2 = a2O;
        this.town = tnO;
        this.county = ctO;
        this.mobilenum = mnO;
        this.email = eO;
    } 
    
    public Owner(String fnO, String lnO, String a1O, String a2O, String tnO, String ctO, int mnO, String eO) {
        //The OwnerID is -1 because ew don't want the user to change or edit the OwnerID values because it's auto-incremented in PHPMYADMIN 
        this(-1, fnO, lnO, a1O, a2O, tnO, ctO, mnO, eO); 
    }
    
    // get and set methods for OwnerID
    public int getOwnerID() {
        return ownerID;
    }

    public void setOwnerID(int ownerID) {
        this.ownerID = ownerID;
    }

    // get and set methods for FirstName
    public String getFirstName() {
        return firstname;
    }

    public void setFirstName(String firstname) {
        this.firstname = firstname;
    }

    // get and set methods for LastName
    public String getLastName() {
        return lastname;
    }

    public void setLastName(String lastname) {
        this.lastname = lastname;
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

    // get and set methods for MobileNum
    public int getMobileNum() {
        return mobilenum;
    }

    public void setMobileNum(int mobilenum) {
        this.mobilenum = mobilenum;
    }

    // get and set methods for Email
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    //@author n00131835
}
