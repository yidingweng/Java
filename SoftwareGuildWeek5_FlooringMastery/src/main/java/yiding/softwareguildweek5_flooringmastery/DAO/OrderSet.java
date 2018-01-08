/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yiding.softwareguildweek5_flooringmastery.DAO;

import java.util.HashMap;
import java.util.Set;
import yiding.softwareguildweek5_flooringmastery.AuditDao.FlooringMasteryAuditDaoFileImpl;
import yiding.softwareguildweek5_flooringmastery.DTO.Order;
//import yiding.softwareguildweek5_flooringmastery.FlooringMasteryPersistenceException;

/**
 *
 * @author yidingweng
 */
public class OrderSet {
    //FlooringMasteryAuditDaoFileImpl auditDao = new FlooringMasteryAuditDaoFileImpl();
    
    public HashMap<String,OrderSet> fileSet = new HashMap<>();
    
    public HashMap<Integer,Order> orderSet;
    
    public int orderIndex = 0;
    
    public OrderSet() {
        this.orderSet = new HashMap<>();
    }
    
    public void addFile(String date, OrderSet orderSet){
        
        fileSet.put(date, orderSet);
        //System.out.println("New file for this day created in fileSet: " + date);
    }

    public void addOrder(int orderNumber, Order currentOrder) {
         orderSet.put(orderNumber, currentOrder );
         //System.out.println("Order Created in OrderSet: " + orderNumber);
         //System.out.println("there are " + orderSet.size() + " orders");
    }

    public Order getOrder(int orderIndex) {
        Order order = orderSet.get(orderIndex);
        return order;
    }

    public int getOrderIndex() {
        //int index;
        int max = 0;
        Set<Integer> orderIndexSet = orderSet.keySet();
        for(int index : orderIndexSet){
            if (index > max){
                max = index;
            }
        }
        return max;
    }

    public void removeOrder(int orderIndex, Order toBeRemovedOrder) {
        orderSet.remove(orderIndex);
    }

    public String generateAllOrdersInfo() {
        String allOrdersInfo = "";
        for (int orderIndex : orderSet.keySet()){
            allOrdersInfo += "ORDER NUMBER: " + orderSet.get(orderIndex).getOrderIndex() + "\n"
            + "CUSTOMER NAME: " + orderSet.get(orderIndex).getName() + "\n"
            + "STATE: " + orderSet.get(orderIndex).getState() + "\n"
            + "TAX RATE: " + orderSet.get(orderIndex).getTaxRate() + "\n"
            + "PRODUCT TYPE: " + orderSet.get(orderIndex).getProductName() + "\n"
            + "AREA: " + orderSet.get(orderIndex).getArea() + "\n"
            + "COST PER SQUARE FOOT: " + orderSet.get(orderIndex).getCostPerSquareFoot() + "\n"
            + "LABOUR COST PER SQUARE FOOT: " + orderSet.get(orderIndex).getLabourCostPerSquareFoot() + "\n"
            + "MATERIAL COST: " + orderSet.get(orderIndex).getMaterialCost() + "\n"
            + "LABOUR COST: " + orderSet.get(orderIndex).getLabourCost() + "\n"
            + "TOTAL TAX: " + orderSet.get(orderIndex).getTax() + "\n"
            + "TOTAL PRICE: " + orderSet.get(orderIndex).getTotal() + "\n\n"
            ;
        }
        return allOrdersInfo;
    }
    
}