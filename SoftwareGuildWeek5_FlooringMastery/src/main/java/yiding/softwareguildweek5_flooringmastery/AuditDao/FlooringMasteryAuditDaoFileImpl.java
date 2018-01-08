/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yiding.softwareguildweek5_flooringmastery.AuditDao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.Set;
import yiding.softwareguildweek5_flooringmastery.DAO.OrderSet;
import yiding.softwareguildweek5_flooringmastery.DAO.ProductSet;
import yiding.softwareguildweek5_flooringmastery.DAO.StateSet;
import yiding.softwareguildweek5_flooringmastery.DTO.Order;
import yiding.softwareguildweek5_flooringmastery.DTO.Product;
import yiding.softwareguildweek5_flooringmastery.DTO.State;
//import yiding.softwareguildweek5_flooringmastery.FlooringMasteryPersistenceException;

/**
 *
 * @author yidingweng
 */
public class FlooringMasteryAuditDaoFileImpl {
    
    static FlooringMasteryAuditDaoFileImpl auditDao = new FlooringMasteryAuditDaoFileImpl();
    //static StateSet set = new StateSet();
    
    public static final String AUDIT_FILE = "audit.txt";
    
    public void writeOrderEntry(String fileName,OrderSet orderDao) throws IOException{
        
        
            PrintWriter out = new PrintWriter(new FileWriter(fileName));
            for (int orderIndex : orderDao.orderSet.keySet()){
            out.println(orderDao.orderSet.get(orderIndex).getOrderIndex()
            + "::" + orderDao.orderSet.get(orderIndex).getName()
            + "::" + orderDao.orderSet.get(orderIndex).getState()
            + "::" + orderDao.orderSet.get(orderIndex).getTaxRate()
            + "::" + orderDao.orderSet.get(orderIndex).getProductName()
            + "::" + orderDao.orderSet.get(orderIndex).getArea()
            + "::" + orderDao.orderSet.get(orderIndex).getCostPerSquareFoot()
            + "::" + orderDao.orderSet.get(orderIndex).getLabourCostPerSquareFoot()
            + "::" + orderDao.orderSet.get(orderIndex).getMaterialCost()
            + "::" + orderDao.orderSet.get(orderIndex).getLabourCost()
            + "::" + orderDao.orderSet.get(orderIndex).getTax()
            + "::" + orderDao.orderSet.get(orderIndex).getTotal()
            );
        }
        out.flush();
        out.close();
            
        
    }
    
    public void createNewFile(String newFileName) throws IOException{
        //System.out.println("before create file");
        //File file = new File(newFileName);
        //System.out.println("after create file");
        PrintWriter out = new PrintWriter(new FileWriter(newFileName));
        out.close();
    }
    
    public StateSet readStateEntry(String fileName) throws FileNotFoundException, IOException{
        StateSet stateSet = new StateSet();
        BufferedReader in = new BufferedReader(new FileReader(fileName));
        String line = "";
        
        while ((line = in.readLine()) != null) {
            State state = new State();
            String parts[] = line.split("::");
            //LocalDate ld = ;
            state.setStateName(parts[0]);
            state.setTax(new BigDecimal(parts[1]));
            
            stateSet.addState(state.getStateName(), state);
        }
        stateSet.stateToString();
        //System.out.println("in AuditDao, readStateEntry" + stateSet.stateToString());
        stateSet.stateToArray();
        in.close();
        return stateSet;
    }
    
    public ProductSet readProductEntry(String fileName) throws FileNotFoundException, IOException{
        ProductSet productSet = new ProductSet();
        BufferedReader in = new BufferedReader(new FileReader(fileName));
        String line = "";
        
        while ((line = in.readLine()) != null) {
            Product product = new Product();
            String parts[] = line.split("::");
            product.setProductName(parts[0]);
            product.setCostPerSquareFoot(new BigDecimal(parts[1]));
            product.setLabourCostPerSquareFoot(new BigDecimal(parts[2]));// I made a mistake here replace 2 with 1, end up returning material cost
            
            productSet.addProduct(product.getProductName(), product);
        }
        productSet.productToString();
        //System.out.println("in AuditDao, readProductEntry" + productSet.productToString());
        productSet.getProductArray();
        in.close();
        return productSet;
    }
    public int readOrderEntry(String orderFileName){
        return 1;
    }

    public OrderSet readOrdersOfFileDate(String orderFileName) throws FileNotFoundException, IOException{
        OrderSet orderSet = new OrderSet();
        BufferedReader in = new BufferedReader(new FileReader(orderFileName));
        String line = "";
        
        while((line = in.readLine()) != null) {
            Order order = new Order();
            String[] parts = line.split("::");
            order.setOrderIndex(Integer.parseInt(parts[0]));
            order.setName(parts[1]);
            order.setState(parts[2]);
            order.setTaxRate(new BigDecimal(parts[3]));
            order.setProductName(parts[4]);
            order.setArea(new BigDecimal(parts[5]));
            order.setCostPerSquareFoot(new BigDecimal(parts[6]));
            order.setLabourCostPerSquareFoot(new BigDecimal(parts[7]));
            order.setMaterialCost(new BigDecimal(parts[8]));
            order.setLabourCost(new BigDecimal(parts[9]));
            order.setTax(new BigDecimal(parts[10]));
            order.setTotal(new BigDecimal(parts[11]));
            
            orderSet.addOrder(order.getOrderIndex(), order);
        }
        in.close();
        return orderSet;
    }

    public int getOrderIndexFromFile(String orderFileName) throws FileNotFoundException, IOException {
        //OrderSet orderSet = new OrderSet();
        BufferedReader in = new BufferedReader(new FileReader(orderFileName));
        String line = "";
        int index;
        int max = 0;
        while((line = in.readLine()) != null) {
            Order order = new Order();
            String[] parts = line.split("::");
            index = order.getOrderIndex();
            if (index > max){
                max = index;
            }
        }
        return max;
            
    }
}
