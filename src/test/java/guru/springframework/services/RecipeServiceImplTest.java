package guru.springframework.services;

import guru.springframework.domain.Recipe;
import guru.springframework.repositories.RecipeRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class RecipeServiceImplTest {

    RecipeServiceImpl recipeService;

    @Mock
    RecipeRepository recipeRepository;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        recipeService = new RecipeServiceImpl(recipeRepository);
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
}