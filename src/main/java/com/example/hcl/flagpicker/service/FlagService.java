package com.example.hcl.flagpicker.service;


import java.util.List;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.hcl.flagpicker.model.Continent;
import com.example.hcl.flagpicker.repository.IFlagRepository;

@Service
public class FlagService {

    @Autowired
	IFlagRepository flagRepository;
    
	public List<Continent> getAllFlags() throws JSONException {
		return flagRepository.getAllFlags();
	}

	public Continent getFlagByContinent(String continentName) {
		return flagRepository.getContinentData(continentName);
	}

	public String getFlagByCountry(String countryName) {
		return flagRepository.getCountryFlag(countryName);
	}
	
	

}
