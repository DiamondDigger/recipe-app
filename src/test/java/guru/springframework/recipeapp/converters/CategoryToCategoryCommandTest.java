package guru.springframework.recipeapp.converters;

import guru.springframework.recipeapp.commands.CategoryCommand;
import guru.springframework.recipeapp.domain.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CategoryToCategoryCommandTest {
    public static final Long LONG_ID = 1L;
    public static final String DESCRIPTION = "description";

    CategoryToCategoryCommand converter;

    @BeforeEach
    void setUp() {
        converter = new CategoryToCategoryCommand();
    }

    @Test
    void testNullParameter(){
        assertNull(converter.convert(null));
    }

    @Test
    void testEmptyObject(){
        assertNotNull(converter.convert(new Category()));
    }

    @Test
    void testConvertTo(){
        //given
        Category category = new Category();
        category.setId(LONG_ID);
        category.setDescription(DESCRIPTION);

        //when
        CategoryCommand categoryCommand = converter.convert(category);

        //then
        assertNotNull(categoryCommand);
        assertEquals(LONG_ID, categoryCommand.getId());
        assertEquals(DESCRIPTION, categoryCommand.getDescription());
    }
}
