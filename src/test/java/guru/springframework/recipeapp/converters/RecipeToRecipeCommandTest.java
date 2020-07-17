package guru.springframework.recipeapp.converters;

import guru.springframework.recipeapp.commands.RecipeCommand;
import guru.springframework.recipeapp.domain.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RecipeToRecipeCommandTest {
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


    RecipeToRecipeCommand converter;

    @BeforeEach
    void setUp() {
        converter = new RecipeToRecipeCommand(
                new NotesToNotesCommand(),
                new CategoryToCategoryCommand(),
                new IngredientToIngredientCommand(
                        new UnitOfMeasureToUnitOfMeasureCommand()
                )
        );
    }

    @Test
    void testNullParameter(){
        assertNull(converter.convert(null));
    }

    @Test
    void testEmptyObject(){
        assertNotNull(converter.convert(new Recipe()));
    }

    @Test
    void testConvertTo(){
        //given
        Recipe recipe = new Recipe();
        recipe.setId(ID);
        recipe.setServings(SERVINGS);
        recipe.setPrepTime(PREP_TIME);
        recipe.setCookTime(COOK_TIME);
        recipe.setDescription(DESCRIPTION);
        recipe.setDirections(DIRECTIONS);
        recipe.setSource(SOURCE);
        recipe.setDifficulty(DIFFICULTY);
        recipe.setUrl(URL);

        Notes notes = new Notes();
        notes.setId(NOTES_ID);

        recipe.setNotes(notes);

        Category category_1 = new Category();
        category_1.setId(CATEGORY_ID_1);
        Category category_2 = new Category();
        category_2.setId(CATEGORY_ID_2);

        recipe.getCategories().add(category_1);
        recipe.getCategories().add(category_2);

        Ingredient ingredient_1 = new Ingredient();
        ingredient_1.setId(INGREDIENTS_ID_1);
        Ingredient ingredient_2 = new Ingredient();
        ingredient_2.setId(INGREDIENTS_ID_2);

        recipe.getIngredients().add(ingredient_1);
        recipe.getIngredients().add(ingredient_2);

        //when
        RecipeCommand recipeCommand = converter.convert(recipe);

        assertNotNull(recipeCommand);
        assertEquals(ID, recipeCommand.getId());
        assertEquals(SERVINGS, recipeCommand.getServings());
        assertEquals(SOURCE, recipeCommand.getSource());
        assertEquals(PREP_TIME, recipeCommand.getPrepTime());
        assertEquals(COOK_TIME, recipeCommand.getCookTime());
        assertEquals(URL, recipeCommand.getUrl());
        assertEquals(DESCRIPTION, recipeCommand.getDescription());
        assertEquals(DIRECTIONS, recipeCommand.getDirections());
        assertEquals(NOTES_ID, recipeCommand.getNotes().getId());
        assertEquals(DIFFICULTY, recipeCommand.getDifficulty());
        assertEquals(2, recipeCommand.getCategories().size());
        assertEquals(2, recipeCommand.getIngredients().size());
    }
}
