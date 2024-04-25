package hu.yokudlela.yokudlela.repository;

import hu.yokudlela.yokudlela.domain.entity.Reservation;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface ReservationRepository extends CrudRepository<Reservation, UUID> {
    public List<Reservation> findByBeginBetweenOrEndBetween(LocalDateTime pBeginStart, LocalDateTime pBeginEnd, LocalDateTime pEndStart, LocalDateTime pEndEnd);
}
