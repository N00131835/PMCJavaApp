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
        
        int option; // initialising the variable option
        
        //a Do-While loop is used so that we can keep asking the user what they need to do, and it will stop looping when the user press the exit option, which in this case it's 5
        do {
            //this is the main menu
            System.out.println("-------------------------");
            System.out.println("1. Create new Property");
            System.out.println("2. View all Property");
            System.out.println("3. Edit existing Property");
            System.out.println("4. Delete Property");
            System.out.println("5. Exit");
            System.out.println("-------------------------");
            System.out.println();
            
            //This is where the user will type the option they want to pick
            System.out.print("Enter an option: ");
            String line = keyboard.nextLine();
            option = Integer.parseInt(line);
            
            //@author n00131835
            //System.out.println("You chose option " + option);
            switch (option) {
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
            }
        }
        while (option != 5);
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
}
