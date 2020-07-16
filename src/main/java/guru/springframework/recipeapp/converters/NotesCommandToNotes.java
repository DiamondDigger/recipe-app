package guru.springframework.recipeapp.converters;

import guru.springframework.recipeapp.commands.NotesCommand;
import guru.springframework.recipeapp.domain.Notes;

public class NotesCommandToNotes {
    public Notes convert(NotesCommand notesCommand) {
        Notes stub = new Notes();
        return stub;
    }
}
