package com.favorite.services;

import org.springframework.stereotype.Service;

import com.favorite.models.FavToBeRemovedOrAdded;
import com.favorite.models.UserFavMap;

@Service
public interface FavoriteService {

	UserFavMap addToFav(FavToBeRemovedOrAdded favToBeAdded);
	UserFavMap removeFromFav(FavToBeRemovedOrAdded favToBeRemoved);
	UserFavMap getFavs(String mailid);
	
	
}
