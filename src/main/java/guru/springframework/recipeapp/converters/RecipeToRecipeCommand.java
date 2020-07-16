package guru.springframework.recipeapp.converters;

import guru.springframework.recipeapp.commands.RecipeCommand;
import guru.springframework.recipeapp.domain.Recipe;

public class RecipeToRecipeCommand {
    public RecipeToRecipeCommand(NotesToNotesCommand notesToNotesCommand,
                                 CategoryToCategoryCommand categoryToCategoryCommand,
                                 IngredientToIngredientCommand ingredientToIngredientCommand) {

    }

    public RecipeCommand convert(Recipe o) {
        RecipeCommand stub = new RecipeCommand();
        return stub;
    }
}
