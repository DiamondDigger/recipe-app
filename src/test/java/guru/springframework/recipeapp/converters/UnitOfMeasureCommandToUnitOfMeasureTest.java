package guru.springframework.recipeapp.converters;

import guru.springframework.recipeapp.commands.UnitOfMeasureCommand;
import guru.springframework.recipeapp.domain.UnitOfMeasure;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UnitOfMeasureCommandToUnitOfMeasureTest {
    public static final String DESCRIPTION = "description";
    public static final Long LONG_ID = 1L;

    UnitOfMeasureCommandToUnitOfMeasure converter;

    @BeforeEach
    void setUp() {
        converter = new UnitOfMeasureCommandToUnitOfMeasure();
    }

    @Test
    void testNullParameter(){
        assertNull(converter.convert(null));
    }

    @Test
    void testEmptyObject(){
        assertNotNull(converter.convert(new UnitOfMeasureCommand()));
    }

    @Test
    void convertTo(){
        //given
        UnitOfMeasureCommand unitOfMeasureCommand = new UnitOfMeasureCommand();
        unitOfMeasureCommand.setId(LONG_ID);
        unitOfMeasureCommand.setDescription(DESCRIPTION);

        //when
        UnitOfMeasure uom = converter.convert(unitOfMeasureCommand);

        //then
        assertNotNull(uom);
        assertEquals(LONG_ID, uom.getId());
        assertEquals(DESCRIPTION, uom.getDescription());
    }
}
