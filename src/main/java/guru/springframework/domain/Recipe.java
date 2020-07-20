package guru.springframework.domain;





import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
public class Recipe {
    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY)
    private Long Id;
    private String description;
    private Integer prepTime;
    private Integer cookTime;
    private Integer serving;
    private String source;
    private String url;
    private String directions;
    @Enumerated(value = EnumType.STRING)
    private Difficulty difficulty;
    @Lob
    private Byte[] image;
    @OneToOne(cascade = CascadeType.ALL)
    private Notes notes;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "recipe")
    private Set<Ingredient> ingredients = new HashSet<>();

    @ManyToMany()
    @JoinTable(name="recipe_category", joinColumns = @JoinColumn(name = "recipe_id"), inverseJoinColumns = @JoinColumn(name="category_id"))
    private Set<Category> categories = new HashSet<>();
    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }


}
