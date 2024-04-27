package hu.yokudlela.yokudlela.domain.entity;

import jakarta.persistence.*;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "menu_items")
public class MenuItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, unique = true, nullable = false)
    private Long id;

    @Size(min = 4, max = 50, message = "error.menuitem.name.length")
    @NotNull(message = "error.menuitem.name.notset")
    @NotEmpty(message = "error.menuitem.blank")
    private String name;

    @NotNull(message = "error.menuitem.price.notset")
    @Min(value = 0, message = "error.menuitem.price.negative")
    private double price;
}
