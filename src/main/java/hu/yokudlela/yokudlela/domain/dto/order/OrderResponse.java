package hu.yokudlela.yokudlela.domain.dto.order;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderResponse {
    private String id;
    private String orderState;
    private String menuItem;
    private String table;
    private Integer quantity;
    private Double price;
}
