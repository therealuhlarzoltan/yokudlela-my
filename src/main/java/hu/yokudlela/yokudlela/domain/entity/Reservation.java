package hu.yokudlela.yokudlela.domain.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import hu.yokudlela.yokudlela.serializer.ContactSerializer;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import hu.yokudlela.yokudlela.domain.entity.Table;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@jakarta.persistence.Table(name = "reservations")
public class Reservation {
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

    @NotBlank(message = "error.reservation.name.notset")
    @NotNull(message = "error.reservation.name.notset")
    @Size(max = 20, min = 3, message = "error.reservation.name.long")
    private String name;

    @NotBlank(message = "error.reservation.name.notset")
    @NotNull(message = "error.reservation.name.notset")
    @JsonSerialize(using = ContactSerializer.class)
    private String contact;

    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @Column(name = "begintime")
    private LocalDateTime begin;

    @Future(message = "error.reservation.begin.past")
    @Column(name = "endtime")
    private LocalDateTime end;


    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "reservation_tables",
            joinColumns = @JoinColumn(name = "reservation_id"),
            inverseJoinColumns = @JoinColumn(name = "table_id")
    )
    private List<Table> tables = new ArrayList<>();



    @Min(value = 1, message = "error.reservation.person.few")
    private byte person;
}
