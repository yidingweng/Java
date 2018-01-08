/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yiding.softwareguildweek4_dvdlibraryspringdi;

/**
 *
 * @author yidingweng
 */

public class DVDCollectionController {
    
    public DVDCollectionController (DVDView view, DVDDaoDbImpl dvdCollection){
        this.view = view;
        this.dvdCollection = dvdCollection;
    }
    
    private DVDView view;
    private DVDDaoDbImpl dvdCollection;

    public void execute()  throws Exception{
        view.displayWelcome();

        boolean keepGoing = true;
        while (keepGoing == true) {
            String option = String.valueOf(view.displayMainMenu());
            switch (option) {
                case "1":
                    dvdCollection.addDVD(view.displayAddDVD());
                    option = view.continueAfter();
                    break;
                case "2":
                    dvdCollection.removeDVD(view.displayDeleteDVD());
                    option = view.continueAfter();
                    break;
                case "3":
                    /*dvdCollection.updateDVD(view.displayEditDVDDB());
                    option = view.continueAfter();
                    /*DVD oldDvd = dvdCollection.editDVD1(view.displayEditDVD1());
                    dvdCollection.removeDVD2(oldDvd.getTitle());
                    DVD newDvd = view.displayEditDVD2(oldDvd);
                    dvdCollection.editDVD2(newDvd);
                    System.out.println("The updated DVD info: \n");
                    dvdCollection.printDVDInfo(newDvd.getTitle());
                    option = view.continueAfter();*/
                    break;
                case "4":
                    dvdCollection.getAllDVDs();
                    option = view.continueAfter();
                    break;
                case "5":
                    dvdCollection.getDVDByTitle(view.displaySearchDVD());
                    option = view.continueAfter();
                    break;
                case "6":
                    //dvdCollection.LoadDVDfromFile(view.displayLoadFromFile());
                    //option = view.continueAfter();
                    break;
                case "7":
                    //dvdCollection.SaveDVDtoFile(view.displaySaveIntoFile());
                    //option = view.continueAfter();
                    break;
                case "E":
                    System.out.println("Exit System");
                    keepGoing = false;
                    break;
                default:
                    System.out.println("Unrecognied input.\n Please re-enter your choice.");
                    break;
                /*case 6:
                    addressBook.changeAddresses(view.displayChangeAddress(view.displayFindAddress())));
                    option = view.continueAfter();
                    break;*/
            }
        }
    }

}
