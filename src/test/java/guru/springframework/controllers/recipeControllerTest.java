package guru.springframework.controllers;

import guru.springframework.commands.RecipeCommand;
import guru.springframework.domain.Recipe;
import guru.springframework.services.RecipeService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


public class recipeControllerTest {

    @Mock
    RecipeService recipeService;

    RecipeController recipeController;

    MockMvc mockMvc;
    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        recipeController = new RecipeController(recipeService);
         mockMvc= MockMvcBuilders.standaloneSetup(recipeController).build();
    }

    @Test
    public void testGetRecipe() throws Exception {
        //given
        Recipe recipe = new Recipe();
        recipe.setId(1L);
        when(recipeService.findById(anyLong())).thenReturn(recipe);


        //when
        mockMvc.perform(MockMvcRequestBuilders.get("/recipe/1/show"))
                .andExpect(status().isOk())
                .andExpect(view().name("recipe/show"))
                .andExpect(model().attributeExists("recipe"));
    }
    @Test
    public void testCreateNewRecipe() throws Exception {
        //given
        RecipeCommand newRecipe = RecipeCommand.builder().id(2L).build();
        when(recipeService.saveRecipecommand(any())).thenReturn(newRecipe);

         //when
        mockMvc.perform(MockMvcRequestBuilders.post("/recipe").contentType(MediaType.APPLICATION_FORM_URLENCODED).param("id","").param("description","some string"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/recipe/2/show/"));
        //then
    }
    @Test
    public void testUpdateForm() throws Exception {
        RecipeCommand rCom = RecipeCommand.builder().id(2L).build();
        when(recipeService.findCommandById(anyLong())).thenReturn(rCom);

        mockMvc.perform(MockMvcRequestBuilders.get("/recipe/2/update")).andExpect(status().isOk())
                .andExpect(view().name("recipe/recipeform")).andExpect(model().attributeExists("recipe"));
    }
    @Test
    public void getNewRecipeForm() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/recipe/new"))
                .andExpect(status().isOk()).andExpect(view().name("recipe/recipeform"))
                .andExpect(model().attributeExists("recipe"));
    }

    @Test
    public void testPostNewRecipieForm() throws Exception {
        RecipeCommand command = new RecipeCommand();
        command.setId(2L);
        when(recipeService.saveRecipecommand(any())).thenReturn(command);
        mockMvc.perform(MockMvcRequestBuilders.post("/recipe").contentType(MediaType.APPLICATION_FORM_URLENCODED).param("id","").param("description","en sträng av tecken"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/recipe/2/show/"));
    }
    @Test
    public void deleteAction() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/recipe/2/delete")).andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/"));
        verify(recipeService, times(1)).deleteById(anyLong());
    }
}