package guru.springframework.converters;

import guru.springframework.commands.CategoryCommand;
import guru.springframework.commands.IngredientCommand;
import guru.springframework.commands.NotesCommand;
import guru.springframework.commands.RecipeCommand;
import guru.springframework.domain.Difficulty;
import guru.springframework.domain.Recipe;
import org.junit.Before;
import org.junit.Test;

import java.util.Set;

import static org.junit.Assert.*;

public class RecipeCommandToRecipeTest {
    public static final Long RECIPE_ID = 1L;
    public static final Integer COOK_TIME = Integer.valueOf("5");
    public static final Integer PREP_TIME = Integer.valueOf("7");
    public static final String DESCRIPTION = "My Recipe";
    public static final String DIRECTIONS = "Directions";
    public static final Difficulty DIFFICULTY = Difficulty.EASY;
    public static final Integer SERVINGS = Integer.valueOf("3");
    public static final String SOURCE = "Source";
    public static final String URL = "Some URL";
    public static final Long CAT_ID_1 = 1L;
    public static final Long CAT_ID2 = 2L;
    public static final Long INGRED_ID_1 = 3L;
    public static final Long INGRED_ID_2 = 4L;
    public static final Long NOTES_ID = 9L;

    RecipeCommandToRecipe converter;
    @Before
    public void setUp() throws Exception {
        converter = new RecipeCommandToRecipe(new NotesCommandToNotes(),new IngredientCommandToIngredient(new UnitOfMeasureCommandToUnitOfMeasure()), new CategoryCommandToCategory() );

    }
    @Test
    public void testNullObject() throws Exception {
        assertNull(converter.convert(null));
    }

    @Test
    public void testEmptyObject() throws Exception {
        assertNotNull(converter.convert(new RecipeCommand()));
    }
    @Test
    public void convert() {
        //Given
        RecipeCommand recipeCommand = RecipeCommand.builder()
                .id(RECIPE_ID)
                .cookTime(COOK_TIME)
                .prepTime(PREP_TIME)
                .description(DESCRIPTION)
                .directions(DIRECTIONS)
                .difficulty(DIFFICULTY)
                .serving(SERVINGS)
                .notes(NotesCommand.builder().id(NOTES_ID).build())
                .source(SOURCE).build();
        Set<IngredientCommand> ingredientSet =  recipeCommand.getIngredients();
        recipeCommand.getIngredients().add(IngredientCommand.builder().id(INGRED_ID_1).build());
        recipeCommand.getIngredients().add(IngredientCommand.builder().id(INGRED_ID_2).build());
        recipeCommand.getCategories().add(CategoryCommand.builder().id(CAT_ID_1).build());
        recipeCommand.getCategories().add(CategoryCommand.builder().id(CAT_ID2).build());
        //when
        Recipe recipe = converter.convert(recipeCommand);
        //then
        assertNotNull(recipe);
        assertEquals(recipe.getId(), RECIPE_ID);
        assertEquals(recipe.getCookTime(), COOK_TIME);
        assertEquals(recipe.getPrepTime(),PREP_TIME);
        assertEquals(recipe.getDescription(),DESCRIPTION);
        assertEquals(recipe.getDirections(),DIRECTIONS);
        assertEquals(recipe.getDifficulty(),DIFFICULTY);
        assertEquals(recipe.getServing(),SERVINGS);
        assertEquals(recipe.getNotes().getId(),NOTES_ID);
        assertEquals(recipe.getSource(),SOURCE);
        assertEquals(recipe.getIngredients().size(),2);
        assertEquals(recipe.getCategories().size(),2);

    }
}