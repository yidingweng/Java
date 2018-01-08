/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.softwareguildweek10_superhero;

import com.mycompany.softwareguildweek10_superhero.dao.SuperHeroWorldDao;
import com.mycompany.softwareguildweek10_superhero.model.Location;
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
public class LocationController {
    SuperHeroWorldDao dao;
    
    @Inject
    public LocationController(SuperHeroWorldDao dao) {
        this.dao = dao;
    }
    
    @RequestMapping(value = "/displayLocationsPage", method = RequestMethod.GET)
    public String displayLocationsPage(Model model){
        List<Location> locationList = dao.getAllLocations();
        model.addAttribute("locationList", locationList);
        
        return "locations";
    }
    
    @RequestMapping(value = "/displayLocationDetails", method = RequestMethod.GET)
    public String displayLocationDetails(HttpServletRequest request, Model model) {
        String locationidParameter = request.getParameter("locationid");
        int locationid = Integer.parseInt(locationidParameter);

        Location location = dao.getLocationByid(locationid);

        model.addAttribute("location", location);

        return "locationDetails";
    }
    
    @RequestMapping(value = "/displayEditLocationForm", method = RequestMethod.GET)
    public String displayEditLocationForm(HttpServletRequest request, Model model) {
        String locationidParameter = request.getParameter("locationid");
        int locationid = Integer.parseInt(locationidParameter);
        Location location = dao.getLocationByid(locationid);
        
        model.addAttribute("location", location);
        
        return "editLocationForm";
    }
    
    @RequestMapping(value = "/deleteLocation", method = RequestMethod.GET)
    public String deleteLocation(HttpServletRequest request) {
        String locationidParameter = request.getParameter("locationid");
        int locationid = Integer.parseInt(locationidParameter);
        dao.deleteLocation(locationid);
        return "redirect:displayLocationsPage";
    }
    
    @RequestMapping(value = "/createLocation", method = RequestMethod.POST)//
    public String createLocation(HttpServletRequest request) {
        // grab the incoming values from the form and create a new Contact
        // object
        Location location = new Location();
        location.setLocationName(request.getParameter("locationName"));
        location.setDescription(request.getParameter("description"));
        location.setAddressInfo(request.getParameter("addressInfo"));
        location.setLatitude(Double.parseDouble(request.getParameter("latitude")));
        location.setLongitude(Double.parseDouble(request.getParameter("longitude")));
        // persist the new Contact
        dao.addLocation(location);

        // we don't want to forward to a View component - we want to
        // redirect to the endpoint that displays the Contacts Page
        // so it can display the new Contact in the table.
        return "redirect:displayLocationsPage";//redirect request
    }
    
    @RequestMapping(value = "/editLocation", method = RequestMethod.POST)
    public String editLocation(@Valid @ModelAttribute("location") Location location, BindingResult result) {

        if (result.hasErrors()) {
            return "editLocationForm";
        }

        dao.updateLocation(location);

        return "redirect:displayLocationsPage";
    }
}
