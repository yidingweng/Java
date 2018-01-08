/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yiding.softwareguildweek5_flooringmastery.DAO;

import java.util.HashMap;
import java.util.Set;
import java.util.Collection;
import yiding.softwareguildweek5_flooringmastery.DTO.State;
/**
 *
 * @author yidingweng
 */
public class StateSet {
    public HashMap<String, State> stateSet = new HashMap<>();
    //public Set<String> stateNameSet = stateSet.keySet();
    String stateString = "";
    //String [][] stateArray = new String[][];
    String[][] stateArray ;
    int stateNo;

    public StateSet() {
        this.stateSet = new HashMap<>();
    }
    public void addState(String stateName, State state){
        stateSet.put(stateName, state );
        //System.out.println(stateName + " " + state.getTax());
    }
    public String stateToString(){
        Set<String> stateNameSet = stateSet.keySet();
        stateNo = stateSet.size();
        int order = 0;
        for(String stateName : stateNameSet){
            order+=1;
            stateString += order + ". "+ stateName + ", ";
        }
        //System.out.println("in stateSet class: " + stateString + "print?");
        return stateString;      
    }
    public String[][] stateToArray(){
        Set<String> stateNameSet = stateSet.keySet();
        stateArray = new String[stateSet.size()+1][2];
        int order = 0;
        for(String stateName : stateNameSet){
            order++;
            stateArray[order][0] = String.valueOf(order);
            //System.out.println(stateArray[order][0]);
            stateArray[order][1] = stateName;
            //System.out.println(stateArray[order][1]);
        }
        return stateArray;
    }
    public String getStateString(){
        return stateString;
    }
    public String[][] getStateArray(){
        return stateArray;
    }
            
    public int getSize(){
        return stateNo;
    }

    public State getState(String stateName) {
        State state = stateSet.get(stateName);
        return state;
    }
}
