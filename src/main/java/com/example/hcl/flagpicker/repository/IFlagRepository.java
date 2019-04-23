package com.example.hcl.flagpicker.repository;

import java.util.List;

import org.json.JSONException;

import com.example.hcl.flagpicker.model.Continent;

public interface IFlagRepository {

	public List getAllFlags() throws JSONException;

	public Continent getContinentData(String continentName) ;

	public String getCountryFlag(String countryName);
	
}