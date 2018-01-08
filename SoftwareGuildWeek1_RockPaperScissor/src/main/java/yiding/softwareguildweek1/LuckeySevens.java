/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yiding.softwareguildweek1;

/**
 *
 * @author yidingweng
 */
import java.util.Scanner;
import java.util.Random;

public class LuckeySevens {
    public static int money;
    public static int max = 0;
    public static boolean haveMoney;
    public static int times = 0;
    
    public static void main(String[] args) {
        money = getMoney();
        haveMoney = !isBroke(money);
        while (haveMoney == true){
            rollTheDies();
            times ++;
        }
        System.out.println("You played " + times + " time(s).");
        System.out.println("The max amount you had was " + max + "dollar(s).");
        
    }
    public static int getMoney(){
        System.out.println("How much money do you have ? ");
        Scanner sc = new Scanner(System.in);
        money = sc.nextInt();
        return money;
    }
    public static int rollTheDies(){
        Random randomizer = new Random();
        int die1 = randomizer.nextInt(6) + 1;
        int die2 = randomizer.nextInt(6) + 1;
        int sum = die1 + die2;
        if(sum == 7) {
            if (isMax(money + 4) == true){
                max = money + 4;
            }
            money += 4;
            return money;
        }
        else
            money -= 1;
            if (isBroke(money) == true){
                haveMoney = false;
            }
            return money;
    }
    public static boolean isMax(int money){
        if (money > max)
            return true;
        else
            return false;
    }
    
    public static boolean isBroke(int money){
        if (money > 0)
            return false;
        else
            return true;
    }
    
}

