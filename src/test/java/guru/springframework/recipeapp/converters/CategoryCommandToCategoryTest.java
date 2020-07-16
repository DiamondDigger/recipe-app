package guru.springframework.recipeapp.converters;

import guru.springframework.recipeapp.commands.CategoryCommand;
import guru.springframework.recipeapp.domain.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CategoryCommandToCategoryTest {
    public static final Long LONG_ID = 1L;
    public static final String DESCRIPTION = "description";

    CategoryCommandToCategory converter;

    @BeforeEach
    void setUp() {
        converter = new CategoryCommandToCategory();
    }

    @Test
    void testNullParameter(){
        assertNull(converter.convert(null));
    }

    @Test
    void testEmptyObject(){
        assertNotNull(converter.convert(new CategoryCommand()));
    }

    @Test
    void testConvertTo(){
        //given
        CategoryCommand categoryCommand = new CategoryCommand();
        categoryCommand.setId(LONG_ID);
        categoryCommand.setDescription(DESCRIPTION);

        //when
        Category category = converter.convert(categoryCommand);

        //then
        assertNotNull(category);
        assertEquals(LONG_ID, category.getId());
        assertEquals(DESCRIPTION, category.getDescription());
    }
}
