package yiding.softwareguildweek5_flooringmastery;

import java.math.BigDecimal;
import java.math.RoundingMode;
import yiding.softwareguildweek5_flooringmastery.DTO.Order;
import yiding.softwareguildweek5_flooringmastery.DTO.PreOrder;

/**
 *
 * @author yidingweng
 */
public class FlooringMasteryView {

    private UserIOConsoleImpl io = new UserIOConsoleImpl();
    
    public FlooringMasteryView(UserIOConsoleImpl io){
        this.io = io;
    }
    
    public int printMenuAndSelection(){
        io.print("\n===================================================== \n");
        io.print("                <<Flooring Program>>\n");
        io.print("   Please select the operation you wish to perform:\n\n");
        io.print("       1. Display Orders\n");
        io.print("       2. Add an Order\n");
        io.print("       3. Edit an Order\n");
        io.print("       4. Remove an Order\n");
        io.print("       5. Save current work\n");
        io.print("       6. Quit\n");
        io.print("===================================================== \n");

       return io.readInt("Please select from the above choices: ", 1, 6);
    }

    public String requestDate() {
        String date = io.readString("Please enter the date in MM/dd/yyyy format: ");
        return date;
    }
    
    public int requestIndex() {
        int orderIndex = io.readInt("Please provide the order number: ");
        return orderIndex;
    }
    

    public void displayOrderInfo(Order currentOrder) {
        io.print("\nORDER NUMBER: " + currentOrder.getOrderIndex());
        io.print("CUSTOMER NAME: " + currentOrder.getName());
        io.print("STATE: " + currentOrder.getState());
        io.print("PRODUCT TYPE: " + currentOrder.getProductName());
        io.print("AREA: " + String.valueOf(currentOrder.getArea()));
        io.print("MATERICAL COST: " + String.valueOf(currentOrder.getMaterialCost()));
        io.print("LABOUR COST: " + String.valueOf(currentOrder.getLabourCost()));
        io.print("TOTAL TAX: " + String.valueOf(currentOrder.getTax()));
        io.print("TOTAL COST: " + String.valueOf(currentOrder.getTotal())+"\n");
        
    }

    public void displayInfoError() {
        io.print("There is an error");
    }

    public PreOrder requestAddInfo(String stateChoices, int stateNo, String productChoices, int productNo) {
        String name = io.readString("Please enter the customer name: ");
        int stateInt = io.readInt("Please choose a state (" + stateChoices + "): ",1,stateNo);
        int productNameInt = io.readInt("Please choose a product (" + productChoices + "): ",1,productNo);
        double areaDouble = io.readDouble("Please enter area: ");
        BigDecimal area = new BigDecimal(areaDouble).setScale(2, RoundingMode.HALF_UP);
        PreOrder currentOrder = new PreOrder(name,stateInt,productNameInt,area);
        /*displayInfo(currentOrder);
        if (toCommit() == true)
            return currentOrder;
        else
            return null;*/
        return currentOrder;
    }

    public void displayAddOrderBanner() {
        io.print("=== Add Order ===");    
    }
    
    public void displayDisplayOrderBanner() {
        io.print("===  Display Added Order ===");    
    }

    public void displayAddSuccessBanner() {
        io.print("===  Order Successfully Added ===");
    }

    public boolean toCommit() {
        int toCommit = io.readInt("Do you want to commit this order (1.Yes, 2.No)? ",1,2);
        if (toCommit == 1)
            return true;
        else
            return false;
    }

    public void displayRemoveOrderBanner() {
        io.print("===  Remove Order ===");
    }
    public boolean toRemove(){
        int toRemove = io.readInt("Do you want to remove this order (1.Yes, 2.No)? ",1,2);
        if (toRemove == 1)
            return true;
        else
            return false;
    }

    public void displayRemoveSuccessBanner() {
        io.print("===  Order Successfully Removed ===");
    }

    public void displayAllOrdersBanner() {
        io.print("===  Display All Orders ===");
    }

    public void displayAllOrders(String allOrdersInfo) {
        io.print(allOrdersInfo);
    }

    public void displayEditOrderBanner() {
        io.print("===  Edit Order ===");
    }

    public PreOrder displayEditOrder(Order toBeEditedOrder, String stateChoices, String productChoices, int stateNo, int productNo) {
        int orderIndex = toBeEditedOrder.getOrderIndex();
        io.print("The current customer name is: " + toBeEditedOrder.getName());
        String name = io.readSkippableString("Please enter new customer name(or hit enter to keep the current): ");
        if (name == null || name.trim().length() == 0) 
            name = toBeEditedOrder.getName();
        io.print("The current state choice is: " + toBeEditedOrder.getState());
        int stateInt = io.readInt("Please choose a state (" + stateChoices + "): ",1,stateNo);
        io.print("The current product choice is: " + toBeEditedOrder.getProductName());
        int productNameInt = io.readInt("Please choose a product (" + productChoices + "): ",1,productNo);
        io.print("The current area is: " + toBeEditedOrder.getArea());
        double areaDouble = io.readDouble("Please re-enter area value: ");
        BigDecimal area = new BigDecimal(areaDouble).setScale(2, RoundingMode.HALF_UP);
        PreOrder updatedOrder = new PreOrder(orderIndex,name,stateInt,productNameInt,area);
        //System.out.println("The new orderIndex should be: " + orderIndex);
        return updatedOrder;
    }

    public void displayEditSuccessBanner() {
        io.print("===  Order Successfully Edited ===");
    }

    public void displaySaveSuccessBanner() {
        io.print("===  Changes Successfully Saved ===");
    }

    public boolean toEdit() {
        int toRemove = io.readInt("Do you want to save this Change (1.Yes, 2.No)? ",1,2);
        if (toRemove == 1)
            return true;
        else
            return false;
    }

    public void displayDateTimeFormatError() {
        io.print("\nIncorrect date time format.\n");
    }

    public void displayFileDoesNotExistError() {
        io.print("\nFile does not exist.\n");
    }

    public void displayNoOrders() {
        io.print("\nThere is no orders on this date.\n");
    }

    public void displayOrderDoesNotExistInFile() {
        io.print("\nOrder does not exist in file.\n");
    }

    public void displayNullPointerError() {
        io.print("\nNo work to be saved.\n");
    }

    public void displaySaveDoesNotApply() {
        io.print("\nSaving does not apply under training mode.\n");
    }
    
}
