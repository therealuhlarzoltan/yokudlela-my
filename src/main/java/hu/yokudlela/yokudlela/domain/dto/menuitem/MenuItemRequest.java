package hu.yokudlela.yokudlela.domain.dto.menuitem;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MenuItemRequest {
    @Size(min = 4, max = 50, message = "error.menuitem.name.length")
    @NotNull(message = "error.menuitem.name.notset")
    @NotEmpty(message = "error.menuitem.blank")
    private String name;


    private Double price;
}
