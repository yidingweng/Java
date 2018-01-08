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
import java.lang.Character;
import java.lang.String ;
import java.util.Scanner;

public class SummativeSums {

    //public static String arrays;
    public static String[] stringArrays = new String[3];
    public static int[][] integerArrays = new int[3][100000];
    public static int[] sumArrays = new int[3];

    public static void main(String[] args) {
        getUserInput();
        storeIntegerArray();
        printResult();
        //System.out.println(arrays);
        //splitIntoThree(arrays);
    }

    public static void getUserInput() {
        System.out.println("Enter the three integer arrays in separate lines: ");
        Scanner sc = new Scanner(System.in);
        for (int i = 0; i < 3; i++) {
            stringArrays[i] = sc.nextLine();
        }
    }

    public static void storeIntegerArray() {
        for (int i = 0; i < 3; i++) {
            //stringArrays[i] = stringArrays[i].substring(1, stringArrays[i].length()-1);
            stringArrays[i] = stringArrays[i].replace("{", "").replace("}", "").replace(" ", "");
            String[] stringArray = stringArrays[i].split(",");
            for (int j = 0; j < stringArray.length; j++) {
                integerArrays[i][j] = Integer.parseInt(stringArray[j]);
                sumArrays[i] += integerArrays[i][j];
                //System.out.println(integerArrays[i][j]);
            }
        }
    }

    public static void printResult() {
        for (int i = 0; i < 3; i++) {
            System.out.println("#" + (i + 1) + " Array Sum: " + sumArrays[i]);
        }
    }
}
