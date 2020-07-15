package guru.springframework.recipeapp.commands;

import guru.springframework.recipeapp.domain.Difficulty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
public class RecipeCommand {
    private Long id;
    private Integer servings;
    private Integer prepTime;
    private Integer cookTime;
    private String source;
    private String url;
    private String directions;
    private String description;
    private Difficulty difficulty;

    private NotesCommand notes;
    private Set<IngredientCommand> ingredients = new HashSet<>();
    private Set<CategoryCommand> categories = new HashSet<>();

}
