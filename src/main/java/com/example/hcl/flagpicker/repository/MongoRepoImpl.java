package com.example.hcl.flagpicker.repository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.bson.types.ObjectId;

import com.example.hcl.flagpicker.model.Continent;


public interface MongoRepoImpl extends MongoRepository<Continent, String> {
	  
}
