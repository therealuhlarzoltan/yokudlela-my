package hu.yokudlela.yokudlela.domain.dto.order;

import hu.yokudlela.yokudlela.domain.validation.order.OrderStateExists;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderGroupRequest {
    @OrderStateExists(message = "error.ordergroup.notexists")
    private String orderGroup;
}
