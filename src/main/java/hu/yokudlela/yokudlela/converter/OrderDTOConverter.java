package hu.yokudlela.yokudlela.converter;

import hu.yokudlela.yokudlela.domain.dto.order.OrderResponse;
import hu.yokudlela.yokudlela.domain.entity.Order;
import hu.yokudlela.yokudlela.domain.entity.Table;
import org.modelmapper.Converter;
import org.modelmapper.spi.MappingContext;

public class OrderDTOConverter implements Converter<Order, OrderResponse> {
    @Override
    public OrderResponse convert(MappingContext<Order, OrderResponse> mappingContext) {
        var entity = mappingContext.getSource();
        var dto = (OrderResponse.builder()
                .id(entity.getId())
                .table(entity.getTable().getName())
                .orderState(entity.getOrderStatus().getName())
                .menuItem(entity.getMenuItem().getName()))
                .quantity(entity.getQuantity())
                .price(entity.getQuantity() * entity.getMenuItem().getPrice())
                .build();
        return dto;
    }
}
