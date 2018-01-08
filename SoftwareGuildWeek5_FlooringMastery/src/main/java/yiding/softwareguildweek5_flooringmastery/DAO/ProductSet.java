/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yiding.softwareguildweek5_flooringmastery.DAO;

import java.util.HashMap;
import java.util.Set;
import yiding.softwareguildweek5_flooringmastery.DTO.Product;
import yiding.softwareguildweek5_flooringmastery.DTO.State;

/**
 *
 * @author yidingweng
 */
public class ProductSet {
    
    public HashMap<String, Product> productSet = new HashMap<>();
    
    String productString = "";
    String[][] productArray ;
    int productNo;
    
    public ProductSet() {
        this.productSet = new HashMap<>();
    }

    public void addProduct(String productName, Product product) {
        productSet.put(productName, product );
        //System.out.println(productName + " " + product.getCostPerSquareFoot() + " " + product.getLabourCostPerSquareFoot());
    }
    public String productToString(){
        Set<String> productNameSet = productSet.keySet();
        productNo = productSet.size();
        int order = 0;
        //String productString = "";
        for(String productName : productNameSet){
            order+=1;
            productString += order + ". "+ productName + ", ";
        }
        //System.out.println("in productSet class: " + productString + "print?");
        return productString;      
    }
    public String getProductString(){
        return productString;
    }

    public int getSize() {
        return productNo;
    }
    public String[][] productToArray(){
        Set<String> productNameSet = productSet.keySet();
        productArray = new String[productSet.size()+1][2];
        int order = 0;
        for(String productName : productNameSet){
            order++;
            productArray[order][0] = String.valueOf(order);
            //System.out.println(stateArray[order][0]);
            productArray[order][1] = productName;
            //System.out.println(stateArray[order][1]);
        }
        return productArray;
    }
    public String[][] getProductArray(){
        return productArray;
    }
    
    public Product getProduct(String productName) {
        Product product = productSet.get(productName);
        return product;
    }
}
