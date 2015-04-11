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
        
        Model model = Model.getInstance(); // Model Class
        
        Property p; // Property Class
        Owner o; // Owner Class
        Tenant t; // Tenant Class
        
        int option; // initialising the variable option
        
        //a Do-While loop is used so that we can keep asking the user what they need to do, and it will stop looping when the user press the exit option, which in this case it's 5
        do {
            //this is the main menu
            
            System.out.println("-------------------------");
            System.out.println("--------PROPERTY---------");
            System.out.println("-------------------------");
            System.out.println("1. Create new Property");
            System.out.println("2. View all Property");
            System.out.println("3. Edit existing Property");
            System.out.println("4. Delete Property");
            System.out.println("-------------------------");
            System.out.println("----------AREA-----------");
            System.out.println("-------------------------");
            System.out.println("5. View all Areas");
            System.out.println("6. Edit existing Area");
            System.out.println("-------------------------");
            System.out.println("----------OWNER----------");
            System.out.println("-------------------------");
            System.out.println("7. Create new Owner");
            System.out.println("8. View all Owner");
            System.out.println("9. Edit existing Owner");
            System.out.println("10. Delete Owner");
            System.out.println("-------------------------");
            System.out.println("---------TENANT----------");
            System.out.println("-------------------------");
            System.out.println("11. Create new Tenant");
            System.out.println("12. View all Tenant");
            System.out.println("13. Edit existing Tenant");
            System.out.println("14. Delete Tenant");
            System.out.println("-------------------------");
            System.out.println("15. Exit");
            System.out.println("-------------------------");
            System.out.println();
            
            //This is where the user will type the option they want to pick
            System.out.print("Enter an option: ");
            String line = keyboard.nextLine();
            option = Integer.parseInt(line);
            
            //@author n00131835
            //System.out.println("You chose option " + option);
            switch (option) {
                //PROPERTY OPTIONS
                case 1: {
                    System.out.println("Creating a Property");
                    System.out.println();
                    p = readProperty(keyboard);
                    model.addProperty(p);
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
                
                //TENANT OPTIONS
                case 11: {
                    System.out.println("Creating an Tenant");
                    System.out.println();
                    t = readTenant(keyboard);
                    model.addTenant(t);
                    break;
                    //option 1 is Creating an Tenant
                }
                case 12: {
                    System.out.println("Viewing all the Tenants");
                    System.out.println();
                    viewTenant(model);
                    break;
                    //option 2 is Viewing all the Tenants
                }
                case 13: {
                    System.out.println("Editing an Tenant");
                    System.out.println();
                    editTenant(keyboard, model);
                    break;
                    //option 3 is Editing an Tenant
                }
                case 14: {
                    System.out.println("Deleting an Tenant");
                    System.out.println();
                    deleteTenant(keyboard, model);
                    break;
                    //option 4 is Deleting an Tenant
                }
            }
        }
        while (option != 15);
        //option 5 is going to stop the application from running.
        System.out.println("Goodbye");
        
    }
    
    //This method is for the variables that needs getString option
    private static String getString(Scanner keyboard, String prompt) {
        System.out.print(prompt);
        return keyboard.nextLine();
    }
   
    //the code below is the one that creates the property when you run the application
    private static Property readProperty(Scanner keyboard) {
        //initiliasing variables from the Property Class
        String address1, address2, town, county, description;
        int rent, bedrooms;
        String line1, line2;

        address1 = getString(keyboard, "Enter address1: ");
        address2 = getString(keyboard, "Enter address2: ");
        town = getString(keyboard, "Enter town: ");
        county = getString(keyboard, "Enter county: ");
        description = getString(keyboard, "Enter description: ");
        line1 = getString(keyboard, "Enter rent: ");
        rent = Integer.parseInt(line1);
        line2 = getString(keyboard, "Enter bedrooms: ");
        bedrooms = Integer.parseInt(line2);
        System.out.println();
        System.out.println();

        Property p = new Property(address1, address2, town, county, description, rent, bedrooms);
        
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
            System.out.printf("%10s %20s %20s %15s %12s %25s %10s %10s\n", "PropertyID", "Address1", "Address2", "Town", "County", "Description", "Rent", "Bedrooms");
            for (Property p : properties) {
                int length = p.getDescription().length();
                if (length > 25) {
                    length = 25;
                }
                // This will create the columns where the info from the database is being placed.
                System.out.printf("%10d %20s %20s %15s %12s %25s %10d %10d\n",
                        p.getPropertyID(),
                        p.getAddress1(),
                        p.getAddress2(),
                        p.getTown(),
                        p.getCounty(),
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
        int rent, bedrooms;
        String line1, line2;
        
        //this will get the information for the database and place it in i.e. p.getAddress1
        address1 = getString(keyboard, "Enter address1: [" + p.getAddress1() + "]: ");
        address2 = getString(keyboard, "Enter address2: [" + p.getAddress2() + "]: ");
        town = getString(keyboard, "Enter town: [" + p.getTown() + "]: ");
        county = getString(keyboard, "Enter county: [" + p.getCounty() + "]: ");
        description = getString(keyboard, "Enter description: [" + p.getDescription() + "]: ");
        line1 = getString(keyboard, "Enter rent: [" + p.getRent() + "]: ");
        line2 = getString(keyboard, "Enter bedrooms: [" + p.getBedrooms() + "]: ");
    
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
        if (description.length() != 0) {
            p.setDescription(description);
        }
        if (line1.length() != 0) {
            rent = Integer.parseInt(line1);
            p.setRent(rent);
        }
        if (line2.length() != 0) {
            bedrooms = Integer.parseInt(line2);
            p.setBedrooms(bedrooms);
        }
    }
    
    //the code below will return true or false whether the Property has been updated or not.
    private static void editProperty(Scanner keyboard, Model model) {
        System.out.print("Enter the PropertyID of the property you wish to edit: ");
        int propertyID = Integer.parseInt(keyboard.nextLine());
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
    private static void deleteProperty(Scanner keyboard, Model model) {
        System.out.print("Enter the PropertyID of the property you wish to delete: ");
        int propertyID = Integer.parseInt(keyboard.nextLine());
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
        areaname = getString(keyboard, "Enter AreaName: [" + a.getAreaName() + "]: ");
        facilities = getString(keyboard, "Enter Facilities: [" + a.getFacilities() + "]: ");
    
        //while the code below will set it to whichever the user type into the database and later on we can check(view) whether it worked or not.
        if (areaname.length() != 0) {
            a.setAreaName(areaname);
        }
        if (facilities.length() != 0) {
            a.setFacilities(facilities);
        }
    }
    
    //the code below will return true or false whether the Area has been updated or not.
    private static void editArea(Scanner keyboard, Model model) {
        System.out.print("Enter the AreaID of the area you wish to edit: ");
        int areaID = Integer.parseInt(keyboard.nextLine());
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
        String line3;

        firstname = getString(keyboard, "Enter firstname: ");
        lastname = getString(keyboard, "Enter lastname: ");
        address1 = getString(keyboard, "Enter address1: ");
        address2 = getString(keyboard, "Enter address2: ");
        town = getString(keyboard, "Enter town: ");
        county = getString(keyboard, "Enter county: ");
        line3 = getString(keyboard, "Enter mobilenum: ");
        mobilenum = Integer.parseInt(line3);
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
        String line3;
        
        //this will get the information for the database and place it in i.e. o.getAddress1
        firstname = getString(keyboard, "Enter firstname: [" + o.getFirstName() + "]: ");
        lastname = getString(keyboard, "Enter lastname: [" + o.getLastName() + "]: ");
        address1 = getString(keyboard, "Enter address1: [" + o.getAddress1() + "]: ");
        address2 = getString(keyboard, "Enter address2: [" + o.getAddress2() + "]: ");
        town = getString(keyboard, "Enter town: [" + o.getTown() + "]: ");
        county = getString(keyboard, "Enter county: [" + o.getCounty() + "]: ");
        line3 = getString(keyboard, "Enter mobilenum: [" + o.getMobileNum() + "]: ");
        email = getString(keyboard, "Enter county: [" + o.getCounty() + "]: ");
    
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
        if (line3.length() != 0) {
            mobilenum = Integer.parseInt(line3);
            o.setMobileNum(mobilenum);
        }
        if (email.length() != 0) {
            o.setEmail(email);
        }
    }
    
    //the code below will return true or false whether the Owner has been updated or not.
    private static void editOwner(Scanner keyboard, Model model) {
        System.out.print("Enter the OwnerID of the owner you wish to edit: ");
        int ownerID = Integer.parseInt(keyboard.nextLine());
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
    private static void deleteOwner(Scanner keyboard, Model model) {
        System.out.print("Enter the OwnerID of the owner you wish to delete: ");
        int ownerID = Integer.parseInt(keyboard.nextLine());
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
