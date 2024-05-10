package hu.yokudlela.yokudlela.domain.dto.order;

import hu.yokudlela.yokudlela.domain.validation.menuitem.MenuItemIdExists;
import hu.yokudlela.yokudlela.domain.validation.table.TableIdExists;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderRequest {
    @TableIdExists(message = "error.table.notexist")
    private Long tableId;
    @MenuItemIdExists(message = "error.menuitem.notexist")
    private Long menuItemId;
    @Min(value = 1, message = "error.quantity.min")
    private Integer quantity;
    @Size(max = 100, message = "error.comment.long")
    private String comment;

    @Size(max = 255, message = "error.order.ordergroup.long")
    private String orderGroup;

}
