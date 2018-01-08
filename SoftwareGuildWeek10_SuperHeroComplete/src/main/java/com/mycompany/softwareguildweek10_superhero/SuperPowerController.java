/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.softwareguildweek10_superhero;

import com.mycompany.softwareguildweek10_superhero.dao.SuperHeroWorldDao;
import com.mycompany.softwareguildweek10_superhero.model.SuperPower;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
/**
 *
 * @author yidingweng
 */
@Controller
public class SuperPowerController {
    SuperHeroWorldDao dao;
    
    @Inject
    public SuperPowerController(SuperHeroWorldDao dao) {
        this.dao = dao;
    }
    
    @RequestMapping(value = "/displaySuperPowersPage", method = RequestMethod.GET)
    public String displaySuperPowersPage(Model model){
        List<SuperPower> superPowerList = dao.getAllSuperPowers();
        model.addAttribute("superPowerList", superPowerList);
        
        return "superPowers";
    }
    
    @RequestMapping(value = "/displaySuperPowerDetails", method = RequestMethod.GET)
    public String displaySuperPowerDetails(HttpServletRequest request, Model model) {
        String superPoweridParameter = request.getParameter("superPowerid");
        int superPowerid = Integer.parseInt(superPoweridParameter);

        SuperPower superPower = dao.getSuperPowerByid(superPowerid);

        model.addAttribute("superPower", superPower);

        return "superPowerDetails";
    }
    
    @RequestMapping(value = "/displayEditSuperPowerForm", method = RequestMethod.GET)
    public String displayEditSuperPowerForm(HttpServletRequest request, Model model) {
        String superPoweridParameter = request.getParameter("superPowerid");
        int superPowerid = Integer.parseInt(superPoweridParameter);
        SuperPower superPower = dao.getSuperPowerByid(superPowerid);
        
        model.addAttribute("superPower", superPower);
        
        return "editSuperPowerForm";
    }
    
    @RequestMapping(value = "/deleteSuperPower", method = RequestMethod.GET)
    public String deleteSuperPower(HttpServletRequest request) {
        String superPoweridParameter = request.getParameter("superPowerid");
        int superPowerid = Integer.parseInt(superPoweridParameter);
        dao.deleteSuperPower(superPowerid);
        return "redirect:displaySuperPowersPage";
    }
    
    @RequestMapping(value = "/createSuperPower", method = RequestMethod.POST)//
    public String createSuperPower(HttpServletRequest request) {
        // grab the incoming values from the form and create a new Contact
        // object
        SuperPower superPower = new SuperPower();
        superPower.setPowerName(request.getParameter("superPowerName"));
        superPower.setDescription(request.getParameter("description"));
        
        // persist the new Contact
        dao.addSuperPower(superPower);

        // we don't want to forward to a View component - we want to
        // redirect to the endpoint that displays the Contacts Page
        // so it can display the new Contact in the table.
        return "redirect:displaySuperPowersPage";//redirect request
    }
    
    @RequestMapping(value = "/editSuperPower", method = RequestMethod.POST)
    public String editSuperPower(@Valid @ModelAttribute("superPower") SuperPower superPower, BindingResult result) {

        if (result.hasErrors()) {
            return "editSuperPowerForm";
        }

        dao.updateSuperPower(superPower);

        return "redirect:displaySuperPowersPage";
    }
}