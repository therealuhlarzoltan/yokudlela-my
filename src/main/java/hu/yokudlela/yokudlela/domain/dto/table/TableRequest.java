package hu.yokudlela.yokudlela.domain.dto.table;

import jakarta.validation.constraints.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TableRequest {
    @NotEmpty(message = "error.table.name.notset")
    @Size(max=20, message = "error.table.name.long")
    private String name;

    @NotNull(message = "error.table.capacity.notset")
    @Min(value = 2, message = "error.table.capacity.min")
    private byte capacity;
}
