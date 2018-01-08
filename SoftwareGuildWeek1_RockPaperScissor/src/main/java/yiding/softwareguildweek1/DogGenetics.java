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
import java.util.Random;
import java.util.Scanner;

public class DogGenetics {
    static int[] percentages = new int[5];
    static String name;
    static int percentagesLeft = 100;
    //static int percent1;
    public static void main(String[] args) {
        for(int i = 0; i<5; i++){
            percentages[i]=0;
        }
        askUserInput();
        printIntro();
        getPercentage();
        printReport();
    }
    public static void askUserInput() {
        System.out.print("What is your dog's name? ");
        Scanner sc = new Scanner(System.in);
        name = sc.nextLine();
        
    }
    public static void printIntro() {
        System.out.println("Well then, I have this highly reliable report on "
                + name + "'s prestigious background right here. \n");
    }
    public static void getPercentage() {
        Random rGen = new Random();
        for(int i = 0;i<4;i++){
            percentages[i] = rGen.nextInt(percentagesLeft)+1;
            percentagesLeft = percentagesLeft- percentages[i];
            if (percentagesLeft <= 0){
                printReport();
                break;
            } 
        }
        percentages[4]=percentagesLeft;
    }
    
    public static void printReport() {
        System.out.println(name + " is:\n\n " +
                percentages[0] + "% St. Bernard\n " + 
                percentages[1] + "% Chihuahua\n " + 
                percentages[2] + "% Dramatic RedBosed Asian Pug\n " + 
                percentages[3] + "% Common Cur\n " + 
                percentages[4] + "% King Doberman\n\n " +
                "Wow, that's QUITE the dog!");
    }
}

