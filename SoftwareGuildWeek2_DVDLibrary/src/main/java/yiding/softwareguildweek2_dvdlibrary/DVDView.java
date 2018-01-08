/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yiding.softwareguildweek2_dvdlibrary;

/**
 *
 * @author yidingweng
 */
public class DVDView {
    
    public UserIOApp io = new UserIOApp();
    
    public void displayWelcome(){
        System.out.println("Welcome");
    }
    
    public String displayMainMenu(){
        String info = ("===================================================== \n" +
        "Initial Menu:\n" +
        "   Please select the operation you wish to perform:\n\n" +
        "       1. Add DVD\n" +
        "       2. Remove DVD\n" +
        "       3. Edit DVD info\n" +
        "       4. List All DVD(s)\n" +
        "       5. Search DVD by title\n" + 
        "       6. Load DVD info from file\n" +
        "       7. Save DVD info to file\n\n" +        
        "       or press E to exit the system\n" +
        "===================================================== \n");
        System.out.println(info);
        String input = io.readString();
        return input;
    }
    //1
    public DVD displayAddDVD(){
        DVD dvd = new DVD();
        System.out.println("Add DVD Menu:");
        
        System.out.println("   Please Enter DVD Title:");
        dvd.setTitle(io.readString());
        
        System.out.println("   Please Enter Release Date(MM/DD/YYYY):");
        dvd.setReleaseDate(io.readString());
        
        System.out.println("   Please Enter MPAA rating:");
        dvd.setmPAARating(io.readString());
        
        System.out.println("   Please Enter Director's Name:");
        dvd.setDirectorName(io.readString());
        
        System.out.println("   Please Enter Studio:");
        dvd.setStudio(io.readString());
        
        System.out.println("   Please Enter User Rating:");
        dvd.setUserRating(io.readString());
        return dvd;
    }
    
    //2
    public String displayDeleteDVD(){
        System.out.println("Remove DVD Menu");
        System.out.println("   Please enter the title of DVD to delete:");
        String key = io.readString();
        return key;
    }
    
    public String displaySearchDVD(){
        System.out.println("Search DVD Menu");
        System.out.println("   Please enter the title of DVD:");
        String key = io.readString();
        return key;
    }
    public String displayEditDVD1(){
        System.out.println("Edit DVD Menu");
        System.out.println("   Please enter the title of DVD to edit:");
        String key = io.readString();
        return key;
    }
    public DVD displayEditDVD2(DVD dvd){
        System.out.println("Existing Title is " + dvd.getTitle() + 
                ". Do you want to change?(1 for Yes, 2 for No)");
        int choice1 = io.readInt();
        if (choice1 == 1){
            System.out.println("Enter new Title:\n");
            dvd.setTitle(io.readString());
        }
            
        System.out.println("Existing Release Date is " + dvd.getReleaseDate() + 
                ". Do you want to change?(1 for Yes, 2 for No)");
        int choice2 = io.readInt();
        if (choice2 == 1){
            System.out.println("Enter new Release Date:\n");
            dvd.setReleaseDate(io.readString());
        }
        
        System.out.println("Existing MPAA-Rating is " + dvd.getmPAARating() + 
                ". Do you want to change?(1 for Yes, 2 for No)");
        int choice3 = io.readInt();
        if (choice3 == 1){
            System.out.println("Enter new MPAA-Rating:\n");
            dvd.setmPAARating(io.readString());
        }
        
        System.out.println("Existing Director's Name is " + dvd.getDirectorName() + 
                ". Do you want to change?(1 for Yes, 2 for No)");
        int choice4 = io.readInt();
        if (choice4 == 1){
            System.out.println("Enter new Director's Name:\n");
            dvd.setDirectorName(io.readString());
        }
        
        System.out.println("Existing Studio is " + dvd.getStudio() + 
                ". Do you want to change?(1 for Yes, 2 for No)");
        int choice5 = io.readInt();
        if (choice5 == 1){
            System.out.println("Enter new Studio:\n");
            dvd.setStudio(io.readString());
        }
        
        System.out.println("Existing User Rating is " + dvd.getUserRating() + 
                ". Do you want to change?(1 for Yes, 2 for No)");
        int choice6 = io.readInt();
        if (choice6 == 1){
            System.out.println("Enter new User Rating:\n");
            dvd.setUserRating(io.readString());
        }
        return dvd;
    }
    public String displayLoadFromFile(){
        System.out.println("Load DVD from Existing File Menu");
        System.out.println("   Please enter the Name the File:");
        String fileName = io.readString();
        return fileName;
    }
    public String displaySaveIntoFile(){
        System.out.println("Save DVD to Existing File Menu");
        System.out.println("   Please enter the Name the File:");
        String fileName = io.readString();
        return fileName;
    }
    
    public String continueAfter(){
        System.out.println("   Press anykey to go to Main Menu");
        System.out.println("");
        String option = io.readString();
        return option;
    }
}

