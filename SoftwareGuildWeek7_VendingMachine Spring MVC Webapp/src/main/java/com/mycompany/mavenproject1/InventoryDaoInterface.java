package com.mycompany.mavenproject1;


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
public interface InventoryDaoInterface {

    HashMap<String, Snacks> getInventory() throws Exception;

    double getSnackPrice(String itemToPurchase);

    boolean isSoldOut(String itemToPurchase) throws InventoryService.NoItemInventoryException;

    void listInventory() throws Exception;

    boolean sufficientFunds(double money, double snackPrice) throws InventoryService.InsufficientFundsException;
    
}
