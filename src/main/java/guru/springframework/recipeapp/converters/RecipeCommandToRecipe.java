package guru.springframework.recipeapp.converters;

import guru.springframework.recipeapp.commands.RecipeCommand;
import guru.springframework.recipeapp.domain.Recipe;

public class RecipeCommandToRecipe {
    private final  NotesCommandToNotes notesCommandToNotes;
    private final CategoryCommandToCategory categoryCommandToCategory;
    private final IngredientCommandToIngredient ingredientCommandToIngredientw;

    public RecipeCommandToRecipe(NotesCommandToNotes notesCommandToNotes,
                                 CategoryCommandToCategory categoryCommandToCategory,
                                 IngredientCommandToIngredient ingredientCommandToIngredient) {

        this.notesCommandToNotes = notesCommandToNotes;
        this.categoryCommandToCategory = categoryCommandToCategory;
        this.ingredientCommandToIngredientw = ingredientCommandToIngredient;
    }

    public Recipe convert(RecipeCommand o) {
        Recipe stub = new Recipe();
        return stub;
    }
}
