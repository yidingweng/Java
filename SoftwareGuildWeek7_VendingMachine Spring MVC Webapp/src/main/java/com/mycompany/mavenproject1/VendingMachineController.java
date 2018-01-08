package com.mycompany.mavenproject1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class VendingMachineController {
        
    
    //InventoryDao dao;
    InventoryService service;
    
    double money;
    double currentTotalInMachine;
    
    String userChoiceString;
    String messageInfo = "Welcome!";
    String changeInfo;
    
    @Inject
    public VendingMachineController(InventoryService service) {
        this.service = service;
    }
    
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String displaySnacksPage(Model model) throws Exception {
        HashMap<String, Snacks> snacksMap = service.requestInventoryInfo();
        List<Snacks> snacksList = new ArrayList<Snacks>(snacksMap.values());
        model.addAttribute("snacksList", snacksList);
        model.addAttribute("total", money/100); 
        model.addAttribute("itemChoice",userChoiceString);
        model.addAttribute("messageInfo",messageInfo);
        model.addAttribute("changeInfo",changeInfo);
        //snacks1.
        return "index";
    }
    
    @RequestMapping(value = "/takeMoney/{coinValue}", method = RequestMethod.POST)
    public String takeMoney(@PathVariable double coinValue, Model model){
        money = money + coinValue;
        //snacks1.
        return "redirect:/";
    }
    
    @RequestMapping(value = "/getUserChoice/{userChoice}", method = RequestMethod.POST)
    public String getUserChoice(@PathVariable String userChoice, Model model){
        userChoiceString = userChoice;
        return "redirect:/";
    }
    
    @RequestMapping(value = "/makePurchase", method = RequestMethod.POST)
    public String makePurchase(Model model) {
        
        service.authenticatePurchase(userChoiceString, money/100);
        messageInfo = service.getMessage();
        if (messageInfo == "Thank You!!!"){
            money = 0;
        }
        changeInfo = service.getChangeString();
        
        return "redirect:/";
    }
    
    @RequestMapping(value = "/changeReturn", method = RequestMethod.POST)
    public String changeReturn(Model model) {
        money = 0;
        userChoiceString = "";
        messageInfo = "Welcome!";
        changeInfo = "";
        
        return "redirect:/";
    }
}
