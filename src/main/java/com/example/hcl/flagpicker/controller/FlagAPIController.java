package com.example.hcl.flagpicker.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;

import com.example.hcl.flagpicker.exception.DataNotFoundException;
import com.example.hcl.flagpicker.model.Continent;
import com.example.hcl.flagpicker.service.FlagService;


@RestController
@RequestMapping("/api")
public class FlagAPIController {


	public static final Logger logger = LoggerFactory.getLogger(FlagAPIController.class);

	@Autowired
	FlagService flagservice;


	/**
	 * Get all continent data
	 *
	 * @return JSON array of continents containing countries with name and flag
	 * @throws JSONException
	 */
	@GetMapping("/all")
	public List<Continent> getAllData() throws JSONException, DataNotFoundException{

		return flagservice.getAllFlags();
	}


	/**
	 * Returns array of countries for the given continent
	 * @param continentName
	 * @return
	 */
	@GetMapping("/continent/{continentName}")
	public  Continent getDataByContinent(@PathVariable String continentName) throws DataNotFoundException{

		return flagservice.getFlagByContinent(continentName);
	}


	/**
	 * Returns flag data for the provided country as string
	 * @param countryName
	 * @return
	 * @throws DataNotFoundException 
	 */
	@GetMapping("/country/{countryName}")
	public String getFlagByCountry(@PathVariable String countryName) throws DataNotFoundException {
		
		return flagservice.getFlagByCountry(countryName);
	}
	

	//TODO :  Separate it to make it common for all controller.
	@ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({RuntimeException.class, DataNotFoundException.class, JSONException.class})
    public Map<String, String> handleValidationExceptions(Exception ex) {
        Map<String, String> errors = new HashMap<>();
        errors.put("API-Exception", ex.getMessage());
        return errors;
    }
	
	


}
