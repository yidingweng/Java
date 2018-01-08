/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yiding.softwareguildweek5_flooringmastery;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import yiding.softwareguildweek5_flooringmastery.AuditDao.FlooringMasteryAuditDaoFileImpl;
import yiding.softwareguildweek5_flooringmastery.DAO.OrderSet;
import yiding.softwareguildweek5_flooringmastery.DAO.ProductSet;
import yiding.softwareguildweek5_flooringmastery.DAO.StateSet;
import yiding.softwareguildweek5_flooringmastery.DTO.Order;
import yiding.softwareguildweek5_flooringmastery.DTO.PreOrder;
import yiding.softwareguildweek5_flooringmastery.DTO.Product;
import yiding.softwareguildweek5_flooringmastery.DTO.State;

/**
 *
 * @author yidingweng
 */
public class FlooringMasteryService {
    private FlooringMasteryAuditDaoFileImpl auditDao;
    private OrderSet fileDao;
    private OrderSet orderDao;
    private StateSet stateDao;
    private ProductSet productDao;
    
    public FlooringMasteryService (FlooringMasteryAuditDaoFileImpl auditDao,OrderSet fileDao,
            OrderSet orderDao,StateSet stateDao,ProductSet productDao) {
        this.auditDao = auditDao;
        this.fileDao = fileDao;
        this.orderDao = orderDao;
        this.stateDao = stateDao;
        this.productDao = productDao;
    }
    
    Order order;
    int orderIndex;
    String currentFileName;

    
    public String authenticateDisplayAllOrders(String orderFileName) throws IOException {
        orderDao = auditDao.readOrdersOfFileDate(orderFileName);
        String allOrdersInfo = orderDao.generateAllOrdersInfo();
        currentFileName = orderFileName;
        return allOrdersInfo;
    }
   
    public Order authenticateDisplayOrder(String fileName, int index) throws IOException{
        orderDao = auditDao.readOrdersOfFileDate(fileName);
        order = orderDao.getOrder(index);
        currentFileName = fileName;
        return order;
    }

    public void authenticateAddOrder(Order currentOrder) {
        orderDao.addOrder(currentOrder.getOrderIndex(), currentOrder);
        //auditDao.writeOrderEntry("Order "+ currentOrder.getName() + " CREATED");
    }

    public void authenticateEditOrder(Order currentOrder) {
        orderDao.addOrder(currentOrder.getOrderIndex(), currentOrder); //To change body of generated methods, choose Tools | Templates.
    }
   
    public void prepareAdd1(String stateFileName, String productFileName) throws IOException{
        stateDao = auditDao.readStateEntry(stateFileName);
        productDao = auditDao.readProductEntry(productFileName);
    }
    public void prepareAdd2(String orderFileName) throws IOException{
        //stateDao = auditDao.readStateEntry(stateFileName);
        //productDao = auditDao.readProductEntry(productFileName);// A place where i made mistake by replacing productFile with StateFile
        try{
            //auditDao.readOrderEntry(fileName)
            orderDao = auditDao.readOrdersOfFileDate(orderFileName);
            
        } catch (FileNotFoundException e){
            auditDao.createNewFile(orderFileName);
            //System.out.println("from service.prepareAdd, file doesn't exist, first order of the day! a new file is created.");
            orderDao = new OrderSet();
            fileDao.addFile(orderFileName, orderDao);
            
            
        }
    }
    
    public String getStateStringFromStateSet(){
        String stateString = stateDao.getStateString();
        //System.out.println("in Service, getStateStringFromStateSet: "+ stateString);///////////
        return stateString;
    }
    public int getStateNoFromStateSet(){
        int stateNo = stateDao.getSize();
        return stateNo;
    }
    
    public String getProductStringFromProductSet(){
        String productString = productDao.getProductString();
        //System.out.println("in Service, getProductStringFromProductSet: "+ productString);///////////
        return  productString;
    }
    
    public int getProductNoFromProductSet(){
        int productNo = productDao.getSize();
        return productNo;
    }

    public Order getFullOrderInfo(PreOrder currentOrder) {
        orderIndex = orderDao.getOrderIndex() + 1;
        String customerName = currentOrder.getName();
        int stateNo = currentOrder.getStateNo();
        int productNameNo = currentOrder.getProductNameNo();
        BigDecimal area = currentOrder.getArea();
        /*System.out.println("From preOrder " + customerName + "::" 
                                            + stateNo + "::" 
                                            + productNameNo + "::" 
                                            + area);*/// test (right)
        String[][] stateArray = stateDao.stateToArray();
        String stateName = stateArray[stateNo][1];
        //System.out.println("stateName from service: " + stateName); // test (right)
        State state = stateDao.getState(stateName);
        BigDecimal taxRate = state.getTax().divide(new BigDecimal("100"), 2, RoundingMode.HALF_UP);
        //System.out.println("Tax from service: " + tax);// test (right)
        
        String[][] productArray = productDao.productToArray();
        String productName = productArray[productNameNo][1];
        //System.out.println("productName from service: " + productName); // test (right)
        Product product = productDao.getProduct(productName);
        BigDecimal unitMaterialCost = product.getCostPerSquareFoot();
        BigDecimal unitLabourCost = product.getLabourCostPerSquareFoot();
        //System.out.println("Unit material cost: " + unitMaterialCost + " Unit labour cost: " + unitLabourCost);//test(right)
        
        BigDecimal totalMaterialCost = area.multiply(unitMaterialCost).setScale(2, RoundingMode.HALF_UP);
        BigDecimal totalLabourCost = area.multiply(unitLabourCost).setScale(2, RoundingMode.HALF_UP);
        
        BigDecimal totalCostBeforeTax = totalMaterialCost.add(totalLabourCost).setScale(2, RoundingMode.HALF_UP);
        
        BigDecimal totalTax = totalCostBeforeTax.multiply(taxRate).setScale(2, RoundingMode.HALF_UP);
        BigDecimal totalCost = totalCostBeforeTax.multiply(totalTax).setScale(2, RoundingMode.HALF_UP);
        //System.out.println("totalCost: " + totalCost);//test(right)
        Order newOrder = new Order(orderIndex,customerName, stateName, state.getTax(), productName, area, unitMaterialCost, unitLabourCost, totalMaterialCost, totalLabourCost, totalTax, totalCost);
        return newOrder;
    }

    public void saveOrderDaoToFile(String orderFileName) throws IOException {
        auditDao.writeOrderEntry(orderFileName, orderDao);
    }

    public boolean checkDaoIsEmptyOrNot() {
        if (orderDao.orderSet.size() == 0)
            return true;
        else
            return false;
    }

    public void authenticateRemoveOrder(Order toBeRemovedOrder) {
        orderDao.removeOrder(toBeRemovedOrder.getOrderIndex(), toBeRemovedOrder);
    }

    public String getCurrentFileName() {
        return currentFileName;
    }

    
}
