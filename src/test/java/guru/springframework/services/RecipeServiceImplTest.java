package guru.springframework.services;

import guru.springframework.converters.RecipeCommandToRecipe;
import guru.springframework.converters.RecipeToRecipeCommand;
import guru.springframework.domain.Recipe;
import guru.springframework.repositories.RecipeRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class RecipeServiceImplTest {

    RecipeServiceImpl recipeService;

    @Mock
    RecipeRepository recipeRepository;

    @Mock
    RecipeToRecipeCommand recipeToRecipeCommand;
    @Mock
    RecipeCommandToRecipe recipeCommandToRecipe;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        recipeService = new RecipeServiceImpl(recipeRepository, recipeCommandToRecipe, recipeToRecipeCommand);
    }

    @Test
    public void getRecipes() {
        //Before
        Set<Recipe> recipies = new HashSet<>();
        recipies.add(new Recipe());
        Recipe recipe = new Recipe();
        recipe.setId(1L);
        recipies.add(recipe);
        when(recipeRepository.findAll()).thenReturn(recipies);
        //When
        Set<Recipe> recipesTest = recipeService.getRecipes();
        assertEquals(recipesTest.size(),2);
        verify(recipeRepository, times(1)).findAll();

        //Then
    }

    @Test
    public void testGetRecipes() {
        //given
        Set<Recipe>recipes = new HashSet<>();
        recipes.add(new Recipe());
        when(recipeRepository.findAll()).thenReturn(recipes);
        //then
        Set<Recipe>returnedRecipies = recipeService.getRecipes();
        assertEquals(1, returnedRecipies.size());
        verify(recipeRepository).findAll();
        verify(recipeRepository,never()).findById(any());
    }

    @Test
    public void findById() {
        //given
        Recipe recipe = new Recipe();
        recipe.setId(1L);
        Optional<Recipe> recipeOptional = Optional.of(recipe);
        when(recipeRepository.findById(anyLong())).thenReturn(recipeOptional);
        //when
        Recipe recipeReturned = recipeService.findById(1L);
        assertNotNull(recipeReturned);
        verify(recipeRepository).findById(any());
        verify(recipeRepository, never()).findAll();
        //when
        //then
    }
}