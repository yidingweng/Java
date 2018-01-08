package yiding.softwareguildweek4_vendingmachinespring;


import java.math.BigDecimal;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author yidingweng
 */
public class InventoryController {
    
    
    InventoryService service;
    InventoryView view;
    //private UserIO io = new UserIOConsoleImpl();
    
    public InventoryController(InventoryService service, InventoryView view) {
        this.service = service;
        this.view = view;
    }

    InventoryController() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public void execute(){
        try{view.displayInventory(service.requestInventoryInfo());
        } catch (Exception ex){
            view.displayGeneralError();
        }
        double money = view.displayCashRequest();
        String snackName = view.displayChoiceRequest();
        try {
            service.authenticatePurchase(snackName, money);
        } catch (InventoryService.InsufficientFundsException ex) {
            view.displayInsufficientFundsError();
        } catch (InventoryService.NoItemInventoryException ex) {
            view.displayNoItemInventoryError();
        } catch (Exception ex) {
            view.displayGeneralError();
        }
        
        view.displayCashReturn(service.quarters,service.dimes,service.nickels,service.pennies);
    }
}
