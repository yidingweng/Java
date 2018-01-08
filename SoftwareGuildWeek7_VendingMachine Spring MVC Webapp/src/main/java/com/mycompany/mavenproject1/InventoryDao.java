package com.mycompany.mavenproject1;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author yidingweng
 */
public class InventoryDao implements InventoryDaoInterface {
    HashMap<String, Snacks> vendingMachine = new HashMap<>();
    
    @Override
    public boolean isSoldOut(String itemToPurchase) throws InventoryService.NoItemInventoryException{
        for (String snackName : new ArrayList<String>(vendingMachine.keySet())){
            if(vendingMachine.get(snackName).getName().equals(itemToPurchase) && vendingMachine.get(snackName).getAmount()>0){
                return false;
            }
        }
        throw new InventoryService.NoItemInventoryException(); 
    }
    @Override
    public boolean sufficientFunds(double money,double snackPrice) throws InventoryService.InsufficientFundsException{
        if (money >= snackPrice){
            return true;
        } else {
            throw new InventoryService.InsufficientFundsException();
        }
    }
    
    @Override
    public double getSnackPrice(String itemToPurchase){
        
        double snackPrice = vendingMachine.get(itemToPurchase).getPrice();
        return snackPrice;
    }
    
    public void changeInventory(String purchasedItem){
        int currentAmount = vendingMachine.get(purchasedItem).getAmount();
        int updatedAmount = currentAmount - 1;
        vendingMachine.get(purchasedItem).setAmount(updatedAmount);
        try {
            saveInventorytoFile();
        } catch (Exception ex) {
            Logger.getLogger(InventoryDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public HashMap<String, Snacks> getInventory()throws Exception{
        vendingMachine = LoadInventoryfromFile();
        return vendingMachine;
    }
    public HashMap<String, Snacks> LoadInventoryfromFile()throws Exception{
        //BufferedReader in = new BufferedReader(new FileReader(getClass().getClassLoader().getResource("vendingMachine.txt").getFile()));
        BufferedReader in = new BufferedReader(new FileReader("/Users/yidingweng/Documents/Yiding_SoftwareGuild2017/yiding_weng_individual_work/SoftwareGuildWeek7_VendingMachineSpringMVC/src/main/resources/vendingMachine.txt"));
        String line = "";
        //System.out.println("hello dao 2");
        
        while ((line = in.readLine()) != null) {
            Snacks snack = new Snacks();
            //System.out.println("hello dao loop");
            String[] parts = line.split("::");
            snack.setIndex(Integer.parseInt(parts[0]));
            snack.setName(parts[1]);
            snack.setAmount(Integer.parseInt(parts[2]));
            snack.setPrice(Double.parseDouble(parts[3]));
            vendingMachine.put(snack.getName(), snack);
        }
        in.close();
        return vendingMachine;
    }
    
    protected HashMap<String, Snacks> LoadInventoryfromFileTest()throws Exception{
        
        BufferedReader in = new BufferedReader(new FileReader("/Users/yidingweng/Documents/Yiding_SoftwareGuild2017/yiding_weng_individual_work/SoftwareGuildWeek7_VendingMachineSpringMVC/src/main/resources/vendingMachine.txt"));
        String line = "";
        
        while ((line = in.readLine()) != null) {
            Snacks snack = new Snacks();
            String[] parts = line.split("::");
            snack.setIndex(Integer.parseInt(parts[0]));
            snack.setName(parts[1]);
            snack.setAmount(Integer.parseInt(parts[2]));
            snack.setPrice(Double.parseDouble(parts[3]));
            vendingMachine.put(snack.getName(), snack);
        }
        in.close();
        return vendingMachine;
    }
    
    private void saveInventorytoFile()throws Exception{
        PrintWriter out = new PrintWriter(new FileWriter("/Users/yidingweng/Documents/Yiding_SoftwareGuild2017/yiding_weng_individual_work/SoftwareGuildWeek7_VendingMachineSpringMVC/src/main/resources/vendingMachine.txt"));

        for (String snackName : vendingMachine.keySet()){
            out.println(vendingMachine.get(snackName).getIndex()
            + "::" + vendingMachine.get(snackName).getName()
            + "::" + vendingMachine.get(snackName).getAmount()
            + "::" + vendingMachine.get(snackName).getPrice());
        }
        out.flush();
        out.close();
    }

    @Override
    public void listInventory() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

   
}
