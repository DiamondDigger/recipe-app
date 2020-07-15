package guru.springframework.recipeapp.converters;

import guru.springframework.recipeapp.commands.UnitOfMeasureCommand;
import guru.springframework.recipeapp.domain.UnitOfMeasure;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UnitOfMeasureToUnitOfMeasureCommandTest {
    public final String DESCRIPTION = "description";
    public final Long LONG_ID = 1L;

    UnitOfMeasureToUnitOfMeasureCommand converter;

    @BeforeEach
    void setUp() {
        converter = new UnitOfMeasureToUnitOfMeasureCommand();
    }

    @Test
    void testNullParameter(){
        assertNull(converter.convert(null));
    }

    @Test
    void testEmptyObject(){
        assertNotNull(converter.convert(new UnitOfMeasure()));
    }

    @Test
    void testConvertTo() {
        //given
        UnitOfMeasure unitOfMeasure = new UnitOfMeasure();
        unitOfMeasure.setId(LONG_ID);
        unitOfMeasure.setDescription(DESCRIPTION);

        //when
        UnitOfMeasureCommand uomc = converter.convert(unitOfMeasure);

        assertNotNull(uomc);
        assertEquals(LONG_ID, uomc.getId());
        assertEquals(DESCRIPTION, uomc.getDescription());

    }
}
