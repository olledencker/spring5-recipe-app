package guru.springframework.converters;

import guru.springframework.commands.RecipeCommand;
import guru.springframework.domain.Recipe;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class RecipeCommandToRecipe implements Converter<RecipeCommand, Recipe> {

    private final NotesCommandToNotes notesCommandToNotes;
    private final IngredientCommandToIngredient ingredientCommandToIngredient;
    private final CategoryCommandToCategory categoryCommandToCategory;
    public RecipeCommandToRecipe(NotesCommandToNotes notesCommandToNotes, IngredientCommandToIngredient ingredientCommandToIngredient, CategoryCommandToCategory categoryCommandToCategory) {
        this.notesCommandToNotes = notesCommandToNotes;
        this.ingredientCommandToIngredient = ingredientCommandToIngredient;
        this.categoryCommandToCategory = categoryCommandToCategory;
    }

    @Override
    @Synchronized
    @Nullable
    public Recipe convert(RecipeCommand recipeCommand) {
        if (recipeCommand== null){
            return null;
        }
        final Recipe recipe = Recipe.builder()
                .id(recipeCommand.getId())
                .cookTime(recipeCommand.getCookTime())
                .description(recipeCommand.getDescription())
                .difficulty(recipeCommand.getDifficulty())
                .directions(recipeCommand.getDirections())
                .notes(notesCommandToNotes.convert(recipeCommand.getNotes()))
                .prepTime(recipeCommand.getPrepTime())
                .serving(recipeCommand.getServing())
                .source(recipeCommand.getSource())
                .url(recipeCommand.getUrl()).build();

        recipeCommand.getIngredients().forEach(i->{recipe.getIngredients().add(ingredientCommandToIngredient.convert(i));});
        recipeCommand.getCategories().forEach(c->{recipe.getCategories().add(categoryCommandToCategory.convert(c));});

        return recipe;

    }
}
