package guru.springframework.recipeapp.converters;

import guru.springframework.recipeapp.commands.CategoryCommand;
import guru.springframework.recipeapp.commands.IngredientCommand;
import guru.springframework.recipeapp.commands.NotesCommand;
import guru.springframework.recipeapp.commands.RecipeCommand;
import guru.springframework.recipeapp.domain.Difficulty;
import guru.springframework.recipeapp.domain.Recipe;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RecipeCommandToRecipeTest {
    public static Long ID = 1L;
    public static Integer SERVINGS = 2;
    public static Integer PREP_TIME = 10;
    public static Integer COOK_TIME = 14;
    public static String SOURCE = "source ";
    public static String URL = "url";
    public static String DIRECTIONS = "directions";
    public static String DESCRIPTION = "description";
    public static Difficulty DIFFICULTY = Difficulty.EASY;

    public static Long NOTES_ID = 1L;

    public static Long INGREDIENTS_ID_1 = 1L;
    public static Long INGREDIENTS_ID_2 = 2L;

    public static Long CATEGORY_ID_1 = 1L;
    public static Long CATEGORY_ID_2 = 2L;


    RecipeCommandToRecipe converter;

    @BeforeEach
    void setUp() {
        converter = new RecipeCommandToRecipe(
                new NotesCommandToNotes(),
                new CategoryCommandToCategory(),
                new IngredientCommandToIngredient(
                        new UnitOfMeasureCommandToUnitOfMeasure()
                )
        );
    }

    @Test
    void testNullParameter(){
        assertNull(converter.convert(null));
    }

    @Test
    void testEmptyObject(){
        assertNotNull(converter.convert(new RecipeCommand()));
    }

    @Test
    void testConvertTo(){
        //given
        RecipeCommand recipeCommand = new RecipeCommand();
        recipeCommand.setId(ID);
        recipeCommand.setServings(SERVINGS);
        recipeCommand.setPrepTime(PREP_TIME);
        recipeCommand.setCookTime(COOK_TIME);
        recipeCommand.setDescription(DESCRIPTION);
        recipeCommand.setDirections(DIRECTIONS);
        recipeCommand.setSource(SOURCE);
        recipeCommand.setDifficulty(DIFFICULTY);
        recipeCommand.setUrl(URL);

        NotesCommand notesCommand = new NotesCommand();
        notesCommand.setId(NOTES_ID);

        CategoryCommand categoryCommand_1 = new CategoryCommand();
        categoryCommand_1.setId(CATEGORY_ID_1);
        CategoryCommand categoryCommand_2 = new CategoryCommand();
        categoryCommand_2.setId(CATEGORY_ID_2);

        recipeCommand.getCategories().add(categoryCommand_1);
        recipeCommand.getCategories().add(categoryCommand_2);

        IngredientCommand ingredientCommand_1 = new IngredientCommand();
        ingredientCommand_1.setId(INGREDIENTS_ID_1);
        IngredientCommand ingredientCommand_2 = new IngredientCommand();
        ingredientCommand_2.setId(INGREDIENTS_ID_2);

        recipeCommand.getIngredients().add(ingredientCommand_1);
        recipeCommand.getIngredients().add(ingredientCommand_2);

        //when
        Recipe recipe = converter.convert(recipeCommand);

        assertNotNull(recipe);
        assertEquals(ID, recipe.getId());
        assertEquals(SERVINGS, recipe.getServings());
        assertEquals(SOURCE, recipe.getSource());
        assertEquals(PREP_TIME, recipe.getPrepTime());
        assertEquals(COOK_TIME, recipe.getCookTime());
        assertEquals(URL, recipe.getUrl());
        assertEquals(DESCRIPTION, recipe.getDescription());
        assertEquals(DIRECTIONS, recipe.getDirections());
        assertEquals(NOTES_ID, recipe.getNotes().getId());
        assertEquals(DIFFICULTY, recipe.getDifficulty());
        assertEquals(2, recipe.getCategories().size());
        assertEquals(2, recipe.getIngredients().size());
    }
}
