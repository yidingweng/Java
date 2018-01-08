/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/*package com.mycompany.softwareguildweek10_superhero;

import com.mycompany.softwareguildweek10_superhero.dao.SuperHeroWorldDao;
import com.mycompany.softwareguildweek10_superhero.model.SuperPower;
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
public class SuperPowerRESTController {
    
    private SuperHeroWorldDao dao;
    
    @Inject
    public SuperPowerRESTController(SuperHeroWorldDao dao) {
        this.dao = dao;
    }
    
    @RequestMapping(value = "/superPower/{id}", method = RequestMethod.GET)
    @ResponseBody
    public SuperPower getSuperPower(@PathVariable("id") int id) {
        return dao.getSuperPowerByid(id);
    }
    
    @RequestMapping(value = "/superPower", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public void createSuperPower(@RequestBody SuperPower superPower) {
        dao.addSuperPower(superPower);
    }
    
    @RequestMapping(value = "/superPower/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteSuperPower(@PathVariable("id") int id) {
        dao.deleteSuperPower(id);
    }
    
    @RequestMapping(value = "/superPower/{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateSuperPower(@PathVariable("id") int id, 
                              @Valid @RequestBody SuperPower superPower) throws UpdateIntegrityException {

        if (id != superPower.getSuperPowerid()) {
            throw 
              new UpdateIntegrityException("SuperPower Id on URL must match SuperPower Id in submitted data.");
        }
        dao.updateSuperPower(superPower);
    }
    
    @RequestMapping(value = "/superPowers", method = RequestMethod.GET)
    @ResponseBody
    public List<SuperPower> getAllSuperPowers() {
        return dao.getAllSuperPowers();
    }
    
}*/
