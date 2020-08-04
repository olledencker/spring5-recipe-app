package guru.springframework.services;


import guru.springframework.commands.IngredientCommand;
import guru.springframework.converters.IngredientToIngredientCommand;
import guru.springframework.domain.Recipe;
import guru.springframework.repositories.RecipeRepository;
import lombok.Synchronized;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class IngredientServiceImpl implements IngredientService{

    private final RecipeRepository recipeRepository;
    private  final IngredientToIngredientCommand ingredientToIngredientCommand;

    public IngredientServiceImpl(RecipeRepository recipeRepository, IngredientToIngredientCommand ingredientToIngredientCommand) {
        this.recipeRepository = recipeRepository;
        this.ingredientToIngredientCommand = ingredientToIngredientCommand;
    }
    @Synchronized
    @Override
    public IngredientCommand findByRecipeIdandIngredientId(Long recipeId, Long ingredientId) {
        Optional<Recipe> recipeOptional = recipeRepository.findById(recipeId);
        Recipe recipe = recipeOptional.get();
        log.debug("###############           Get ingredients¤¤¤¤¤¤¤¤¤¤¤");
        Optional<IngredientCommand> ingredientCommand = recipe.getIngredients().stream()
                .filter(ingr->ingr.getId().equals(ingredientId))
                .map(i-> ingredientToIngredientCommand.convert(i)).findFirst();
        if(!ingredientCommand.isPresent()){
            log.error("no ingredient found");
            return null;
        }
        return ingredientCommand.get();
    }
}
