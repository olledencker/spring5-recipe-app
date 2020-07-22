package guru.springframework.converters;

import guru.springframework.commands.NotesCommand;
import guru.springframework.domain.Notes;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class NotesCommandToNotesTest {
    public static Long NOTES_ID= 1L;
    public static String DESCR="a descr";

    NotesCommandToNotes converter;
    @Before
    public void setUp() throws Exception {
        converter = new NotesCommandToNotes();
    }
    @Test
    public void isNullable(){
        assertNull(converter.convert(null));

    }
    @Test
    public void emptyObject(){
        assertNotNull(converter.convert(new NotesCommand()));
    }
    @Test
    public void convert() {
        NotesCommand nCom = NotesCommand.builder().id(NOTES_ID)
                                .RecipeNote(DESCR).build();

        Notes note = converter.convert(nCom);

        assertNotNull(note);
        assertEquals(note.getId(),NOTES_ID);
        assertEquals(note.getRecipeNote(),DESCR);
    }
}