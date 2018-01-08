/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.softwareguildweek10_superhero.model;

import java.util.List;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author yidingweng
 */
public class SuperHero {
    private int SuperHeroid;
    @NotEmpty(message = "You must supply a value for  SuperHero Name.")
    @Length(max = 50, message = "SuperHero Name must be no more than 200 characters in length.")
    private String superHeroName;
    @Length(max = 200, message = "Description must be no more than 200 characters in length.")
    private String description;
    @Length(max = 20, message = "Nature must be no more than 20 characters in length.")
    private String nature;
    private List<SuperPower> superPowers;
    private List<Organization> organizations;
    //private List<Location> locations;
    
    
    public int getSuperHeroid() {
        return SuperHeroid;
    }

    public void setSuperHeroid(int SuperHeroid) {
        this.SuperHeroid = SuperHeroid;
    }
    
    public String getSuperHeroName() {
        return superHeroName;
    }

    public void setSuperHeroName(String superHeroName) {
        this.superHeroName = superHeroName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNature() {
        return nature;
    }

    public void setNature(String nature) {
        this.nature = nature;
    }
    public List<SuperPower> getSuperPowers() {
        return superPowers;
    }

    public void setSuperPowers(List<SuperPower> superPowers) {
        this.superPowers = superPowers;
    }

    public List<Organization> getOrganizations() {
        return organizations;
    }

    public void setOrganizations(List<Organization> organizations) {
        this.organizations = organizations;
    }

    /*public List<Location> getLocations() {
        return locations;
    }

    public void setLocations(List<Location> locations) {
        this.locations = locations;
    }*/
}
