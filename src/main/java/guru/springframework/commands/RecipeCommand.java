package guru.springframework.commands;

import guru.springframework.domain.Difficulty;
import lombok.*;

import java.util.HashSet;
import java.util.Set;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RecipeCommand {
    private Long id;
    private String description;
    private Integer prepTime;
    private Integer cookTime;
    private Integer serving;
    private String source;
    private String url;
    private String directions;
    private Difficulty difficulty;
    private Byte[] image;
    private NotesCommand notes;
    @Builder.Default
    private Set<IngredientCommand> ingredients = new HashSet<>();
    @Builder.Default
    private Set<CategoryCommand> categories = new HashSet<>();
}
