package guru.springframework.recipeapp.converters;

import com.sun.istack.Nullable;
import guru.springframework.recipeapp.commands.RecipeCommand;
import guru.springframework.recipeapp.domain.Recipe;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;

public class RecipeToRecipeCommand implements Converter<Recipe, RecipeCommand> {
    private final NotesToNotesCommand notesToNotesCommand;
    private final CategoryToCategoryCommand categoryToCategoryCommand;
    private final IngredientToIngredientCommand ingredientToIngredientCommand;

    public RecipeToRecipeCommand(NotesToNotesCommand notesToNotesCommand,
                                 CategoryToCategoryCommand categoryToCategoryCommand,
                                 IngredientToIngredientCommand ingredientToIngredientCommand) {

        this.notesToNotesCommand = notesToNotesCommand;
        this.categoryToCategoryCommand = categoryToCategoryCommand;
        this.ingredientToIngredientCommand = ingredientToIngredientCommand;
    }


    @Synchronized
    @Nullable
    @Override
    public RecipeCommand convert(Recipe recipe) {

        if (recipe != null) {
            RecipeCommand recipeCommand = new RecipeCommand();
            recipeCommand.setId(recipe.getId());
            recipeCommand.setPrepTime(recipe.getPrepTime());
            recipeCommand.setCookTime(recipe.getCookTime());
            recipeCommand.setDescription(recipe.getDescription());
            recipeCommand.setDirections(recipe.getDirections());
            recipeCommand.setDifficulty(recipe.getDifficulty());
            recipeCommand.setServings(recipe.getServings());
            recipeCommand.setSource(recipe.getSource());
            recipeCommand.setUrl(recipe.getUrl());
            recipeCommand.setNotes(notesToNotesCommand.convert(recipe.getNotes()));

            if (recipe.getCategories() != null && recipe.getCategories().size() > 0) {

                recipe.getCategories()
                        .forEach(category -> recipeCommand.getCategories()
                                .add(categoryToCategoryCommand.convert(category)));
            }

            if (recipe.getIngredients() != null && recipe.getIngredients().size() > 0) {

                recipe.getIngredients()
                        .forEach(ingredient -> recipeCommand.getIngredients()
                                .add(ingredientToIngredientCommand.convert(ingredient)));
            }

            return recipeCommand;
        }

        return null;
    }
}
