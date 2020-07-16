package guru.springframework.recipeapp.converters;

import guru.springframework.recipeapp.commands.CategoryCommand;
import guru.springframework.recipeapp.domain.Category;

public class CategoryToCategoryCommand {
    public CategoryCommand convert(Category o) {
        CategoryCommand stub = new CategoryCommand();
        return stub;
    }
}
