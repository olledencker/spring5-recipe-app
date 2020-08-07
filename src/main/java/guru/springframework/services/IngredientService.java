package guru.springframework.services;

import guru.springframework.commands.IngredientCommand;

public interface IngredientService {
    IngredientCommand findByRecipeIdandIngredientId(Long recipeId, Long ingredientId);
    IngredientCommand saveIngredient(IngredientCommand ingredientCommand);
    void deleteIngredient(Long recipeId, Long ingredientID);
}
