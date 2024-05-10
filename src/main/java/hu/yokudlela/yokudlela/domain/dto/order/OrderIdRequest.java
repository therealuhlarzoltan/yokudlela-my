package hu.yokudlela.yokudlela.domain.dto.order;

import hu.yokudlela.yokudlela.domain.validation.order.OrderIdExists;
import hu.yokudlela.yokudlela.domain.validation.table.TableIdExists;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderIdRequest {
    @OrderIdExists(message = "error.order.notexists")
    private String id;
}
