/**
 *
 * @author n00131835

 * // 1. present menu, read option and display option chosen
 
   // 2. do above but inside a loop until user chooses exit
 
   // 3. as above but display a different message for each menu option chosen
 
   // 4. implement the option to view the property
 
   // 5. implement the option to create a new property
 
   // 6. implement the option to delete a property
 
 * // 7. implement the option to edit a property
 
 */

package pmcdemoapp;

import java.util.List;
import java.util.Scanner;

public class PMCDemoApp {

    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);
        
        Model model; // Model Class
        
        Property p; // Property Class
        Owner o; // Owner Class
        
        int option = 14; // initialising the variable option, 14 is the default value because its the option for the exit feature for this program.
        
        //a Do-While loop is used so that we can keep asking the user what they need to do, and it will stop looping when the user press the exit option, which in this case it's 5
        do {
            //this is the main menu
            try{
                model = Model.getInstance();
                System.out.println("-------------------------");
                System.out.println("--------PROPERTY---------");
                System.out.println("-------------------------");
                System.out.println("1. Create new Property");
                System.out.println("2. View all Properties");
                System.out.println("3. Edit existing Property");
                System.out.println("4. View a single Property");
                System.out.println("5. Delete Property");
                System.out.println("-------------------------");
                System.out.println("----------AREA-----------");
                System.out.println("-------------------------");
                System.out.println("6. View all Areas");
                System.out.println("7. Edit existing Area");
                System.out.println("8. View a single Area");
                System.out.println("-------------------------");
                System.out.println("----------OWNER----------");
                System.out.println("-------------------------");
                System.out.println("9. Create new Owner");
                System.out.println("10. View all Owners");
                System.out.println("11. Edit existing Owner");
                System.out.println("12. View a single Owner");
                System.out.println("13. Delete Owner");
                System.out.println("-------------------------");
                System.out.println("14. Exit");
                System.out.println("-------------------------");
                System.out.println();

                //This is where the user will type the option they want to pick
                option = getInt(keyboard, "Enter an option: ", 11);

                //@author n00131835
                //System.out.println("You chose option " + option);
                switch (option) {
                    //PROPERTY OPTIONS
                    case 1: {
                        System.out.println("Creating a Property");
                        System.out.println();
                        createProperty(keyboard, model);
                        break;
                        //option 1 is Creating a Property
                    }
                    case 2: {
                        System.out.println("Viewing all the Properties");
                        System.out.println();
                        viewProperty(model);
                        break;
                        //option 2 is Viewing all the Properties
                    }
                    case 3: {
                        System.out.println("Editing a Property");
                        System.out.println();
                        editProperty(keyboard, model);
                        break;
                        //option 3 is Editing a Property
                    }
                    case 4: {
                        System.out.println("Deleting a Property");
                        System.out.println();
                        deleteProperty(keyboard, model);
                        break;
                        //option 4 is Deleting a Property
                    }

                    // AREA OPTIONS
                    case 5: {
                        System.out.println("Viewing all Areas");
                        System.out.println();
                        viewArea(model);
                        break;
                        //option 2 is Viewing all the Properties
                    }
                    case 6: {
                        System.out.println("Editing an Area");
                        System.out.println();
                        editArea(keyboard, model);
                        break;
                        //option 3 is Editing a Property
                    }

                    //OWNER OPTIONS
                    case 7: {
                        System.out.println("Creating an Owner");
                        System.out.println();
                        o = readOwner(keyboard);
                        model.addOwner(o);
                        break;
                        //option 1 is Creating an Owner
                    }
                    case 8: {
                        System.out.println("Viewing all the Owners");
                        System.out.println();
                        viewOwner(model);
                        break;
                        //option 2 is Viewing all the Owners
                    }
                    case 9: {
                        System.out.println("Editing an Owner");
                        System.out.println();
                        editOwner(keyboard, model);
                        break;
                        //option 3 is Editing an Owner
                    }
                    case 10: {
                        System.out.println("Deleting an Owner");
                        System.out.println();
                        deleteOwner(keyboard, model);
                        break;
                        //option 4 is Deleting an Owner
                    }
                }
            }
            catch(DataAccessException e){
                System.out.println();
                System.out.println(e.getMessage());
                System.out.println();
            }
        }
        while (option != 14);
        //option 5 is going to stop the application from running.
        System.out.println("EXIT");
        
    }
    
    //This method is for the variables that needs getString option
    private static String getString(Scanner keyboard, String prompt) {
        System.out.print(prompt);
        return keyboard.nextLine();
    }
    
    private static int getInt(Scanner keyboard, String prompt, int defValue){
        int option = defValue;
        boolean finished = false;
        
        do{
            try{
                System.out.print(prompt);
                String line = keyboard.nextLine();
                if (line.length() > 0){
                    option = Integer.parseInt(line);
                }
                finished = true;
            }
            catch(NumberFormatException e) {
                System.out.println("Exception: " + e.getMessage());
                System.out.println();
                System.out.println();
            }
        }
        while (!finished);
        
        return option;
    }
    
    private static void createProperty(Scanner keyboard, Model model) throws DataAccessException {
        Property p = readProperty(keyboard);
        if (model.addProperty(p)) {
            System.out.println();
            System.out.println("Property successfully added to the database");
            System.out.println();
            System.out.println(); //This is just a line break so that the code in not too squished.
        }
        else {
            System.out.println();
            System.out.println("Property not added to the database");
            System.out.println();
            System.out.println(); //This is just a line break so that the code in not too squished.
         }
    }
    
    //the code below is the one that creates the property when you run the application
    private static Property readProperty(Scanner keyboard) {
        //initiliasing variables from the Property Class
        String address1, address2, town, county, description;
        int areaId, rent, bedrooms;

        address1 = getString(keyboard, "Enter address1: ");
        address2 = getString(keyboard, "Enter address2: ");
        town = getString(keyboard, "Enter town: ");
        county = getString(keyboard, "Enter county: ");
        areaId = getInt(keyboard, "Enter areaId (areaId can only be 1,2,3,4,5): ", 0);
        description = getString(keyboard, "Enter description: ");
        rent = getInt(keyboard, "Enter rent: ", 0);
        bedrooms = getInt(keyboard, "Enter bedrooms: ", 0);
        System.out.println();
        System.out.println();

        Property p = new Property(address1, address2, town, county, areaId, description, rent, bedrooms);
        
        return p;
    }  

    //the code below is the one that can show the user the property when you run the application
    private static void viewProperty(Model model) {
        List<Property> properties = model.getProperties();
        if (properties.isEmpty()){
            System.out.println("The are no properties in the database.");
        }
        else {
            // This will create the columns that will appear here in the output when you run the app.
            System.out.printf("%10s %20s %20s %15s %12s %25s %25s %10s %10s\n", "PropertyID", "Address1", "Address2", "Town", "County", "AreaID", "Description", "Rent", "Bedrooms");
            for (Property p : properties) {
                int length = p.getDescription().length();
                if (length > 25) {
                    length = 25;
                }
                
                Area a = model.findAreaById(p.getAreaId());
                // This will create the columns where the info from the database is being placed.
                System.out.printf("%10d %20s %20s %15s %12s %25s %25s %10d %10d\n",
                        p.getPropertyID(),
                        p.getAddress1(),
                        p.getAddress2(),
                        p.getTown(),
                        p.getCounty(),
                        (a != null) ? a.getAreaName() : "No Area", //tertiary expression
                        p.getDescription().substring(0, length),
                        p.getRent(),
                        p.getBedrooms());
            }
        System.out.println(); 
        System.out.println(); //This is just a line break so that the code in not too squished.
        }
    }

    //@author n00131835
    
    //the code below allows the user to edit the property when you run the application
    private static void editPropertyDetails(Scanner keyboard, Property p) {
        //initiliasing variables from the Property Class
        String address1, address2, town, county, description;
        int areaId, rent, bedrooms;
        
        //this will get the information for the database and place it in i.e. p.getAddress1
        address1 = getString(keyboard, "Edit address1: [" + p.getAddress1() + "]: ");
        address2 = getString(keyboard, "Edit address2: [" + p.getAddress2() + "]: ");
        town = getString(keyboard, "Edit town: [" + p.getTown() + "]: ");
        county = getString(keyboard, "Edit county: [" + p.getCounty() + "]: ");
        areaId = getInt(keyboard, "Enter areaId (areaId can only be 1,2,3,4,5): [" + p.getAreaId() + "]: ", 0);
        description = getString(keyboard, "Edit description: [" + p.getDescription() + "]: ");
        rent = getInt(keyboard, "Edit rent: [" + p.getRent() + "]: ", 0);
        bedrooms = getInt(keyboard, "Edit bedrooms: [" + p.getBedrooms() + "]: ", 0);
    
        //while the code below will set it to whichever the user type into the database and later on we can check(view) whether it worked or not.
        if (address1.length() != 0) {
            p.setAddress1(address1);
        }
        if (address2.length() != 0) {
            p.setAddress2(address2);
        }
        if (town.length() != 0) {
            p.setTown(town);
        }
        if (county.length() != 0) {
            p.setCounty(county);
        }
        if (areaId != p.getAreaId()) {
            p.setAreaId(areaId);
        }
        if (description.length() != 0) {
            p.setDescription(description);
        }
        if (rent != p.getRent()) {
            p.setRent(rent);
        }
        if (bedrooms != p.getBedrooms()) {
            p.setBedrooms(bedrooms);
        }
    }
    
    //the code below will return true or false whether the Property has been updated or not.
    private static void editProperty(Scanner keyboard, Model model) throws DataAccessException {
        int propertyID = getInt(keyboard, "Enter the PropertyID of the property you wish to edit: ", 0);
        Property p;

        p = model.findPropertyById(propertyID);
        if (p != null) {
            editPropertyDetails(keyboard, p);
            if (model.updateProperty(p)) {
                System.out.println();
                System.out.println();
                System.out.println("Property updated");
                System.out.println();
                System.out.println(); //This is just a line break so that the code in not too squished.
            }
            else {
                System.out.println();
                System.out.println();
                System.out.println("Property not updated");
                System.out.println();
                System.out.println(); //This is just a line break so that the code in not too squished.
            }
        }
        else {
            System.out.println();
            System.out.println();
            System.out.println("Property not found");
            System.out.println();
            System.out.println(); //This is just a line break so that the code is not too squished.
        }
    }
    
    //the code below will return true or false whether the Property has been deleted or not.
    private static void deleteProperty(Scanner keyboard, Model model) throws DataAccessException {
        int propertyID = getInt(keyboard, "Enter the PropertyID of the property you wish to delete: ", 0);
        Property p;

        p = model.findPropertyById(propertyID);
        if (p != null) {
            if (model.removeProperty(p)) {
                System.out.println();
                System.out.println();
                System.out.println("Property deleted");
                System.out.println();
                System.out.println();//This is just a line break so that the code is not too squished.
            }
            else {
                System.out.println();
                System.out.println();
                System.out.println("Property not deleted");
                System.out.println();
                System.out.println();//This is just a line break so that the code is not too squished.
            }
        }
        else {
            System.out.println();
            System.out.println();
            System.out.println("Property not found");
            System.out.println();
            System.out.println();//This is just a line break so that the code is not too squished.
        }
    }
    //@author n00131835
    
    ////////////////////////////////////////////////////////////////////////////
    
    //the code below is the one that can show the user the area when you run the application
    private static void viewArea(Model model) {
        List<Area> areas = model.getAreas();
        if (areas.isEmpty()){
            System.out.println("The are no areas in the database.");
        }
        else {
            // This will create the columns that will appear here in the output when you run the app.
            System.out.printf("%10s %20s %20s", "AreaID", "AreaName", "Facilities");
            for (Area a : areas) {
                // This will create the columns where the info from the database is being placed.
                System.out.printf("%10d %20s %20s",
                        a.getAreaID(),
                        a.getAreaName(),
                        a.getFacilities());
            }
        System.out.println(); 
        System.out.println(); //This is just a line break so that the code in not too squished.
        }
    }

    //@author n00131835
    
    //the code below allows the user to edit the area when you run the application
    private static void editAreaDetails(Scanner keyboard, Area a) {
        //initiliasing variables from the Area Class
        String areaname, facilities;
        
        //this will get the information for the database and place it in i.e. a.getAreaName
        areaname = getString(keyboard, "Edit AreaName: [" + a.getAreaName() + "]: ");
        facilities = getString(keyboard, "Edit Facilities: [" + a.getFacilities() + "]: ");
    
        //while the code below will set it to whichever the user type into the database and later on we can check(view) whether it worked or not.
        if (areaname.length() != 0) {
            a.setAreaName(areaname);
        }
        if (facilities.length() != 0) {
            a.setFacilities(facilities);
        }
    }
    
    //the code below will return true or false whether the Area has been updated or not.
    private static void editArea(Scanner keyboard, Model model) throws DataAccessException {
        int areaID = getInt(keyboard, "Enter the AreaID of the area you wish to edit: ", 0);
        Area a;

        a = model.findAreaById(areaID);
        if (a != null) {
            editAreaDetails(keyboard, a);
            if (model.updateArea(a)) {
                System.out.println();
                System.out.println();
                System.out.println("Area updated");
                System.out.println();
                System.out.println(); //This is just a line break so that the code in not too squished.
            }
            else {
                System.out.println();
                System.out.println();
                System.out.println("Area not updated");
                System.out.println();
                System.out.println(); //This is just a line break so that the code in not too squished.
            }
        }
        else {
            System.out.println();
            System.out.println();
            System.out.println("Area not found");
            System.out.println();
            System.out.println(); //This is just a line break so that the code is not too squished.
        }
    }
    
    ////////////////////////////////////////////////////////////////////////////
    
    //the code below is the one that creates the owner when you run the application
    private static Owner readOwner(Scanner keyboard) {
        //initiliasing variables from the Owner Class
        String firstname, lastname, address1, address2, town, county, email;
        int mobilenum;

        firstname = getString(keyboard, "Enter firstname: ");
        lastname = getString(keyboard, "Enter lastname: ");
        address1 = getString(keyboard, "Enter address1: ");
        address2 = getString(keyboard, "Enter address2: ");
        town = getString(keyboard, "Enter town: ");
        county = getString(keyboard, "Enter county: ");
        mobilenum = getInt(keyboard, "Enter mobilenum: ", 0);
        email = getString(keyboard, "Enter email: ");
        System.out.println();
        System.out.println();

        Owner o = new Owner(firstname, lastname, address1, address2, town, county, mobilenum, email);
        
        return o;
    }  

    //the code below is the one that can show the user the owner when you run the application
    private static void viewOwner(Model model) {
        List<Owner> owners = model.getOwners();
        if (owners.isEmpty()){
            System.out.println("The are no owners in the database.");
        }
        else {
            // This will create the columns that will appear here in the output when you run the apo.
            System.out.printf("%10s %15s %15s %20s %20s %18s %12s %15s %30s\n", "OwnerID", "FirstName", "LastName", "Address1", "Address2", "Town", "County", "MobileNum", "Email");
            for (Owner o : owners) {
                int length = o.getFirstName().length();
                if (length > 25) {
                    length = 25;
                }
                // This will create the columns where the info from the database is being placed.
                System.out.printf("%10d %15s %15s %20s %20s %18s %12s %15d %30s\n",
                        o.getOwnerID(),
                        o.getFirstName(),
                        o.getLastName(),
                        o.getAddress1(),
                        o.getAddress2(),
                        o.getTown(),
                        o.getCounty(),
                        o.getMobileNum(),
                        o.getEmail());
            }
        System.out.println(); 
        System.out.println(); //This is just a line break so that the code in not too squished.
        }
    }

    //@author n00131835
    
    //the code below allows the user to edit the owner when you run the application
    private static void editOwnerDetails(Scanner keyboard, Owner o) {
        //initiliasing variables from the Owner Class
        String firstname, lastname, address1, address2, town, county, email;
        int mobilenum;
        
        //this will get the information for the database and place it in i.e. o.getAddress1
        firstname = getString(keyboard, "Edit firstname: [" + o.getFirstName() + "]: ");
        lastname = getString(keyboard, "Edit lastname: [" + o.getLastName() + "]: ");
        address1 = getString(keyboard, "Edit address1: [" + o.getAddress1() + "]: ");
        address2 = getString(keyboard, "Edit address2: [" + o.getAddress2() + "]: ");
        town = getString(keyboard, "Edit town: [" + o.getTown() + "]: ");
        county = getString(keyboard, "Edit county: [" + o.getCounty() + "]: ");
        mobilenum = getInt(keyboard, "Edit mobilenum: [" + o.getMobileNum() + "]: ", 0);
        email = getString(keyboard, "Edit email: [" + o.getEmail() + "]: ");
    
        //while the code below will set it to whichever the user type into the database and later on we can check(view) whether it worked or not.
        if (firstname.length() != 0) {
            o.setFirstName(firstname);
        }
        if (lastname.length() != 0) {
            o.setLastName(lastname);
        }
        if (address1.length() != 0) {
            o.setAddress1(address1);
        }
        if (address2.length() != 0) {
            o.setAddress2(address2);
        }
        if (town.length() != 0) {
            o.setTown(town);
        }
        if (county.length() != 0) {
            o.setCounty(county);
        }
        if (mobilenum != o.getMobileNum()) {
            o.setMobileNum(mobilenum);
        }
        if (email.length() != 0) {
            o.setEmail(email);
        }
    }
    
    //the code below will return true or false whether the Owner has been updated or not.
    private static void editOwner(Scanner keyboard, Model model) throws DataAccessException {
        int ownerID = getInt(keyboard, "Enter the OwnerID of the owner you wish to edit: ", 0);
        Owner o;

        o = model.findOwnerById(ownerID);
        if (o != null) {
            editOwnerDetails(keyboard, o);
            if (model.updateOwner(o)) {
                System.out.println();
                System.out.println();
                System.out.println("Owner updated");
                System.out.println();
                System.out.println(); //This is just a line break so that the code in not too squished.
            }
            else {
                System.out.println();
                System.out.println();
                System.out.println("Owner not updated");
                System.out.println();
                System.out.println(); //This is just a line break so that the code in not too squished.
            }
        }
        else {
            System.out.println();
            System.out.println();
            System.out.println("Owner not found");
            System.out.println();
            System.out.println(); //This is just a line break so that the code is not too squished.
        }
    }
    
    //the code below will return true or false whether the Owner has been deleted or not.
    private static void deleteOwner(Scanner keyboard, Model model) throws DataAccessException {
        int ownerID = getInt(keyboard, "Enter the OwnerID of the owner you wish to delete: ", 0);
        Owner o;

        o = model.findOwnerById(ownerID);
        if (o != null) {
            if (model.removeOwner(o)) {
                System.out.println();
                System.out.println();
                System.out.println("Owner deleted");
                System.out.println();
                System.out.println();//This is just a line break so that the code is not too squished.
            }
            else {
                System.out.println();
                System.out.println();
                System.out.println("Owner not deleted");
                System.out.println();
                System.out.println();//This is just a line break so that the code is not too squished.
            }
        }
        else {
            System.out.println();
            System.out.println();
            System.out.println("Owner not found");
            System.out.println();
            System.out.println();//This is just a line break so that the code is not too squished.
        }
    }
    //@author n00131835
}
