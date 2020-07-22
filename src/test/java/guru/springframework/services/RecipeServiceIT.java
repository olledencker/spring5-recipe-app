package guru.springframework.services;

import guru.springframework.commands.RecipeCommand;
import guru.springframework.converters.RecipeCommandToRecipe;
import guru.springframework.converters.RecipeToRecipeCommand;
import guru.springframework.domain.Recipe;
import guru.springframework.repositories.RecipeRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
@RunWith(SpringRunner.class)
@SpringBootTest
public class RecipeServiceIT {
    public static String NEW_DESCR = "a new descr";

    @Autowired
    RecipeService recipeService;
    @Autowired
    RecipeRepository recipeRepository;
    @Autowired
    RecipeToRecipeCommand recipeToRecipeCommand;
    @Autowired
    RecipeCommandToRecipe recipeCommandToRecipe;

    @Before
    public void setUp(){

    }
    @Test
    @Transactional
    public void testSaveDescription(){
        //given
        Set<Recipe> recipeSet = recipeService.getRecipes();

        Recipe recipe = recipeSet.iterator().next();
        RecipeCommand testRecipeCommand = recipeToRecipeCommand.convert(recipe);

        //when
        testRecipeCommand.setDescription(NEW_DESCR);
        RecipeCommand savedCommand =  recipeService.saveRecipecommand(testRecipeCommand);

        //then
        assertNotNull(savedCommand);
        assertEquals(savedCommand.getDescription(),NEW_DESCR);
        assertEquals(recipe.getId(), savedCommand.getId());
        assertEquals(savedCommand.getCategories().size(),recipe.getCategories().size());
        assertEquals(savedCommand.getIngredients().size(),recipe.getIngredients().size());

    }
}
