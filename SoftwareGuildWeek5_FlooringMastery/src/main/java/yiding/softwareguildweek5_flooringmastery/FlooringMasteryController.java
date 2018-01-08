/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yiding.softwareguildweek5_flooringmastery;

import java.io.IOException;
import yiding.softwareguildweek5_flooringmastery.DTO.Order;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;
import yiding.softwareguildweek5_flooringmastery.DTO.PreOrder;

/**
 *
 * @author yidingweng
 */
public class FlooringMasteryController {
    
    FlooringMasteryService service;
    FlooringMasteryView view;
    int mode;
    
    String orderFileName;
    
    public FlooringMasteryController(FlooringMasteryService service,FlooringMasteryView view, int mode){
        this.service = service;
        this.view = view;
        this.mode = mode;
    }
    
    public void execute() {
        if (mode == 1){
            boolean keepGoing = true;
            int menuSelection = 0;
        
            while (keepGoing) {

                menuSelection = getMenuSelection();

                switch(menuSelection){
                    case 1: //display
                        displayAllOrders();
                        break;

                    case 2://add
                        boolean isEmpty = checkDaoIsEmpty();
                        if(isEmpty == true){
                            preAddOrder();
                            saveWork();
                        }else{
                            if (correctFileName() == true){
                                try {
                                    service.prepareAdd1("Taxes.txt","Products.txt");
                                    addOrder();
                                    saveWork();
                                } catch (IOException ex) {
                                    view.displayInfoError();
                                }
                            } else {
                                preAddOrder();
                                addOrder();
                                saveWork();
                            }
                        }
                        break;

                    case 3://edit
                        editOrder();
                        saveWork();
                        break;

                    case 4://remove
                        removeOrder();
                        saveWork();
                        break;

                    case 5://saveWork
                        saveWork();
                        break;
                    case 6:
                        keepGoing = false;
                }
            }
        } else {
            boolean keepGoing = true;
            int menuSelection = 0;
        
            while (keepGoing) {

                menuSelection = getMenuSelection();

                switch(menuSelection){
                    case 1: //display
                        displayAllOrders();
                        break;

                    case 2://add
                        boolean isEmpty = checkDaoIsEmpty();
                        if(isEmpty == true){
                            System.out.println("Dao is empty");
                            preAddOrder();
                            addOrder();
                        }else{
                            if (correctFileName() == true){
                                try {
                                    service.prepareAdd1("Taxes.txt","Products.txt");
                                    addOrder();
                                } catch (IOException ex) {
                                    view.displayInfoError();
                                }
                            } else {
                                preAddOrder();
                                addOrder();
                            }
                        }
                        break;

                    case 3://edit
                        editOrder();

                        break;

                    case 4://remove
                        removeOrder();
                        break;

                    case 5://saveWork
                        //saveWork();
                        view.displaySaveDoesNotApply();
                        break;
                    case 6:
                        keepGoing = false;
                }
            }
        }
    }
    
    private int getMenuSelection() {
        int choice = view.printMenuAndSelection();
        return choice;
    }
    private void displayAllOrders(){
        view.displayAllOrdersBanner();
        orderFileName = validateDateTime();
        String allOrdersInfo = null;
        try {
            allOrdersInfo = service.authenticateDisplayAllOrders(orderFileName);
            //System.out.println("display all orders: ");
            if (allOrdersInfo != null){
                view.displayAllOrders(allOrdersInfo);
            } 
        } catch (IOException e){
            view.displayFileDoesNotExistError();
        }
    }

    private Order displayOrder() throws IOException{
        
        orderFileName = validateDateTime();
        int index = view.requestIndex();
        Order currentOrder = service.authenticateDisplayOrder(orderFileName,index);
        view.displayOrderInfo(currentOrder);
        
        return currentOrder;
    }
    private String validateDateTime(){
        String date = view.requestDate();
        try{
            LocalDate ld = LocalDate.parse(date, DateTimeFormatter.ofPattern("MM/dd/yyyy"));
            String formatted = ld.format(DateTimeFormatter.ofPattern("MMddYYYY"));
            orderFileName = "Orders_"+formatted+".txt";
        } catch(Exception e){
            view.displayDateTimeFormatError();
            validateDateTime();
        }
        return orderFileName;
    }
    
    private boolean checkDaoIsEmpty() {
        boolean isEmpty = service.checkDaoIsEmptyOrNot();
        return isEmpty;
    }
    private void preAddOrder(){
        //view.displayAddOrderBanner();
        LocalDate ld = LocalDate.now();
        String formatted = ld.format(DateTimeFormatter.ofPattern("MMddYYYY"));
        orderFileName = "Orders_"+formatted+".txt";
        try {
            service.prepareAdd1("Taxes.txt","Products.txt");
        } catch (IOException ex) {
            view.displayInfoError();
        }
        try {
            service.prepareAdd2(orderFileName);
        } catch (IOException ex) {
            view.displayInfoError();
        }
    }
    private void addOrder(){
        view.displayAddOrderBanner();
        String stateChoices = service.getStateStringFromStateSet();
        int stateNo = service.getStateNoFromStateSet();
        //System.out.println("in controller, addOrder: "+ stateString);///////////
        
        String productChoices = service.getProductStringFromProductSet();
        int productNo = service.getProductNoFromProductSet();
        //System.out.println("in controller, addOrder: "+ productString);///////////
        
        PreOrder currentOrder = view.requestAddInfo(stateChoices, stateNo, productChoices, productNo);
        Order fullOrder = service.getFullOrderInfo(currentOrder);
        
        view.displayDisplayOrderBanner();
        view.displayOrderInfo(fullOrder);
        
        boolean toCommit = view.toCommit();
        if (toCommit == true){
            view.displayAddSuccessBanner();
            service.authenticateAddOrder(fullOrder);
            
        }
    }
    
    private void editOrder(){
        view.displayEditOrderBanner();
        
        try{
            Order toBeEditedOrder = displayOrder();
            int orderIndex = toBeEditedOrder.getOrderIndex();
            try {
                //service.authenticateRemoveOrder(toBeEditedOrder);
                service.prepareAdd1("Taxes.txt","Products.txt");
            } catch (IOException ex) {
                view.displayInfoError();
            }
            String stateChoices = service.getStateStringFromStateSet();
            String productChoices = service.getProductStringFromProductSet();
            int stateNo = service.getStateNoFromStateSet();
            int productNo = service.getProductNoFromProductSet();

            PreOrder updatedOrder = view.displayEditOrder(toBeEditedOrder,stateChoices,productChoices,stateNo,productNo);
            Order fullOrder = service.getFullOrderInfo(updatedOrder);
            fullOrder.setOrderIndex(orderIndex);
            view.displayOrderInfo(fullOrder);

            boolean toEdit = view.toEdit();
            if (toEdit == true){
                view.displayEditSuccessBanner();
                service.authenticateEditOrder(fullOrder);
            }
        } catch (IOException e){
            view.displayFileDoesNotExistError();
        } catch (NullPointerException ex){
            view.displayOrderDoesNotExistInFile();
        }
    } 
    

    private void removeOrder(){
        view.displayRemoveOrderBanner();
        try {Order toBeRemovedOrder = displayOrder();
        
            boolean toRemove = view.toRemove();
            if (toRemove == true){
                view.displayRemoveSuccessBanner();
                service.authenticateRemoveOrder(toBeRemovedOrder);
            }
        } catch (IOException e){
            view.displayFileDoesNotExistError();
        }catch (NullPointerException ex){
            view.displayOrderDoesNotExistInFile();
        }
    }
    
    private void saveWork() {
        try {
            service.saveOrderDaoToFile(orderFileName);
            view.displaySaveSuccessBanner();
        } catch (IOException ex) {
            view.displayInfoError();
        } catch (NullPointerException e){
            view.displayNullPointerError();
        }
    }

    private boolean correctFileName() {
        boolean isCorrectFileName = false;
        LocalDate ld = LocalDate.now();
        String formatted = ld.format(DateTimeFormatter.ofPattern("MMddYYYY"));
        orderFileName = "Orders_"+formatted+".txt";
        System.out.println("ideal fileName is: " + orderFileName);
        if (orderFileName.equals(service.getCurrentFileName())){
            System.out.println("real fileName is: " + service.getCurrentFileName());
            isCorrectFileName = true;
        }
        return isCorrectFileName;
    }

}
