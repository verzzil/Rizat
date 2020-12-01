package dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import org.hibernate.validator.constraints.Length;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class PostDto {
    @NotEmpty
    @Length(min = 5, max = 50)
    private String name;

    @NotEmpty
    @Length(min = 5, max = 500)
    private String text;
}
