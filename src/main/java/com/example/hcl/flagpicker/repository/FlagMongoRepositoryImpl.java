package com.example.hcl.flagpicker.repository;

import java.util.List;

import org.json.JSONException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.example.hcl.flagpicker.exception.DataNotFoundException;
import com.example.hcl.flagpicker.model.Continent;
import com.example.hcl.flagpicker.model.Country;

@ConditionalOnProperty(name = "repository.selection", havingValue = "mongo", matchIfMissing = false)
@Repository
public class FlagMongoRepositoryImpl implements IFlagRepository {

	public static final Logger logger = LoggerFactory.getLogger(FlagMongoRepositoryImpl.class);

	@Autowired
	MongoTemplate mongoTemplate;

	@Override
	public List getAllFlags() throws JSONException {
		logger.debug("Mongo Repo: getAllFlags");
		return mongoTemplate.findAll(Continent.class);
	}

	@Override
	public Continent getContinentData(String continentName) {
		logger.debug("Mongo Repo: getContinentData :" + continentName);

		Query query = new Query();
		Criteria crit = Criteria.where("continent").regex(continentName, "i");

		query.addCriteria(crit);
		logger.debug("Query :" + query.toString());
		Continent continent = mongoTemplate.findOne(query, Continent.class);
		if(continent == null) {
			throw new DataNotFoundException("No data found for continent "+ continentName);
		}
		logger.debug("Continent fetched from MongoDB" + continent);
		return continent;
	}

	/*
	 * TODO : The code needs to be optimized with the below query
	 * 
	 * db.getCollection('continent').find({ 'countries.name' : 'China' }, {
	 * 'countries.name.$' : 1 });
	 * 
	 * 
	 */

	@Override
	public String getCountryFlag(String countryName) {
		logger.debug("Mongo Repo: getCountryFlag :" + countryName);
		Query query = new Query();

		Criteria crit = Criteria.where("countries.name").is(countryName);
		query.addCriteria(crit);
		logger.debug("Query: " + query);
		List<Country> countries = mongoTemplate.find(query, Country.class);
		logger.debug("Country fetched from Mongodb :" + countries);
		if(countries!=null & countries.size() == 0) {
			throw new DataNotFoundException("No data found for country "+ countryName);
		}
		for (Country country : countries) {
			if (country.getName().equalsIgnoreCase(countryName)) {
				return country.getFlag();
			}
		}
		logger.debug("No Country fetched from Mongodb :" + countryName);
		throw new DataNotFoundException("No data found for country "+ countryName);

	}

}
