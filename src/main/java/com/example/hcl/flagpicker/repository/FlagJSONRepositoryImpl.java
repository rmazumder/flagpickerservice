package com.example.hcl.flagpicker.repository;

import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;

import org.json.JSONException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Repository;

import com.example.hcl.flagpicker.exception.DataNotFoundException;
import com.example.hcl.flagpicker.model.Continent;
import com.example.hcl.flagpicker.model.Country;
import com.fasterxml.jackson.databind.ObjectMapper;

@ConditionalOnProperty(name = "repository.selection", havingValue = "json", matchIfMissing = true)
@Repository
public class FlagJSONRepositoryImpl implements IFlagRepository {

	public static final Logger logger = LoggerFactory.getLogger(FlagJSONRepositoryImpl.class);

	List<Continent> continents;

	public FlagJSONRepositoryImpl() throws IOException, JSONException {
		logger.debug("Initilializing JSON Repository");
		ObjectMapper mapper = new ObjectMapper();
		continents = mapper.readValue(new ClassPathResource("data/continents.json").getFile(),
				mapper.getTypeFactory().constructCollectionType(List.class, Continent.class));

	}

	public List<Continent> getAllFlags() throws JSONException {
		logger.debug("Repo getAll methods");
		return continents;
	}

	public Continent getContinentData(String continentName) {

		logger.debug("getContinentData method ::" + continentName);
		Continent continent;
		try {
			continent = continents.stream()
					.filter(x -> x.getContinent().equalsIgnoreCase(continentName))
					.findAny()
					.get();
			logger.debug("continent found" + continent);

		} catch (NoSuchElementException e) {
			logger.debug("No continent found with name " + continentName);
			throw new DataNotFoundException("No data found for continent " + continentName, e);
		}

		return continent;
	}

	public String getCountryFlag(String countryName) {

		logger.debug("getCountryFlag:" + countryName);
	
		//continents.stream().filter(x->x.getCountries().stream().anyMatch(c->c.getName().equalsIgnoreCase(countryName)))
		
		for (Continent continent : continents) {
			Country country = continent.getCountry(countryName);
			if (country != null) {
				logger.debug("country found" + country);
				return country.getFlag();
			}
		}
		logger.debug("No country found" + countryName);
	    throw new DataNotFoundException("No data found for country "+ countryName);
	}

}
