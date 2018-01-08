package yiding.softwareguildweek4_vendingmachinespring;

/**
 *
 * @author yidingweng
 */
public class Snacks {
    private String name;
    private int amount;
    private double price;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    
    public String toString(){
        return "Snack. "+ name + " |Quantity" + amount + " " + " |Price: " + price;
    }
    
}
