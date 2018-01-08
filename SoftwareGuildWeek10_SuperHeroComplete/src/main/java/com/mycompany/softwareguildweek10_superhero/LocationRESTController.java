/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/*package com.mycompany.softwareguildweek10_superhero;

import com.mycompany.softwareguildweek10_superhero.dao.SuperHeroWorldDao;
import com.mycompany.softwareguildweek10_superhero.model.Location;
import java.util.List;
import javax.inject.Inject;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
/**
 *
 * @author yidingweng
 */

/*@CrossOrigin
@Controller
public class LocationRESTController {
    private SuperHeroWorldDao dao;
    
    @Inject
    public LocationRESTController(SuperHeroWorldDao dao) {
        this.dao = dao;
    }
    
    @RequestMapping(value = "/location/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Location getLocation(@PathVariable("id") int id) {
        return dao.getLocationByid(id);
    }
    
    @RequestMapping(value = "/location", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public void createLocation(@RequestBody Location location) {
        dao.addLocation(location);
    }
    
    @RequestMapping(value = "/location/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteLocation(@PathVariable("id") int id) {
        dao.deleteLocation(id);
    }
    
    @RequestMapping(value = "/location/{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateLocation(@PathVariable("id") int id, 
                              @Valid @RequestBody Location location) throws UpdateIntegrityException {

        if (id != location.getLocationid()) {
            throw 
              new UpdateIntegrityException("Contact Id on URL must match Contact Id in submitted data.");
        }
        dao.updateLocation(location);
    }
    
    @RequestMapping(value = "/locations", method = RequestMethod.GET)
    @ResponseBody
    public List<Location> getAllLocations() {
        return dao.getAllLocations();
    }
}*/
