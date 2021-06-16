package com.favorite.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.favorite.models.UserFavMap;


@Repository
public interface UserFavRepo extends MongoRepository<UserFavMap, String>{

}
