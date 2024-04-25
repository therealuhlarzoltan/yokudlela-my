package hu.yokudlela.yokudlela.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@jakarta.persistence.Table(name = "tables")
@EqualsAndHashCode(exclude = {"name","isAvailable","capacity"})
public class Table {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private long id;

    @NotEmpty(message = "error.table.name.notset")
    @Size(max=20, message = "error.table.name.long")
    private String name;

    @JsonIgnore
    private boolean isAvailable = true;

    @Min(value = 2, message = "error.table.capacity.min")
    @NotNull(message = "error.table.capacity.notset")
    private long capacity;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "reservation_tables",
            joinColumns = @JoinColumn(name = "reservation_id"),
            inverseJoinColumns = @JoinColumn(name = "table_id")
    )
    private List<Reservation> reservations = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "orders_id", nullable = false)
    private List<Order> orders = new ArrayList<>();
}
