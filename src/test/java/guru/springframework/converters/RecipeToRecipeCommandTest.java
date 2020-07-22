package guru.springframework.converters;

import guru.springframework.commands.RecipeCommand;
import guru.springframework.domain.Category;
import guru.springframework.domain.Difficulty;
import guru.springframework.domain.Ingredient;
import guru.springframework.domain.Recipe;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class RecipeToRecipeCommandTest {
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
    RecipeToRecipeCommand converter;
    @Before
    public void setUp() throws Exception {
        converter = new RecipeToRecipeCommand(new CategoryToCategoryCommand(),new IngredientToIngredientCommand(new UnitOfMeasureToUnitOfMeasureCommand()),new NotesToNotesCommand());
    }
    @Test
    public void isNullable(){
        assertNull(converter.convert(null));
    }
    @Test
    public void notNull(){
        assertNotNull(converter.convert(Recipe.builder().id(RECIPE_ID).build()));
    }
    @Test
    public void convert() {
        //given
        Recipe recipe = Recipe.builder().id(RECIPE_ID).cookTime(COOK_TIME)
                .prepTime(PREP_TIME).description(DESCRIPTION).difficulty(DIFFICULTY)
                .serving(SERVINGS).source(SOURCE).url(URL).build();
        recipe.getCategories().add(Category.builder().id(CAT_ID_1).build());
        recipe.getCategories().add(Category.builder().id(CAT_ID2).build());
        recipe.getIngredients().add(Ingredient.builder().id(INGRED_ID_1).build() );
        recipe.getIngredients().add(Ingredient.builder().id(INGRED_ID_2).build());


        //when
        RecipeCommand rCom = converter.convert(recipe);
        //then
        assertNotNull(rCom);
        assertEquals(rCom.getId(), RECIPE_ID);
        assertEquals(rCom.getCookTime(),COOK_TIME);
        assertEquals(rCom.getPrepTime(),PREP_TIME);
        assertEquals(rCom.getDescription(),DESCRIPTION);
        assertEquals(rCom.getDifficulty(),DIFFICULTY);
        assertEquals(rCom.getServing(),SERVINGS);
        assertEquals(rCom.getSource(),SOURCE);
        assertEquals(rCom.getUrl(),URL);
        assertEquals(rCom.getIngredients().size(),2);
        assertEquals(rCom.getCategories().size(),2);
    }
}