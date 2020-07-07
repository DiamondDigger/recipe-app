package guru.springframework.recipeapp.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CategoryTest {

    Category category;

    @BeforeEach
    public void setUp(){
        category = new Category();
    }

    @Test
    void getId() {
        Long idValue = 4L;

        category.setId(idValue);

        assertEquals(4L, category.getId());
    }

    @Test
    void getDescription() {
        String description = "some description";

        category.setDescription(description);

        assertEquals("some description", category.getDescription());
    }

}