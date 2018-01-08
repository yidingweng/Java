/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.softwareguildweek10_superhero;

import com.mycompany.softwareguildweek10_superhero.dao.SuperHeroWorldDao;
import com.mycompany.softwareguildweek10_superhero.model.Location;
import com.mycompany.softwareguildweek10_superhero.model.Sighting;
import com.mycompany.softwareguildweek10_superhero.model.SuperHero;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.format.annotation.DateTimeFormat;
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
public class SightingController {
    SuperHeroWorldDao dao;
    
    @Inject
    public SightingController(SuperHeroWorldDao dao) {
        this.dao = dao;
    }


    @RequestMapping(value = "/displaySightingsPage", method = RequestMethod.GET)
    public String displaySightingsPage(Model model){
        List<Sighting> SightingList = dao.getAllSightings();
        List<SuperHero> SuperHeroList = dao.getAllSuperHeros();
        List<Location> LocationList = dao.getAllLocations();
        for (Sighting sighting : SightingList) {
            int locationid = sighting.getLocationid();
            sighting.setLocation(dao.getLocationByid(locationid));
        }
        model.addAttribute("sightingList", SightingList);
        model.addAttribute("superHeroList", SuperHeroList);
        model.addAttribute("locationList", LocationList);
        return "sightings";
    }
    
    //@RequestMapping(value = "/", method = RequestMethod.GET)
    @RequestMapping(value = "/displayTopTenSightings", method = RequestMethod.GET)
    public String displayTopTenSightings(Model model){
        List<Sighting> topTenSightings = dao.getSightingsOrderByDate();
        //List<Location> LocationList = dao.getAllLocations();
        for (Sighting sighting : topTenSightings) {
            int locationid = sighting.getLocationid();
            sighting.setLocation(dao.getLocationByid(locationid));
        }
        model.addAttribute("topTenSightings", topTenSightings);
        //model.addAttribute("locationList", LocationList);
        return "../index";
    }
    
    @RequestMapping(value = "/displaySightingDetails", method = RequestMethod.GET)
    public String displaySightingDetails(HttpServletRequest request, Model model) {
        String sightingidParameter = request.getParameter("sightingid");
        int sightingid = Integer.parseInt(sightingidParameter);

        Sighting sighting = dao.getSightingByid(sightingid);
        int locationid = sighting.getLocationid();
        Location location = dao.getLocationByid(locationid);
        sighting.setLocation(location);
        
        List<SuperHero> superHeroList = dao.findSuperHerosForSighting(sighting);
        sighting.setSuperHeros(superHeroList);

        model.addAttribute("sighting", sighting);

        return "sightingDetails";
    }
    
    @RequestMapping(value = "/displayEditSightingForm", method = RequestMethod.GET)
    public String displayEditSightingForm(HttpServletRequest request, Model model) {
        String sightingidParameter = request.getParameter("sightingid");
        int sightingid = Integer.parseInt(sightingidParameter);
        Sighting sighting = dao.getSightingByid(sightingid);
        
        model.addAttribute("sighting", sighting);
        List<SuperHero> superHeroList = dao.getAllSuperHeros();
        model.addAttribute("superHeroList", superHeroList);
        List<Location> LocationList = dao.getAllLocations();
        model.addAttribute("locationList", LocationList);
        return "editSightingForm";
    }
    
    @RequestMapping(value = "/deleteSighting", method = RequestMethod.GET)
    public String deleteSighting(HttpServletRequest request) {
        String sightingidParameter = request.getParameter("sightingid");
        int sightingid = Integer.parseInt(sightingidParameter);
        dao.deleteSighting(sightingid);
        return "redirect:displaySightingsPage";
    }
    
    @RequestMapping(value = "/createSighting", method = RequestMethod.POST)//
    public String createSighting(HttpServletRequest request) {
        // grab the incoming values from the form and create a new Contact
        // object
        SuperHero superHero;
        Sighting sighting = new Sighting();
        sighting.setLocationid(Integer.parseInt(request.getParameter("location")));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String date = request.getParameter("sightingDate");
        LocalDate localDate = LocalDate.parse(date, formatter);
        sighting.setSightingDate(localDate);
        
        superHero = dao.getSuperHeroByid(Integer.parseInt(request.getParameter("superHero")));
        List<SuperHero> superHeroList = new ArrayList<SuperHero>();
        superHeroList.add(superHero);
        
        sighting.setSuperHeros(superHeroList);
        
        dao.addSighting(sighting);

        return "redirect:displaySightingsPage";//redirect request
    
    }    
    @RequestMapping(value = "/editSighting", method = RequestMethod.POST)
    public String editSighting(@Valid @ModelAttribute("sighting") Sighting sighting,
            HttpServletRequest request,

            BindingResult result) {

        if (result.hasErrors()) {
            return "editSightingForm";
        }
        
        SuperHero superHero;
        //Location location;
        //location = dao.getLocationByid(Integer.parseInt(request.getParameter("locationid")));
        superHero = dao.getSuperHeroByid(Integer.parseInt(request.getParameter("superHero")));
        List<SuperHero> superHeroList = new ArrayList<SuperHero>();
        superHeroList.add(superHero);

        sighting.setSuperHeros(superHeroList);
        //sighting.setLocation(location);//this is not necessary, but leaving there doesn't hurt either

        //organization = dao.getLocationByid(Integer.parseInt(request.getParameter("location")));
        //List<Organization> organizationList = new ArrayList<Organization>();
        //organizationList.add(organization);

        //superHero.setOrganizations(organizationList);
        
        dao.updateSighting(sighting); //this is not necessary, but leaving there doesn't hurt either

        return "redirect:displaySightingsPage";
    }
    
}