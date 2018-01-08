/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.softwareguildweek10_superhero.dao;

import com.mycompany.softwareguildweek10_superhero.model.Location;
import com.mycompany.softwareguildweek10_superhero.model.Organization;
import com.mycompany.softwareguildweek10_superhero.model.Sighting;
import com.mycompany.softwareguildweek10_superhero.model.SuperHero;
import com.mycompany.softwareguildweek10_superhero.model.SuperPower;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author yidingweng
 */
public interface SuperHeroWorldDao {
    public void addLocation(Location location);

    public void deleteLocation(int locationid);

    public void updateLocation(Location location);

    public Location getLocationByid(int locationid);

    public List<Location> getAllLocations();
    
    public void addSuperHero(SuperHero superHero);

    public void deleteSuperHero(int superHeroid);

    public void updateSuperHero(SuperHero superHero);

    public SuperHero getSuperHeroByid(int superHeroid);

    public List<SuperHero> getAllSuperHeros();
    
    public List<SuperHero> getSuperHerosBySuperPowerid(int locationid);
    
    public List<SuperHero> getSuperHerosByLocationid(int locationid);
    
    public List<SuperHero> getSuperHerosByOrganizationid(int organizationid);
    
    public void addSuperPower(SuperPower superPower);

    public void deleteSuperPower(int superPowerid);

    public void updateSuperPower(SuperPower superPower);

    public SuperPower getSuperPowerByid(int superPowerid);

    public List<SuperPower> getAllSuperPowers();
    
    public void addOrganization(Organization organization);

    public void deleteOrganization(int organizationid);

    public void updateOrganization(Organization organization);

    public Organization getOrganizationByid(int organizationid);

    public List<Organization> getAllOrganizations();
    
    public void addSighting(Sighting sighting);
    
    public void deleteSighting(int sightingid);
    
    public void updateSighting(Sighting sighting);
    
    public Sighting getSightingByid(int sightingid);
    
    public List<Sighting> getAllSightings();
    
    public List<Sighting> getSightingsOrderByDate();
    
    public List<Sighting> getSightingsByLocationidAndSightingDate(int locationid, LocalDate sightingDate);
    
    public List<Sighting> getSightingsBySightingDate(LocalDate sightingDate);
    
    public List<SuperHero> findSuperHerosForSighting(Sighting sighting); 
}
