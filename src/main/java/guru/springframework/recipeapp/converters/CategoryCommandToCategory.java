package guru.springframework.recipeapp.converters;

import guru.springframework.recipeapp.commands.CategoryCommand;
import guru.springframework.recipeapp.domain.Category;

public class CategoryCommandToCategory {
    public Category convert(CategoryCommand categoryCommand) {
        Category stub = new Category();
        return stub;
    }
}
