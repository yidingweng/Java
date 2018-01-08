package com.mycompany.mavenproject1;


import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Set;

/**
 *
 * @author yidingweng
 */
public class InventoryView {
    private UserIO io = new UserIO();
    protected BigDecimal quarters;
    protected BigDecimal dimes;
    protected BigDecimal nickels;
    protected BigDecimal pennies;
    
    public double displayCashRequest(){
        System.out.println("Please enter the amount of cash: ");
        double cash = io.readDouble();
        return cash;
    }
    
    public String displayChoiceRequest(){
        System.out.println("Please enter the name of the snack you want: ");
        String userChoice = io.readString();
        return userChoice;
    }
    public void displayInventory(HashMap<String, Snacks> vendingMachine2){
        Set<String> snackNames = vendingMachine2.keySet();
        System.out.println("List Inventory Menu:");
        for (String name : snackNames){
            if (vendingMachine2.get(name).getAmount()>0){
                System.out.println("Index: " + vendingMachine2.get(name).getIndex());
                System.out.println("Snack Name: " + vendingMachine2.get(name).getName());
                System.out.println("Price: " + vendingMachine2.get(name).getPrice() + " $");
                System.out.println("");
            }
        }
    }
    public void loadManu(HashMap<String, Snacks> vendingMachine2){
    }
    
    public void displayCashReturn(BigDecimal quarters,BigDecimal dimes,BigDecimal nickels,BigDecimal pennies){
        System.out.println("Here's your change: ");
        System.out.println(quarters + " quarters.");
        System.out.println(dimes + " dimes.");
        System.out.println(nickels + " nickels.");
        System.out.println(pennies + " pennies.");
    }
}
