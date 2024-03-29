package com.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.demo.model.ServiceProvider;
import com.demo.model.UserRequirement;
import com.demo.service.ServiceProviderServ;
import com.demo.service.UserRequirementService;

@RequestMapping("/serviceprovider")
@RestController
@CrossOrigin
public class ServiceProviderController {
	@Autowired
	private ServiceProviderServ ServiceProviderObj;
	@Autowired
	private UserRequirementService userreq;

	@GetMapping("/getall")
	public ResponseEntity<List<ServiceProvider>> getallserviceprovider(){
		List<ServiceProvider> splist=ServiceProviderObj.getall();
		return ResponseEntity.ok(splist);	
	}
	@PostMapping("/register")
	public ResponseEntity<String> registerServiceProvider(@RequestBody ServiceProvider sp){
		System.out.println(sp);
		ServiceProviderObj.addServiceProvider(sp);
		return ResponseEntity.ok("data added successfully");
		
	}	
	@PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody ServiceProvider user) {
       ServiceProvider sp= ServiceProviderObj.validate(user.getUsername(),user.getPassword());
       if (sp != null) {
           return ResponseEntity.ok(sp);
       } else {
   
    	   return ResponseEntity.noContent().build();
       }
    }
	@GetMapping("/getallRequirements/{skill}")
	public ResponseEntity<List<UserRequirement>> getAllRequirements(@PathVariable String skill){
		List<UserRequirement> reqList=userreq.getall(skill);
		
		return ResponseEntity.ok(reqList);	
	}
	
}
