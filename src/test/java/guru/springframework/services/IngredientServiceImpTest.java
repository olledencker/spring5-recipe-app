package guru.springframework.services;

import guru.springframework.commands.IngredientCommand;
import guru.springframework.converters.IngredientToIngredientCommand;
import guru.springframework.domain.Ingredient;
import guru.springframework.domain.Recipe;
import guru.springframework.repositories.RecipeRepository;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

public class IngredientServiceImpTest {
    IngredientService ingredientService;

    @Mock
    IngredientToIngredientCommand ingredientToIngredientCommand;

    @Mock
    RecipeRepository recipeRepository;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        ingredientService = new IngredientServiceImpl(recipeRepository,ingredientToIngredientCommand);

    }
    @Test
    void findRecipieAndIngredientHappyPath()   {
        //Given
        Recipe recipe = Recipe.builder().id(1L).build();
        recipe.addIngredient(Ingredient.builder().id(1L).build());
        recipe.addIngredient(Ingredient.builder().id(2L).build());
        recipe.addIngredient(Ingredient.builder().id(3L).build());

        Optional<Recipe> recipeOptional = Optional.of(recipe);
        when(recipeRepository.findById(anyLong())).thenReturn(recipeOptional);
        //then
        IngredientCommand ingredientCommand = ingredientService.findByRecipeIdandIngredientId(1L,3L);
        assertEquals(ingredientCommand.getId(),Long.valueOf(3L));
        assertEquals(ingredientCommand.getRecipeId(),Long.valueOf(1L));
        verify(recipeRepository,times(1)).findById(anyLong());


    }
}
