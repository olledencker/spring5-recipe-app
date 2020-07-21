package guru.springframework.commands;

import guru.springframework.domain.Recipe;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class CategoryCommand {
    private Long id;
    private String description;
    private Set<Recipe> recepies= new HashSet<>();
}
