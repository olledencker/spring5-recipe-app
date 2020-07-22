package guru.springframework.commands;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoryCommand {
    private Long id;
    private String description;
}
