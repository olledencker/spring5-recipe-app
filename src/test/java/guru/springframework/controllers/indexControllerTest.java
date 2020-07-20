package guru.springframework.controllers;

import guru.springframework.domain.Recipe;
import guru.springframework.repositories.RecipeRepository;
import guru.springframework.services.RecipeService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@RunWith(SpringJUnit4ClassRunner.class)
public class indexControllerTest  {

    @Mock
    RecipeService recipeService;

    @Mock
    RecipeRepository recipeRepository;

    @Mock
    Model model;
    private indexController controller;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        controller = new indexController(recipeService);

    }
@Test
    public void testMockMVC() throws Exception {
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
        mockMvc.perform(MockMvcRequestBuilders.get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"));
      //  mockMvc.perform(get())

    }

    @Test
    public void testGetIndex() {

        //given
        Set<Recipe> recipeSet = new HashSet<Recipe>();
        recipeSet.add(new Recipe());
        Recipe recipe = new Recipe();
        recipe.setId(1L);;
        recipeSet.add(recipe);
        when(recipeService.getRecipes()).thenReturn(recipeSet);
        ArgumentCaptor<Set<Recipe>> argumentCaptor = ArgumentCaptor.forClass(Set.class);

        //when
       String index= controller.getindex(model);

       //then
       assertEquals(index, "index");
       verify(recipeService, times(1)).getRecipes();
       verify(model,times(1)).addAttribute(eq("recipies"), argumentCaptor.capture());
       Set<Recipe> setInController = argumentCaptor.getValue();
       assertEquals(setInController.size(),2);
    }
}