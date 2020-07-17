package guru.springframework.recipeapp.converters;

import com.sun.istack.Nullable;
import guru.springframework.recipeapp.commands.RecipeCommand;
import guru.springframework.recipeapp.domain.Recipe;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class RecipeCommandToRecipe  implements Converter<RecipeCommand, Recipe> {
    private final  NotesCommandToNotes notesCommandToNotes;
    private final CategoryCommandToCategory categoryCommandToCategory;
    private final IngredientCommandToIngredient ingredientCommandToIngredient;

    public RecipeCommandToRecipe(NotesCommandToNotes notesCommandToNotes,
                                 CategoryCommandToCategory categoryCommandToCategory,
                                 IngredientCommandToIngredient ingredientCommandToIngredient) {

        this.notesCommandToNotes = notesCommandToNotes;
        this.categoryCommandToCategory = categoryCommandToCategory;
        this.ingredientCommandToIngredient = ingredientCommandToIngredient;
    }

    @Synchronized
    @Nullable
    @Override
    public Recipe convert(RecipeCommand recipeCommand) {
        if (recipeCommand != null) {
            Recipe recipe = new Recipe();
            recipe.setId(recipeCommand.getId());
            recipe.setDirections(recipeCommand.getDirections());
            recipe.setDescription(recipeCommand.getDescription());
            recipe.setCookTime(recipeCommand.getCookTime());
            recipe.setPrepTime(recipeCommand.getPrepTime());
            recipe.setUrl(recipeCommand.getUrl());
            recipe.setServings(recipeCommand.getServings());
            recipe.setSource(recipeCommand.getSource());
            recipe.setDifficulty(recipeCommand.getDifficulty());
            recipe.setNotes(notesCommandToNotes.convert(recipeCommand.getNotes()));

            if (recipeCommand.getIngredients() != null && recipeCommand.getIngredients().size() > 0) {
                recipeCommand.getIngredients()
                        .forEach(ingredientCommand -> recipe.getIngredients()
                                .add(ingredientCommandToIngredient.convert(ingredientCommand)));
            }

            if (recipeCommand.getCategories() != null && recipeCommand.getCategories().size() > 0) {
                recipeCommand.getCategories()
                        .forEach(categoryCommand -> recipe.getCategories()
                                .add(categoryCommandToCategory.convert(categoryCommand)));

            }
            return recipe;
        }

        return null;
    }
}
