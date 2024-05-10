package hu.yokudlela.yokudlela.repository;

import hu.yokudlela.yokudlela.domain.entity.Reservation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface ReservationRepository extends CrudRepository<Reservation, String> {
    public List<Reservation> findByBeginBetweenOrEndBetween(LocalDateTime pBeginStart, LocalDateTime pBeginEnd, LocalDateTime pEndStart, LocalDateTime pEndEnd);
}
