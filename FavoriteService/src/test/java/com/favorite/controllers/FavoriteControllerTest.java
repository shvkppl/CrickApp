package com.favorite.controllers;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.favorite.controllers.FavoriteController;
import com.favorite.models.FavToBeRemovedOrAdded;
import com.favorite.models.UserFavMap;
import com.favorite.services.FavoriteService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@WebMvcTest
public class FavoriteControllerTest {
	
	@Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserFavMap userFav;
    @MockBean
    private FavToBeRemovedOrAdded favToBeRemOrAdded;
    
    @MockBean
    private FavoriteService favService;
    
    @InjectMocks
    private FavoriteController favController;
    
//    private List<Note> noteList;
    
    @Before
    public void setUp() {

        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(favController).build();
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
        
    }

    
    @Test
    public void addFavSuccess() throws Exception {
        when(favService.addToFav(any())).thenReturn(userFav);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/favorites/addToFav").contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(favToBeRemOrAdded)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andDo(MockMvcResultHandlers.print());

    }

    
    
    
    @Test
    public void removeFromFavSuccess() throws Exception {
        when(favService.removeFromFav(any())).thenReturn(userFav);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/favorites/removeFromFav").contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(favToBeRemOrAdded)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());

    }
    
    
    @Test
    public void getFavsSuccess() throws Exception {
        when(favService.getFavs(any())).thenReturn(userFav);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/favorites/getFavs/shiv@ibm.com").contentType(MediaType.APPLICATION_JSON)
                .content("shiv@ibm.com"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());

    }
    
    
    
    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
}
