package hu.yokudlela.yokudlela.domain.entity;

import hu.yokudlela.yokudlela.domain.enumeration.OrderState;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import hu.yokudlela.yokudlela.domain.entity.Table;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@jakarta.persistence.Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator",
            parameters = {
                    @Parameter(
                            name = "uuid_gen_strategy_class",
                            value = "org.hibernate.id.uuid.CustomVersionOneStrategy"
                    )
            }
    )
    @Column(name = "id", updatable = false, nullable = false)
    private String id;

    @Column(name = "order_group")
    @NotNull(message = "error.order.ordergroup.notset")
    @NotEmpty(message = "error.order.ordergroup.empty")
    @Size(max = 255, message = "error.order.ordergroup.long")
    private String orderGroup;

    @Min(value = 1, message = "error.order.quantity.few")
    private int quantity;

    @Size(max = 100, message = "error.order.comment.long")
    private String comment;

    @Column(name = "order_status")
    @NotNull(message = "error.order.status.notset")
    private OrderState orderStatus;

    @NotNull(message = "error.order.menuitem.notset")
    @ManyToOne(fetch = FetchType.LAZY)
    private MenuItem menuItem;

    @NotNull(message = "error.order.table.notset")
    @ManyToOne(fetch = FetchType.LAZY)
    private Table table;
}
