package guru.springframework.recipeapp.services;

import guru.springframework.recipeapp.commands.IngredientCommand;
import guru.springframework.recipeapp.converters.IngredientToIngredientCommand;
import guru.springframework.recipeapp.domain.Recipe;
import guru.springframework.recipeapp.repositories.RecipeRepository;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

@Slf4j
public class IngredientServiceImpl implements IngredientService {

    final private RecipeService recipeService;
    final private RecipeRepository recipeRepository;
    final private IngredientToIngredientCommand ingredientToIngredientCommand;

    public IngredientServiceImpl(RecipeService recipeService, RecipeRepository recipeRepository,
                                 IngredientToIngredientCommand ingredientToIngredientCommand) {
        this.recipeService = recipeService;
        this.recipeRepository = recipeRepository;
        this.ingredientToIngredientCommand = ingredientToIngredientCommand;
    }

    @Override
    public IngredientCommand findByRecipeIdAndIngredientId(Long recipeId, Long ingredientId) {

        Optional<Recipe> optionalRecipe = recipeRepository.findById(recipeId);

        if (optionalRecipe == null) {
            //todo impl error handler
            log.debug("Recipe with such id not found: " + recipeId);
        }

        Recipe recipe = optionalRecipe.get();

        Optional<IngredientCommand> optionalIngredientCommand1 = recipe.getIngredients().stream()
                .filter(ingredient -> ingredient.getId().equals(ingredientId))
                .map(ingredientToIngredientCommand::convert).findFirst();

        if (optionalIngredientCommand1 == null) {
            //todo impl error handler
            log.debug("Ingredient with such id not found: " + ingredientId);
        }

        return optionalIngredientCommand1.get();
    }
}
