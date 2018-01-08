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
public class PreOrder {
    protected String name;
    protected int stateNo;
    protected int productNameNo;
    protected BigDecimal area ;
    protected int index;

    public PreOrder(String name, int state, int productName, BigDecimal area) {
        this.name = name;
        this.stateNo = state;
        this.productNameNo = productName;
        this.area = area;
    }
    
    public PreOrder(int index, String name, int state, int productName, BigDecimal area) {
        this.index = index;
        this.name = name;
        this.stateNo = state;
        this.productNameNo = productName;
        this.area = area;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStateNo() {
        return stateNo;
    }

    public void setState(int state) {
        this.stateNo = state;
    }

    public int getProductNameNo() {
        return productNameNo;
    }

    public void setProductNameNo(int productName) {
        this.productNameNo = productName;
    }

    public BigDecimal getArea() {
        return area;
    }

    public void setArea(BigDecimal area) {
        this.area = area;
    }   

}
