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
public class State {
    protected String stateName;
    protected BigDecimal tax;

    public State() {
        this.stateName = "";
        this.tax = new BigDecimal(0);
    }

    public String getStateName() {
        return stateName;
    }

    public BigDecimal getTax() {
        return tax;
    }

    public State(String stateName, BigDecimal tax) {
        this.stateName = stateName;
        this.tax = tax;
    }

    public void setStateName(String name) {
        this.stateName = name;
    }

    public void setTax(BigDecimal tax) {
        this.tax = tax;
    }
    
}
