package guru.springframework.converters;

import guru.springframework.commands.IngredientCommand;
import guru.springframework.domain.Ingredient;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class IngredientCommandToIngredient implements Converter<IngredientCommand, Ingredient> {
    private final UnitOfMeasureCommandToUnitOfMeasure unitOfMeasureCommandToUnitOfMeasure;

    public IngredientCommandToIngredient(UnitOfMeasureCommandToUnitOfMeasure unitOfMeasureCommandToUnitOfMeasure) {
        this.unitOfMeasureCommandToUnitOfMeasure = unitOfMeasureCommandToUnitOfMeasure;
    }

    @Nullable
    @Synchronized
    @Override
    public Ingredient convert(IngredientCommand ingredientCommand) {
        if(ingredientCommand==null){
            return null;
        }
        Ingredient ingredient = new Ingredient();
        ingredient.setId(ingredientCommand.getId());
       ingredient.setUom(unitOfMeasureCommandToUnitOfMeasure.convert(ingredientCommand.getUom()));
       ingredient.setAmount(ingredientCommand.getAmount());




       ingredient.setDescription(ingredientCommand.getDescription());
        return ingredient;
    }
}
