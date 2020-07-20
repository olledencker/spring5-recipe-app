package guru.springframework.domain;


import lombok.*;

import javax.persistence.*;

@Data
@Entity
@EqualsAndHashCode(exclude = {"recipe"})
public class Notes{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @OneToOne
    private Recipe recipe;

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getRecipeNote() {
        return RecipeNote;
    }

    public void setRecipeNote(String recipeNote) {
        RecipeNote = recipeNote;
    }

    @Lob
    private String RecipeNote;

    public Notes() {
    }
    public Notes(String note){
        this.setRecipeNote(note);
    }
}
