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

public class HealthyHearts {
    static int age;
    static int maxHeartRate;
    static int lowerRateZone;
    static int upperRateZone;
    
    public static void main(String[] args) {
        askAge();
        calcMaxRate(age);
        calcHeartRateZone(maxHeartRate);
        printResult();
    }
    public static void askAge() {
        System.out.print("What is your age? ");
        Scanner sc = new Scanner(System.in);
        age = sc.nextInt();
        
    }
    public static void calcMaxRate(int age) {
        maxHeartRate = 220 - age; 
    }
    public static void calcHeartRateZone(int maxHeartRate) {
        lowerRateZone = (int)(maxHeartRate*0.5);
        upperRateZone = (int)(maxHeartRate*0.85);
    }
    public static void printResult() {
        System.out.println("Your maximum heart rate should be "+ maxHeartRate +" beats per minute\n" +
        "Your target HR Zone is " + lowerRateZone+" - " + upperRateZone + " beats per minute");
    }
    
}
