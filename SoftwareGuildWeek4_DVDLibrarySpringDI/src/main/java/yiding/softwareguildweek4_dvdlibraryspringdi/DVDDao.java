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
import java.util.HashMap;
import java.util.Set;
import java.util.Collection;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;


public class DVDDao {
    HashMap<String, DVD> dvdCollection = new HashMap<>();

    protected static int count;
    //1 add a DVD to the collection
    public void addDVD(DVD dvd){
        dvdCollection.put(dvd.getTitle(),dvd);
        System.out.println("DVD '" + dvd.getTitle() + "' add");
        count++;
    }
    //2 remove a DVD from the collection
    public void removeDVD(String title){
        boolean exist = false;
        for (String dvdTitle : new ArrayList<String>(dvdCollection.keySet())){
            if(dvdCollection.get(dvdTitle).getTitle().equals(title)){
                printDVDInfo(dvdTitle);
                exist = true;
                dvdCollection.remove(title);
                count--;
                System.out.println("DVD '" + title + "' delected");
                //dvdCollection.remove(title);
            }
        }
        if (exist == false){
            System.out.println("The DVD '" + title + "' does not exist in the collection");
        }
    }
    public void removeDVD2(String title){
        dvdCollection.remove(title);
        count--;
    }
    public DVD editDVD1(String title){
        
        boolean exist = false;
        for (String dvdTitle : dvdCollection.keySet()){
            if(dvdCollection.get(dvdTitle).getTitle().equals(title)){
                exist = true;
            }
        }
        if (exist == false){
            System.out.println("The DVD '" + title + "' does not exist in the collection");
            return null;
        } else return dvdCollection.get(title);
    }
    public void editDVD2(DVD dvd){
        dvdCollection.put(dvd.getTitle(),dvd);
        count++;
    }
    
    //4 list the DVDs in the collection
    public void getAllDVDs(){
        Set<String> titles = dvdCollection.keySet();
        System.out.println("List DVD(s) Menu:");
        if (dvdCollection.isEmpty()) {
            System.out.println("The DVD collection is empty");
        } else {
            for (String dvdTitle : titles){
                printDVDInfo(dvdTitle);
            }
        }
    }
    
    //5 search DVD by title
    public void getDVDByTitle(String title){
        boolean exist = false;
        for (String dvdTitle : dvdCollection.keySet()){
            if(dvdCollection.get(dvdTitle).getTitle().equals(title)){
                printDVDInfo(dvdTitle);
                exist = true;
            }
        }
        if (exist == false){
            System.out.println("The DVD '" + title + "' does not exist in the collection");
        }
        
    }
    
    /*public void listDVDCount(){
        System.out.println("List DVD Count Menu:");
        System.out.println("    There are " + count + " DVD(s) in the collection.");

    }*/
    public void LoadDVDfromFile(String fileName)throws Exception{
        
        BufferedReader in = new BufferedReader(new FileReader(fileName));
        String line = "";
        
        while ((line = in.readLine()) != null) {
            DVD dvd = new DVD();
            String parts[] = line.split("::");
            //LocalDate ld = ;
            dvd.setTitle(parts[0]);
            dvd.setReleaseDate(LocalDate.parse(parts[1]).format(DateTimeFormatter.ofPattern("MM/dd/yyyy")).toString());
            dvd.setmPAARating(parts[2]);
            dvd.setDirectorName(parts[3]);
            dvd.setStudio(parts[4]);
            dvd.setUserRating(parts[5]);
            dvdCollection.put(dvd.getTitle(), dvd);
            count ++;
        }
        in.close();
        
    }
    public void SaveDVDtoFile(String fileName)throws Exception{
        PrintWriter out = new PrintWriter(new FileWriter(fileName));

        for (String dvdTitle : dvdCollection.keySet()){
            out.println(dvdCollection.get(dvdTitle).getTitle()
            + "::" + dvdCollection.get(dvdTitle).getReleaseDate()
            + "::" + dvdCollection.get(dvdTitle).getmPAARating()
            + "::" + dvdCollection.get(dvdTitle).getDirectorName()
            + "::" + dvdCollection.get(dvdTitle).getStudio()
            + "::" + dvdCollection.get(dvdTitle).getUserRating());
        }
        out.flush();
        out.close();
    }
        
    public void printDVDInfo(String key){
        System.out.println("Title: " + dvdCollection.get(key).getTitle());
        System.out.println("Release date: " + dvdCollection.get(key).getReleaseDate());
        System.out.println("MPAA-rating: " + dvdCollection.get(key).getmPAARating());
        System.out.println("Director's Name: " + dvdCollection.get(key).getDirectorName());
        System.out.println("Studio: " + dvdCollection.get(key).getStudio());
        System.out.println("User rating: " + dvdCollection.get(key).getUserRating());
        System.out.println("");
    }
}
