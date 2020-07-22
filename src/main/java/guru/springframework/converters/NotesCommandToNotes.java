package guru.springframework.converters;

import guru.springframework.commands.NotesCommand;
import guru.springframework.domain.Notes;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class NotesCommandToNotes implements Converter<NotesCommand,Notes> {
    @Override
    @Synchronized
    @Nullable
    public Notes convert(NotesCommand notes) {
        if(notes==null){
            return null;
        }
        final Notes notesCommand = new Notes();
        notesCommand.setId(notes.getId());
        notesCommand.setRecipeNote(notes.getRecipeNote());
        return notesCommand;
    }
}
