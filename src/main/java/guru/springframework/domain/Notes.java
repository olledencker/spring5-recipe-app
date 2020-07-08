package guru.springframework.domain;


import javax.persistence.*;

@Entity
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

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    public String getRecipeNote() {
        return RecipeNote;
    }

    public void setRecipeNote(String recipeNote) {
        RecipeNote = recipeNote;
    }

    @Lob
    private String RecipeNote;
}
