package com.leverx.leverxspringproj.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping; 
import org.springframework.web.bind.annotation.RequestMethod;
import com.sap.cloud.sdk.s4hana.connectivity.exception.AccessDeniedException;

import com.google.gson.JsonElement;
import com.leverx.leverxspringproj.domain.Destination;
import com.leverx.leverxspringproj.service.CloudService;
import com.leverx.leverxspringproj.service.SecurityService;

@Controller public class HomeController {    
	@Autowired
	private CloudService cloudService; 
	
	@Autowired
	private SecurityService securityService; 
	
	@RequestMapping(value="/", method=RequestMethod.GET)  
	public String getHome(Model model) {   
		Map<String, JsonElement> vcap = cloudService.getNameSpace();
		JsonElement vc = vcap.get("space_name");
		model.addAttribute("VCAP",vc.toString());
		String appName = cloudService.getApplicationName();   
		model.addAttribute("appName", appName); 
		List<Destination> destinations = cloudService.getDestinations();
		model.addAttribute("destinations", destinations);
		return "index"; 
	} 
	
	@RequestMapping(value="/scope", method=RequestMethod.GET)
	public String checkScope() throws AccessDeniedException  {
		securityService.userHasAuthorization("Display");
		return "scope";
	}
} 
