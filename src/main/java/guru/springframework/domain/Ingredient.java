package guru.springframework.domain;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Ingredient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    private BigDecimal amount;
    @ManyToOne
    @EqualsAndHashCode.Exclude
    private Recipe recipe;
    @OneToOne(fetch =FetchType.EAGER)
    private UnitOfMeasure uom;


    public Ingredient(String ripe_avocados, BigDecimal bigDecimal, UnitOfMeasure st) {
        this.setDescription(ripe_avocados);
        this.setAmount(bigDecimal);
        this.setUom(st);
    }
}
