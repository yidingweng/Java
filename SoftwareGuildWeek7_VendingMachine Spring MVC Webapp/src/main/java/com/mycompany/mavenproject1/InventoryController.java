package com.mycompany.mavenproject1;


import java.math.BigDecimal;
import java.util.HashMap;

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
    InventoryService service = new InventoryService();
    InventoryView view = new InventoryView();
    
    public void execute() throws Exception{
        view.displayInventory(service.requestInventoryInfo());
        double money = view.displayCashRequest();
        String snackName = view.displayChoiceRequest();
        service.authenticatePurchase(snackName, money);
        view.displayCashReturn(service.quarters,service.dimes,service.nickels,service.pennies);
    }
}
