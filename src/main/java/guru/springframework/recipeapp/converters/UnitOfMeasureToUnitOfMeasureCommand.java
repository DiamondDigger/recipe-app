package guru.springframework.recipeapp.converters;

import guru.springframework.recipeapp.commands.UnitOfMeasureCommand;
import guru.springframework.recipeapp.domain.UnitOfMeasure;

public class UnitOfMeasureToUnitOfMeasureCommand {
    public UnitOfMeasureCommand convert(UnitOfMeasure o) {
        UnitOfMeasureCommand stub = new UnitOfMeasureCommand();
        return stub;
    }
}
