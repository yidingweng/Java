/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yiding.softwareguildweek5_flooringmastery.DTO;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 *
 * @author yidingweng
 */
public class Order {
    protected int orderIndex;
    protected String name;
    protected String state;
    protected BigDecimal taxRate;// new
    protected String productName;
    protected BigDecimal area ;
    protected BigDecimal costPerSquareFoot;// new
    protected BigDecimal materialCost;
    protected BigDecimal labourCostPerSquareFoot;// new
    protected BigDecimal labourCost;
    protected BigDecimal tax;
    protected BigDecimal total;
    
    public Order(int orderIndex, String name, String state, BigDecimal taxRate, String productName, BigDecimal area, BigDecimal costPerSquareFoot, BigDecimal labourCostPerSquareFoot, BigDecimal materialCost, BigDecimal labourCost, BigDecimal tax, BigDecimal total) {
        this.orderIndex = orderIndex;
        this.name = name;
        this.state = state;
        this.taxRate = taxRate;
        this.productName = productName;
        this.area = area;
        this.costPerSquareFoot = costPerSquareFoot;
        this.labourCostPerSquareFoot = labourCostPerSquareFoot;
        this.materialCost = materialCost;
        this.labourCost = labourCost;
        this.tax = tax;
        this.total = total;
    }

    public Order() {
        this.orderIndex = 0;
        this.name = "";
        this.state = "";
        this.taxRate = new BigDecimal("0");
        this.productName = "";
        this.area = new BigDecimal("0");
        this.costPerSquareFoot = new BigDecimal("0");
        this.labourCostPerSquareFoot = new BigDecimal("0");
        this.materialCost = new BigDecimal("0");
        this.labourCost = new BigDecimal("0");
        this.tax = new BigDecimal("0");
        this.total = new BigDecimal("0");
    }
    public int getOrderIndex(){
        return orderIndex;
    }
    
    public void setOrderIndex(int orderIndex){
        this.orderIndex = orderIndex;
    }
    
    public BigDecimal getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(BigDecimal taxRate) {
        this.taxRate = taxRate;
    }

    public BigDecimal getCostPerSquareFoot() {
        return costPerSquareFoot;
    }

    public void setCostPerSquareFoot(BigDecimal costPerSquareFoot) {
        this.costPerSquareFoot = costPerSquareFoot;
    }

    public BigDecimal getLabourCostPerSquareFoot() {
        return labourCostPerSquareFoot;
    }

    public void setLabourCostPerSquareFoot(BigDecimal labourCostPerSquareFoot) {
        this.labourCostPerSquareFoot = labourCostPerSquareFoot;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public BigDecimal getArea() {
        return area;
    }

    public void setArea(BigDecimal area) {
        this.area = area;
    }

    public BigDecimal getMaterialCost() {
        return materialCost;
    }

    public void setMaterialCost(BigDecimal materialCost) {
        this.materialCost = materialCost;
    }

    public BigDecimal getLabourCost() {
        return labourCost;
    }

    public void setLabourCost(BigDecimal laborCost) {
        this.labourCost = laborCost;
    }

    public BigDecimal getTax() {
        return tax;
    }

    public void setTax(BigDecimal tax) {
        this.tax = tax;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }
    
    /*public Order(String name, String state, String productName, double area) {
        this.name = name;
        this.state = state;
        this.productName = productName;
        this.area = area;
        
    }*/

      
}
