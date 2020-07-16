package guru.springframework.recipeapp.converters;

import guru.springframework.recipeapp.commands.IngredientCommand;
import guru.springframework.recipeapp.domain.Ingredient;
import guru.springframework.recipeapp.domain.UnitOfMeasure;

import java.math.BigDecimal;

public class IngredientCommandToIngredient {
    public IngredientCommandToIngredient(UnitOfMeasureCommandToUnitOfMeasure unitOfMeasureCommandToUnitOfMeasure) {

    }

    public Ingredient convert(IngredientCommand ingredientCommand) {
        Ingredient stub = new Ingredient("", new BigDecimal(2), new UnitOfMeasure());
        return stub;
    }
}
