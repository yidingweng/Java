/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yiding.softwareguildweek5_flooringmastery.DTO;

import java.math.BigDecimal;

/**
 *
 * @author yidingweng
 */
public class Product {
    protected String productName;
    protected BigDecimal costPerSquareFoot;
    protected BigDecimal labourCostPerSquareFoot;
    
    public Product() {
        this.productName = "";
        this.costPerSquareFoot = new BigDecimal("0");
        this.labourCostPerSquareFoot = new BigDecimal("0");
    }
    
    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setCostPerSquareFoot(BigDecimal costPerSquareFoot) {
        this.costPerSquareFoot = costPerSquareFoot;
    }

    public void setLabourCostPerSquareFoot(BigDecimal labourCostPerSquareFoot) {
        this.labourCostPerSquareFoot = labourCostPerSquareFoot;
    }

    public String getProductName() {
        return productName;
    }

    public BigDecimal getCostPerSquareFoot() {
        return costPerSquareFoot;
    }

    public BigDecimal getLabourCostPerSquareFoot() {
        return labourCostPerSquareFoot;
    }

    public Product(String productName, BigDecimal costPerSquareFoot, BigDecimal labourCostPerSquareFoot) {
        this.productName = productName;
        this.costPerSquareFoot = costPerSquareFoot;
        this.labourCostPerSquareFoot = labourCostPerSquareFoot;
    }
    
}
