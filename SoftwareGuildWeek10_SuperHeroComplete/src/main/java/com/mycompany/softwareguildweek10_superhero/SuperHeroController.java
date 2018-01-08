/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.softwareguildweek10_superhero;

import com.mycompany.softwareguildweek10_superhero.dao.SuperHeroWorldDao;
import com.mycompany.softwareguildweek10_superhero.model.Organization;
import com.mycompany.softwareguildweek10_superhero.model.SuperHero;
import com.mycompany.softwareguildweek10_superhero.model.SuperPower;
import java.util.ArrayList;
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
public class SuperHeroController {
    SuperHeroWorldDao dao;
    
    @Inject
    public SuperHeroController(SuperHeroWorldDao dao) {
        this.dao = dao;
    }
    
    @RequestMapping(value = "/displaySuperHerosPage", method = RequestMethod.GET)
    public String displaySuperHerosPage(Model model){
        List<SuperHero> SuperHeroList = dao.getAllSuperHeros();
        List<SuperPower> SuperPowerList = dao.getAllSuperPowers();
        List<Organization> OrganizationList = dao.getAllOrganizations();
        model.addAttribute("superHeroList", SuperHeroList);
        model.addAttribute("superPowerList", SuperPowerList);
        model.addAttribute("organizationList", OrganizationList);
        return "superHeros";
    }
    
    @RequestMapping(value = "/displaySuperHeroDetails", method = RequestMethod.GET)
    public String displaySuperHeroDetails(HttpServletRequest request, Model model) {
        String superHeroidParameter = request.getParameter("superHeroid");
        int superHeroid = Integer.parseInt(superHeroidParameter);

        SuperHero superHero = dao.getSuperHeroByid(superHeroid);
        if (superHero.getSuperPowers().size() < 1){
            SuperPower noSuperPower = new SuperPower();
            noSuperPower.setPowerName("No Super Power");
            List<SuperPower> superPowers = new ArrayList<SuperPower>();
            superPowers.add(noSuperPower);
            superHero.setSuperPowers(superPowers);
        }
        
        if (superHero.getOrganizations().size() < 1){
            Organization noOrganization = new Organization();
            noOrganization.setOrganizationName("No associated Organization");
            List<Organization> organizations = new ArrayList<Organization>();
            organizations.add(noOrganization);
            superHero.setOrganizations(organizations);
        }

        model.addAttribute("superHero", superHero);

        return "superHeroDetails";
    }
    
    @RequestMapping(value = "/displayEditSuperHeroForm", method = RequestMethod.GET)
    public String displayEditSuperHeroForm(HttpServletRequest request, Model model) {
        String superHeroidParameter = request.getParameter("superHeroid");
        int superHeroid = Integer.parseInt(superHeroidParameter);
        SuperHero superHero = dao.getSuperHeroByid(superHeroid);
        
        model.addAttribute("superHero", superHero);
        List<SuperPower> superPowerList = dao.getAllSuperPowers();
        List<Organization> organizationList = dao.getAllOrganizations();
        model.addAttribute("superPowerList", superPowerList);
        model.addAttribute("organizationList", organizationList);
        return "editSuperHeroForm";
    }
    
    @RequestMapping(value = "/deleteSuperHero", method = RequestMethod.GET)
    public String deleteSuperHero(HttpServletRequest request) {
        String superHeroidParameter = request.getParameter("superHeroid");
        int superHeroid = Integer.parseInt(superHeroidParameter);
        dao.deleteSuperHero(superHeroid);
        return "redirect:displaySuperHerosPage";
    }
    
    @RequestMapping(value = "/createSuperHero", method = RequestMethod.POST)//
    public String createSuperHero(HttpServletRequest request) {
        // grab the incoming values from the form and create a new Contact
        // object
        SuperPower superPower;
        Organization organization;
        SuperHero superHero = new SuperHero();
        superHero.setSuperHeroName(request.getParameter("superHeroName"));
        superHero.setDescription(request.getParameter("description"));
        superHero.setNature(request.getParameter("nature"));
        
        superPower = dao.getSuperPowerByid(Integer.parseInt(request.getParameter("superPower")));
        List<SuperPower> superPowerList = new ArrayList<SuperPower>();
        superPowerList.add(superPower);
        
        superHero.setSuperPowers(superPowerList);
        
        organization = dao.getOrganizationByid(Integer.parseInt(request.getParameter("organization")));
        List<Organization> organizationList = new ArrayList<Organization>();
        organizationList.add(organization);
        
        superHero.setOrganizations(organizationList);
        
        dao.addSuperHero(superHero);

        return "redirect:displaySuperHerosPage";//redirect request
    }
    
    @RequestMapping(value = "/editSuperHero", method = RequestMethod.POST)
    public String editSuperHero(@Valid @ModelAttribute("superHero") SuperHero superHero,
            HttpServletRequest request,
            
            BindingResult result) {

        if (result.hasErrors()) {
            return "editSuperHeroForm";
        }
        SuperPower superPower;
        Organization organization;
        
        superPower = dao.getSuperPowerByid(Integer.parseInt(request.getParameter("superPower")));
        List<SuperPower> superPowerList = new ArrayList<SuperPower>();
        superPowerList.add(superPower);
        
        superHero.setSuperPowers(superPowerList);
        
        organization = dao.getOrganizationByid(Integer.parseInt(request.getParameter("organization")));
        List<Organization> organizationList = new ArrayList<Organization>();
        organizationList.add(organization);
        
        superHero.setOrganizations(organizationList);
        //superHero.setSuperPowers(request.getParameter("superPower"));
        //superHero.setOrganizations(request.getParameter("organization"));
        dao.updateSuperHero(superHero);

        return "redirect:displaySuperHerosPage";
    }
}
