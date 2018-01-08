/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.softwareguildweek10_superhero.model;

/**
 *
 * @author yidingweng
 */
public class SuperPower {
    private int SuperPowerid;
    private String powerName;
    private String description;
    
    public int getSuperPowerid() {
        return SuperPowerid;
    }

    public void setSuperPowerid(int SuperPowerid) {
        this.SuperPowerid = SuperPowerid;
    }
    
    public String getPowerName() {
        return powerName;
    }

    public void setPowerName(String powerName) {
        this.powerName = powerName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String discription) {
        this.description = discription;
    }
    
}
