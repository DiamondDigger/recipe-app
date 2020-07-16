package guru.springframework.recipeapp.converters;

import guru.springframework.recipeapp.commands.IngredientCommand;
import guru.springframework.recipeapp.domain.Ingredient;

public class IngredientToIngredientCommand {
    public IngredientToIngredientCommand(UnitOfMeasureToUnitOfMeasureCommand unitOfMeasureToUnitOfMeasureCommand) {
    }

    public IngredientCommand convert(Ingredient ingredient) {
        IngredientCommand stub = new IngredientCommand();
        return stub;
    }
}
