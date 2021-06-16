package com.favorite.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.favorite.models.FavToBeRemovedOrAdded;
import com.favorite.models.UserFavMap;
import com.favorite.repositories.UserFavRepo;

@Service
public class FavoriteServiceImpl implements FavoriteService{

	@Autowired
	UserFavRepo favRepo;
	
	@Override
	public UserFavMap addToFav(FavToBeRemovedOrAdded favToBeAdded) {
		Optional<UserFavMap> favOption = favRepo.findById(favToBeAdded.getMailId());
//		System.out.println(favOption.get().getMailId());
//		System.out.println(favOption.get().getFavorites());
		
		if(favOption.isPresent()) {
			List<Long> favorites = favOption.get().getFavorites();
			for(long matchid: favorites) {
				if(matchid == favToBeAdded.getMatchId())
					return favOption.get();
			}
			favorites.add(favToBeAdded.getMatchId());
			
			UserFavMap userfavmap = new UserFavMap(favToBeAdded.getMailId());
//			userfavmap.setMailId(favToBeAdded.getMailid());
			userfavmap.setFavorites(favorites);
			return favRepo.save(userfavmap);
		}
		else {
			ArrayList<Long> favorites = new ArrayList<Long>();
			favorites.add(favToBeAdded.getMatchId());
			UserFavMap userfavmap = new UserFavMap(favToBeAdded.getMailId());
//			userfavmap.setMailId(favToBeAdded.getMailid());
			userfavmap.setFavorites(favorites);
			return favRepo.save(userfavmap);
		}
		
	}

	@Override
	public UserFavMap removeFromFav(FavToBeRemovedOrAdded favToBeRemoved) {
	Optional<UserFavMap> favOption = favRepo.findById(favToBeRemoved.getMailId());
			
			if(favOption.isPresent()) {
				List<Long> favorites = favOption.get().getFavorites();
				favorites.remove(favToBeRemoved.getMatchId());
				UserFavMap userfavmap = new UserFavMap(favToBeRemoved.getMailId());
//				userfavmap.setMailId(favToBeRemoved.getMailid());
				userfavmap.setFavorites(favorites);
				return favRepo.save(userfavmap);
				
			}
			else {
				return null;
			}
			
			
		}
	

	@Override
	public UserFavMap getFavs(String mailid) {
		System.out.println("inside get fav service");
		Optional<UserFavMap> favOption = favRepo.findById(mailid);
		if(favOption.isPresent()) {
			System.out.println("favoption is presenet");
			return favOption.get();
		}
		System.out.println("Should return empty object");
		return new UserFavMap(mailid);
	}

}
