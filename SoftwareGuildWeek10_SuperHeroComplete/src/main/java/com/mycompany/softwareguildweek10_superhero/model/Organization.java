/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.softwareguildweek10_superhero.model;

import java.util.List;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author yidingweng
 */
public class Organization {
    private int organizationid;
    @NotEmpty(message = "You must supply a value for Organization Name.")
    @Length(max = 50, message = "Organization Name must be no more than 50 characters in length.")
    private String organizationName;
    @Length(max = 50, message = "Description Name must be no more than 50 characters in length.")  
    private String description;
    @Length(max = 50, message = "Address Name must be no more than 50 characters in length.")  
    private String address;
    @Email(message = "Please enter a valid email address.")
    @Length(max = 50, message = "Email must be no more than 50 characters in length.")
    private String email;
    private List<SuperHero> superHeros;
    
    public int getOrganizationid() {
        return organizationid;
    }

    public void setOrganizationid(int organizationId) {
        this.organizationid = organizationId;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public List<SuperHero> getSuperHeros(){
        return superHeros;
    }
    
    public void setSuperHeros(List<SuperHero> superHeros){
        this.superHeros = superHeros;
    }
}
