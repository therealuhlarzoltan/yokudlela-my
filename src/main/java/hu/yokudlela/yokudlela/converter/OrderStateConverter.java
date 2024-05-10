package hu.yokudlela.yokudlela.converter;

import hu.yokudlela.yokudlela.domain.enumeration.OrderState;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.stream.Stream;

@Converter(autoApply = true)
public class OrderStateConverter implements AttributeConverter<OrderState, String> {

    @Override
    public String convertToDatabaseColumn(OrderState orderState) {
        if (orderState == null) {
            return null;
        }
        return orderState.getName();
    }

    @Override
    public OrderState convertToEntityAttribute(String name) {
        if (name == null) {
            return null;
        }

        return Stream.of(OrderState.values())
                .filter(s -> s.getName().equals(name))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}