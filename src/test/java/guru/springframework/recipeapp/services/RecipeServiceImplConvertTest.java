package guru.springframework.recipeapp.services;

import guru.springframework.recipeapp.commands.RecipeCommand;
import guru.springframework.recipeapp.converters.RecipeCommandToRecipe;
import guru.springframework.recipeapp.converters.RecipeToRecipeCommand;
import guru.springframework.recipeapp.domain.Recipe;
import guru.springframework.recipeapp.repositories.RecipeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class RecipeServiceImplConvertTest {
    public static final String DESCRIPTION_NEW = "new description";
    private static final String DESCRIPTION_OLD = "old description";

    @Autowired
    RecipeServiceImpl recipeService;

    @Autowired
    IngredientService ingredientService;

    @Autowired
    RecipeCommandToRecipe recipeCommandToRecipe;

    @Autowired
    RecipeToRecipeCommand recipeToRecipeCommand;

    @Autowired
    RecipeRepository recipeRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);

        recipeService = new RecipeServiceImpl(recipeRepository,
                recipeCommandToRecipe, recipeToRecipeCommand);
    }

    @Transactional
    @Test
    void saveRecipeCommand() {
        List<Recipe> recipes = new ArrayList<>(2);

        Recipe recipeForRepo = new Recipe();
        recipeForRepo.setId(2L);
        recipeForRepo.setDescription(DESCRIPTION_OLD);

        recipes.add(recipeForRepo);
        recipeRepository.saveAll(recipes);

        Iterable<Recipe> iterable = recipeRepository.findAll();
        Recipe recipeFromRepo = iterable.iterator().next();

        RecipeCommand unsavedCommand = recipeToRecipeCommand.convert(recipeFromRepo);
        unsavedCommand.setDescription(DESCRIPTION_NEW);

        RecipeCommand savedCommand = recipeService.saveRecipeCommand(unsavedCommand);

        assertEquals(DESCRIPTION_NEW, savedCommand.getDescription());
        assertEquals(recipeFromRepo.getId(), savedCommand.getId());
        assertEquals(recipeFromRepo.getCookTime(), savedCommand.getCookTime());
        assertEquals(recipeFromRepo.getCategories().size(), savedCommand.getCategories().size());
        assertEquals(recipeFromRepo.getIngredients().size(), savedCommand.getIngredients().size());
    }
}