package guru.springframework.recipeapp.converters;

import guru.springframework.recipeapp.commands.NotesCommand;
import guru.springframework.recipeapp.domain.Notes;

public class NotesToNotesCommand {
    public NotesCommand convert(Notes notesCommand) {
        NotesCommand stub = new NotesCommand();
        return stub;
    }
}
