package guru.springframework.recipeapp.services;

import guru.springframework.recipeapp.commands.IngredientCommand;
import guru.springframework.recipeapp.converters.IngredientCommandToIngredient;
import guru.springframework.recipeapp.converters.IngredientToIngredientCommand;
import guru.springframework.recipeapp.converters.UnitOfMeasureCommandToUnitOfMeasure;
import guru.springframework.recipeapp.converters.UnitOfMeasureToUnitOfMeasureCommand;
import guru.springframework.recipeapp.domain.Ingredient;
import guru.springframework.recipeapp.domain.Recipe;
import guru.springframework.recipeapp.repositories.RecipeRepository;
import guru.springframework.recipeapp.repositories.UnitOfMeasureRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

class IngredientServiceImplTest {

    private final IngredientToIngredientCommand ingredientToIngredientCommand;

    @Mock
    RecipeRepository recipeRepository;

    @Mock
    UnitOfMeasureRepository unitOfMeasureRepository;

    IngredientService ingredientService;

    IngredientCommandToIngredient ingredientCommandToIngredient;


    //init converters
    public IngredientServiceImplTest() {
        this.ingredientToIngredientCommand = new IngredientToIngredientCommand(new UnitOfMeasureToUnitOfMeasureCommand());
        this.ingredientCommandToIngredient = new IngredientCommandToIngredient(new UnitOfMeasureCommandToUnitOfMeasure());
    }

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);

        ingredientService = new IngredientServiceImpl(recipeRepository, ingredientToIngredientCommand,
                ingredientCommandToIngredient, unitOfMeasureRepository);
    }

    @Test
    void testFindByRecipeIdAndIngredientId() {
        //given

        Recipe recipe = new Recipe();
        recipe.setId(1L);

        Ingredient ingredientOne = new Ingredient();
        ingredientOne.setId(1L);

        Ingredient ingredientTwo = new Ingredient();
        ingredientTwo.setId(2L);

        Ingredient ingredientThree = new Ingredient();
        ingredientThree.setId(3L);


        recipe.addIngredient(ingredientOne);
        recipe.addIngredient(ingredientTwo);
        recipe.addIngredient(ingredientThree);

        Optional<Recipe> recipeOptional = Optional.of(recipe);

        //when
        when(recipeRepository.findById(anyLong())).thenReturn(recipeOptional);

        IngredientCommand ingredientCommand = ingredientService
                .findByRecipeIdAndIngredientId(1L, 3L);

        assertNotNull(ingredientThree);
        assertEquals(3L, ingredientCommand.getId());
        assertEquals(1L, ingredientCommand.getRecipeId());

    }

    @Test
    void saveRecipeCommand(){
        //given
        IngredientCommand ingredientCommand = new IngredientCommand();
        ingredientCommand.setId(3L);

        Optional<Recipe> recipeOptional = Optional.of(new Recipe());

        Recipe savedRecipe = new Recipe();
        savedRecipe.setId(1L);
        savedRecipe.addIngredient(new Ingredient());
        savedRecipe.getIngredients().iterator().next().setId(3L);

        when(recipeRepository.findById(anyLong())).thenReturn(recipeOptional);
        when(recipeRepository.save(any())).thenReturn(savedRecipe);

        //when
        IngredientCommand savedCommand = ingredientService.saveIngredientCommand(ingredientCommand);

        //then
        assertEquals(3L, savedCommand.getId());
        verify(recipeRepository,times(1)).findById(anyLong());
        verify(recipeRepository,times(1)).save(any(Recipe.class));

    }
}