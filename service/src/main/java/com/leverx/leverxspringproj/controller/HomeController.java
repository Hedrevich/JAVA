package com.leverx.leverxspringproj.controller;


import com.leverx.leverxspringproj.service.CloudService;
import com.leverx.leverxspringproj.service.HomeControllerService;
import com.leverx.leverxspringproj.service.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.sap.cloud.sdk.s4hana.connectivity.exception.AccessDeniedException;


@Controller
public class HomeController {


	@Autowired
	private CloudService cloudService;

	@Autowired
	private SecurityService securityService;

	@Autowired
	private HomeControllerService homeControllerService;

	@RequestMapping(value="/", method=RequestMethod.GET)
		public String getHome(Model model) {
		model = homeControllerService.setAttributes(model,cloudService);
		return "index";
	}

	@RequestMapping(value="/jwt", method=RequestMethod.GET)
		public String getJWT(Model model) {
		model = homeControllerService.parseJWT(model,cloudService);
		return "jwt";
	}

	@RequestMapping(value="/scope", method=RequestMethod.GET)
	public String checkScope() throws AccessDeniedException {
		securityService.userHasAuthorization("Display");
		return "scope";
	}
}
