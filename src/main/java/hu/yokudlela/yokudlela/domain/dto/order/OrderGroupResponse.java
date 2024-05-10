package hu.yokudlela.yokudlela.domain.dto.order;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderGroupResponse {
    private String orderGroup;
    private List<OrderResponse> orders;
    private Double totalPrice;
}
