package com.ibm.crickapp.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.ibm.crickapp.model.Match;
import com.ibm.crickapp.model.MatchCounter;

@Repository
public interface RecommendationRepository extends MongoRepository<MatchCounter,Long>{

	


}
