package guru.springframework.recipeapp.converters;

import guru.springframework.recipeapp.commands.RecipeCommand;
import guru.springframework.recipeapp.domain.Recipe;

public class RecipeCommandToRecipe {
    public RecipeCommandToRecipe(NotesCommandToNotes notesCommandToNotes,
                                 CategoryCommandToCategory categoryCommandToCategory,
                                 IngredientCommandToIngredient ingredientCommandToIngredient) {

    }

    public Recipe convert(RecipeCommand o) {
        Recipe stub = new Recipe();
        return stub;
    }
}
