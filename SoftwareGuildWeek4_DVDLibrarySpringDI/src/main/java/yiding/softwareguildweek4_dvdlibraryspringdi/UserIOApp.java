/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yiding.softwareguildweek4_dvdlibraryspringdi;

/**
 *
 * @author yidingweng
 */
import java.util.Scanner;
public class UserIOApp {
    Scanner sc = new Scanner(System.in);
    /*public void print(String message){;}*/

    /*public double readDouble(String prompt){
        double input = Double.parseDouble(sc.nextLine());
        return input;
    }

    public double readDouble(String prompt, double min, double max){
        return 2;
    }

    public float readFloat(String prompt){
        return 2;
    }

    public float readFloat(String prompt, float min, float max){
        return 2;
    }*/
    public int readInt(){
        int input = Integer.parseInt(sc.nextLine());
        return input;
    }
    
    public int readInt(String prompt){
        int input = Integer.parseInt(sc.nextLine());
        return input;
    }
    public String readString(){
        String input = sc.nextLine();
        return input;
    }

    /*public int readInt(String prompt, int min, int max){
        return 2;
    }

    public long readLong(String prompt){
        return 2;
    }*/
    
}
