package com.mycompany.mavenproject1;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;

/**
 *
 * @author yidingweng
     */
public class InventoryService {
    BigDecimalMath math = new BigDecimalMath();
    InventoryDao dao = new InventoryDao();
    protected BigDecimal quarters;
    protected BigDecimal dimes;
    protected BigDecimal nickels;
    protected BigDecimal pennies;
    protected BigDecimal dollars;
    protected String changeString;
    protected String message;
    
    protected BigDecimal getQuarters() {
        return quarters;
    }

    protected BigDecimal getDimes() {
        return dimes;
    }

    protected BigDecimal getNickels() {
        return nickels;
    }

    protected BigDecimal getPennies() {
        return pennies;
    }
    
    public HashMap<String, Snacks> requestInventoryInfo() throws Exception{
        return dao.LoadInventoryfromFile();
    }
    
    
    public void authenticatePurchase(String snackName, double money){
        
        try {
            dao.isSoldOut(snackName);
            double snackPrice = dao.getSnackPrice(snackName);
            dao.sufficientFunds(money,snackPrice);
            dao.changeInventory(snackName);
            message = "Thank You!!!";
            double change = money - snackPrice;
            returnChange(change);
        } catch (NoItemInventoryException e){
            message = "Sold out!";
            changeString = "";
        } catch (InsufficientFundsException ex){
            double snackPrice = dao.getSnackPrice(snackName);
            message = "Please Deposit "+ returnDeposit(snackPrice - money);
        }
    }
    public String getMessage(){
        return message;
    }
    
    public String returnDeposit(double deposit){
        calculateCoins(deposit);
        return dollars + " dollars " +
                quarters + " quarters " + 
                dimes + " dimes " +
                nickels + " nickels ";
    }
    
    public String returnChange(double change){
        calculateCoins(change);
        changeString = dollars + " dollars " +
               quarters + " quarters " + 
               dimes + " dimes " +
               nickels + " nickels ";
        return changeString;
    }
    
    public String getChangeString(){
        return changeString;
    }
    
    public void calculateCoins(double money){
        money = (int)Math.round(money*100);
        dollars = new BigDecimal((money-money%100)/100);
        dollars = dollars.setScale(0,RoundingMode.HALF_UP);
        
        money -= dollars.doubleValue()*100;
        quarters = new BigDecimal((money-money%25)/25);
        quarters = quarters.setScale(0,RoundingMode.HALF_UP);
        
        money -= quarters.doubleValue()*25;
        dimes = new BigDecimal((money-money%10)/10);
        dimes = dimes.setScale(0,RoundingMode.HALF_UP);
        
        money -= dimes.doubleValue()*10;
        nickels = new BigDecimal((money-money%5)/5);
        nickels = nickels.setScale(0,RoundingMode.HALF_UP);
           
    }

    public static class NoItemInventoryException extends Exception {

        public NoItemInventoryException() {
        }
    }

    public static class InsufficientFundsException extends Exception {

        public InsufficientFundsException() {
        }
    }
}
