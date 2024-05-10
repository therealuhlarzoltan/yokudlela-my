package hu.yokudlela.yokudlela.domain.dto.menuitem;

import hu.yokudlela.yokudlela.domain.validation.menuitem.MenuItemIdExists;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MenuItemIdRequest {
    @MenuItemIdExists(message = "error.menuitem.notexists")
    private Long id;
}
