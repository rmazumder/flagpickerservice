package com.example.hcl.flagpicker.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Continent {

	@Id
	private String continentId;
	private String continent;
	private List<Country> countries;

	public String getContinent() {
		return continent;
	}

	public void setContinent(String continent) {
		this.continent = continent;
	}

	public List<Country> getCountries() {
		return countries;
	}

	public void setCountries(List<Country> countries) {
		this.countries = countries;
	}

	@Override
	public String toString() {
		return "Continent [name=" + continent + ", countries=" + countries + "]";
	}

	public Country getCountry(String countryName) {
		
		return countries.stream()
				.filter(x -> x.getName().equalsIgnoreCase(countryName))
				.findAny().orElse(null);

	}

	public String getContinentId() {
		return continentId;
	}

	public void setContinentId(String continentId) {
		this.continentId = continentId;
	}

}
