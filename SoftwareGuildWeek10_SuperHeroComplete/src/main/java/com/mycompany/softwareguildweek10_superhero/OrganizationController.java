/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.softwareguildweek10_superhero;

import com.mycompany.softwareguildweek10_superhero.dao.SuperHeroWorldDao;
import com.mycompany.softwareguildweek10_superhero.model.Organization;
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
public class OrganizationController {
    SuperHeroWorldDao dao;
    
    @Inject
    public OrganizationController(SuperHeroWorldDao dao) {
        this.dao = dao;
    }
    
    @RequestMapping(value = "/displayOrganizationsPage", method = RequestMethod.GET)
    public String displayOrganizationsPage(Model model){
        List<Organization> organizationList = dao.getAllOrganizations();
        model.addAttribute("organizationList", organizationList);
        
        return "organizations";
    }
    
    @RequestMapping(value = "/displayOrganizationDetails", method = RequestMethod.GET)
    public String displayOrganizationDetails(HttpServletRequest request, Model model) {
        String organizationidParameter = request.getParameter("organizationid");
        int organizationid = Integer.parseInt(organizationidParameter);

        Organization organization = dao.getOrganizationByid(organizationid);

        model.addAttribute("organization", organization);

        return "organizationDetails";
    }
    
    @RequestMapping(value = "/displayEditOrganizationForm", method = RequestMethod.GET)
    public String displayEditOrganizationForm(HttpServletRequest request, Model model) {
        String organizationidParameter = request.getParameter("organizationid");
        int organizationid = Integer.parseInt(organizationidParameter);
        Organization organization = dao.getOrganizationByid(organizationid);
        
        model.addAttribute("organization", organization);
        
        return "editOrganizationForm";
    }
    
    @RequestMapping(value = "/deleteOrganization", method = RequestMethod.GET)
    public String deleteOrganization(HttpServletRequest request) {
        String organizationidParameter = request.getParameter("organizationid");
        int organizationid = Integer.parseInt(organizationidParameter);
        dao.deleteOrganization(organizationid);
        return "redirect:displayOrganizationsPage";
    }
    
    @RequestMapping(value = "/createOrganization", method = RequestMethod.POST)//
    public String createOrganization(HttpServletRequest request) {
        // grab the incoming values from the form and create a new Contact
        // object
        Organization organization = new Organization();
        organization.setOrganizationName(request.getParameter("organizationName"));
        organization.setDescription(request.getParameter("description"));
        organization.setAddress(request.getParameter("address"));
        organization.setEmail(request.getParameter("email"));
        
        // persist the new Contact
        dao.addOrganization(organization);

        // we don't want to forward to a View component - we want to
        // redirect to the endpoint that displays the Contacts Page
        // so it can display the new Contact in the table.
        return "redirect:displayOrganizationsPage";//redirect request
    }
    
    @RequestMapping(value = "/editOrganization", method = RequestMethod.POST)
    public String editOrganization(@Valid @ModelAttribute("organization") Organization organization, BindingResult result) {

        if (result.hasErrors()) {
            return "editOrganizationForm";
        }

        dao.updateOrganization(organization);

        return "redirect:displayOrganizationsPage";
    }
}
