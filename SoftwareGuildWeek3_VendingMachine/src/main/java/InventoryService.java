
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
    
    
    public void authenticatePurchase(String snackName, double money) throws NoItemInventoryException,InsufficientFundsException, Exception {
        
        try {
            dao.isSoldOut(snackName);
            double snackPrice = dao.getSnackPrice(snackName);
            dao.changeInventory(snackName);
            dao.sufficientFunds(money,snackPrice);
            double change = money - snackPrice;
            returnCash(snackName, change);
        } catch (NoItemInventoryException e){
            //System.out.println(snackName + " does not exist in inventory.");
            throw e;
        } catch (InsufficientFundsException ex){
            /*System.out.println("Insufficient amount of cash.");
            double snackPrice = dao.getSnackPrice(snackName);
            System.out.println(snackName + " is " + snackPrice + " $.");
            System.out.println("You only have " + money + " $.");*/
            throw ex;
        }
    }
    
    public void returnCash(String purchasedItem, double change){
        change = (int)Math.round(change*100);
        quarters = new BigDecimal((change-change%25)/25);
        quarters = quarters.setScale(0,RoundingMode.HALF_UP);
        
        change -= quarters.doubleValue()*25;
        dimes = new BigDecimal((change-change%10)/10);
        dimes = dimes.setScale(0,RoundingMode.HALF_UP);
        
        change -= dimes.doubleValue()*10;
        nickels = new BigDecimal((change-change%5)/5);
        nickels = nickels.setScale(0,RoundingMode.HALF_UP);
        
        change -= nickels.doubleValue()*5;
        pennies = new BigDecimal(change);
        pennies = pennies.setScale(0,RoundingMode.HALF_UP);
        //new BigDecimal(5);
           
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
