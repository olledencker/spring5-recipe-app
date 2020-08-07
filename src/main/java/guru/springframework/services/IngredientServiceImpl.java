package guru.springframework.services;


import guru.springframework.commands.IngredientCommand;
import guru.springframework.converters.IngredientCommandToIngredient;
import guru.springframework.converters.IngredientToIngredientCommand;
import guru.springframework.domain.Ingredient;
import guru.springframework.domain.Recipe;
import guru.springframework.repositories.RecipeRepository;
import guru.springframework.repositories.UnitOfMeasureRepository;
import lombok.Synchronized;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Slf4j
public class IngredientServiceImpl implements IngredientService{


    private final RecipeRepository recipeRepository;
    private  final IngredientToIngredientCommand ingredientToIngredientCommand;
    private final IngredientCommandToIngredient ingredientCommandToIngredient;
    private final UnitOfMeasureRepository unitOfMeasureRepository;

    public IngredientServiceImpl(RecipeRepository recipeRepository, IngredientToIngredientCommand ingredientToIngredientCommand, IngredientCommandToIngredient ingredientCommandToIngredient, UnitOfMeasureRepository unitOfMeasureRepository) {
        this.recipeRepository = recipeRepository;
        this.ingredientToIngredientCommand = ingredientToIngredientCommand;
        this.ingredientCommandToIngredient = ingredientCommandToIngredient;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
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
    @Override
    public void deleteIngredient(Long recipeId, Long ingredientID) {
        Optional<Recipe> recipeOpt = recipeRepository.findById(recipeId);
        if(recipeOpt.isPresent()){
            Recipe recipe = recipeOpt.get();
            Optional<Ingredient> ingredientOptional =recipe.getIngredients().stream()
                    .filter(i->i.getId().equals(ingredientID)).findFirst();
            if(ingredientOptional.isPresent()){
                Ingredient ingredientToDelete= ingredientOptional.get();
                ingredientToDelete.setRecipe(null);
                recipe.getIngredients().remove(ingredientToDelete);

                Recipe savedRecepie = recipeRepository.save(recipe);
          //      log.debug(savedRecepie.toString());
            }
        }
    }
    @Override
    @Transactional
    public IngredientCommand saveIngredient(IngredientCommand ingredientCommand) {
        Optional<Recipe> recipeOptional = recipeRepository.findById(ingredientCommand.getRecipeId());
        if(!recipeOptional.isPresent()){
            log.error("Recepie for "+ ingredientCommand.getRecipeId() +" is missing");
            return new IngredientCommand();
        }else{
            Recipe recipe = recipeOptional.get();
            Optional<Ingredient> optionalIngredient = recipe.getIngredients().stream().filter(i->i.getId().equals(ingredientCommand.getId())).findFirst();
            if(optionalIngredient.isPresent()){
                Ingredient ingredient = optionalIngredient.get();
                ingredient.setDescription(ingredientCommand.getDescription());
                ingredient.setAmount(ingredientCommand.getAmount());
                ingredient.setUom(unitOfMeasureRepository.findById(ingredientCommand.getUom().getId()).orElseThrow(()->new RuntimeException("UOM Doen not exists")));
            }else{
                recipe.addIngredient(ingredientCommandToIngredient.convert(ingredientCommand));
            }
            Recipe savedRecipe = recipeRepository.save(recipe);

            Optional<Ingredient> ingredientOptional =  savedRecipe.getIngredients().stream().filter(ri->ri.getId().equals(ingredientCommand.getId())).findFirst();
            if(!ingredientOptional.isPresent()){
                ingredientOptional = savedRecipe.getIngredients().stream().filter(i->i.getAmount().equals(ingredientCommand.getAmount()))
                        .filter(i->i.getDescription().equals(ingredientCommand.getDescription()))
                        .filter(i->i.getUom().getId().equals(ingredientCommand.getUom().getId())).findFirst();
            }
            return ingredientToIngredientCommand.convert(ingredientOptional.get() );
        }
    }

}
