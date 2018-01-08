/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.softwareguildweek10_superhero.model;

import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author yidingweng
 */
public class Sighting {
    private int sightingid;
    private int locationid;
    private Location location;
    private LocalDate sightingDate;
    private List<SuperHero> superHeros;

    public int getSightingid() {
        return sightingid;
    }

    public void setSightingid(int sightingid) {
        this.sightingid = sightingid;
    }
    
    public int getLocationid(){
        return locationid;
    }
    
    public void setLocationid(int locationid){
        this.locationid = locationid;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
    
    public LocalDate getSightingDate() {
        return sightingDate;
    }

    public void setSightingDate(LocalDate sightingDate) {
        this.sightingDate = sightingDate;
    }
    
    public List<SuperHero> getSuperHeros() {
        return superHeros;
    }

    public void setSuperHeros(List<SuperHero> superHeros) {
        this.superHeros = superHeros;
    }
    
}
