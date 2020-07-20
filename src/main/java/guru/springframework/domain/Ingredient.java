package guru.springframework.domain;

import javax.persistence.*;
import java.math.BigDecimal;
@Entity
public class Ingredient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private String description;
    private BigDecimal amount;
    @ManyToOne
    private Recipe recipe;
    @OneToOne(fetch =FetchType.EAGER)
    private UnitOfMeasure uom;

    public Ingredient() {
        super();
    }

    public Ingredient(String description, BigDecimal amount, UnitOfMeasure unitOfMeasure) {
        this.setDescription(description);
        this.setAmount(amount);
        this.setUom(unitOfMeasure);
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getDescription() {
        return this.description;
    }

    public BigDecimal getAmount() {
        return this.amount;
    }

    public Recipe getRecipe() {
        return this.recipe;
    }

    public UnitOfMeasure getUom() {
        return this.uom;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    public void setUom(UnitOfMeasure uom) {
        this.uom = uom;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof Ingredient)) return false;
        final Ingredient other = (Ingredient) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$Id = this.getId();
        final Object other$Id = other.getId();
        if (this$Id == null ? other$Id != null : !this$Id.equals(other$Id)) return false;
        final Object this$description = this.getDescription();
        final Object other$description = other.getDescription();
        if (this$description == null ? other$description != null : !this$description.equals(other$description))
            return false;
        final Object this$amount = this.getAmount();
        final Object other$amount = other.getAmount();
        if (this$amount == null ? other$amount != null : !this$amount.equals(other$amount)) return false;
        final Object this$recipe = this.getRecipe();
        final Object other$recipe = other.getRecipe();
        if (this$recipe == null ? other$recipe != null : !this$recipe.equals(other$recipe)) return false;
        final Object this$uom = this.getUom();
        final Object other$uom = other.getUom();
        if (this$uom == null ? other$uom != null : !this$uom.equals(other$uom)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof Ingredient;
    }


    public String toString() {
        return "Ingredient(Id=" + this.getId() + ", description=" + this.getDescription() + ", amount=" + this.getAmount() + ", recipe=" + this.getRecipe() + ", uom=" + this.getUom() + ")";
    }
}
