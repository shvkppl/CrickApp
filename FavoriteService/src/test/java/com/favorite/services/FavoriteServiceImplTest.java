package com.favorite.services;



import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.favorite.controllers.FavoriteController;
import com.favorite.models.FavToBeRemovedOrAdded;
import com.favorite.models.UserFavMap;
import com.favorite.repositories.UserFavRepo;
import com.favorite.services.FavoriteService;
import com.favorite.services.FavoriteServiceImpl;

import java.util.*;


public class FavoriteServiceImplTest {

	


    @MockBean
    private UserFavMap userFav;
    @MockBean
    private FavToBeRemovedOrAdded favToBeRemOrAdded;
    
    @Mock
    private UserFavRepo userFavRepo;
    
    @InjectMocks
    private FavoriteServiceImpl favServiceImpl;
    

    Optional<UserFavMap> options;
//    private List<Note> noteList;
    
    @Before
    public void setUp() {

        MockitoAnnotations.initMocks(this);
//        mockMvc = MockMvcBuilders.standaloneSetup(favController).build();
        userFav = new UserFavMap();
        userFav.setMailId("shiv@ibm.com");
        List<Long> favorites = new ArrayList<Long>();
        favorites.add(1L);
        favorites.add(2L);
        
        userFav.setFavorites(favorites);
        
        // Reminder

        favToBeRemOrAdded = new FavToBeRemovedOrAdded();
        favToBeRemOrAdded.setMailId("shiv@ibm.com");
        favToBeRemOrAdded.setMatchId(3L);
        
        options = Optional.of(userFav);
    }

    
    @Test
    public void addToFavSuccess() {
        when(userFavRepo.save((UserFavMap) any())).thenReturn(userFav);
        UserFavMap userfavmap = favServiceImpl.addToFav(favToBeRemOrAdded);
//        Assert.assertThat(userfavmap, instanceOf(UserFavMap.class));
        verify(userFavRepo, times(1)).save((UserFavMap) any());
    }
    
    @Test
    public void removeFromFavSuccess() {
    	when(userFavRepo.save((UserFavMap) any())).thenReturn(userFav);
        UserFavMap userfavmap = favServiceImpl.removeFromFav(favToBeRemOrAdded);
//        Assert.assertThat(userfavmap, instanceOf(UserFavMap.class));
        verify(userFavRepo, times(1)).findById((String) any());
    }
    
    @Test
    public void getFavsSuccess() {
    	when(userFavRepo.findById((String) any())).thenReturn(options);
        UserFavMap userfavmap = favServiceImpl.getFavs("shiv@ibm.com");
//        Assert.assertThat(userfavmap, instanceOf(UserFavMap.class));
        verify(userFavRepo, times(1)).findById((String) any());
    }
}
