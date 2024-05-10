package hu.yokudlela.yokudlela.service;

import hu.yokudlela.yokudlela.domain.dto.reservation.ReservationIdResponse;
import hu.yokudlela.yokudlela.domain.dto.reservation.ReservationRequest;
import hu.yokudlela.yokudlela.domain.dto.reservation.ReservationResponse;
import hu.yokudlela.yokudlela.domain.entity.Reservation;
import hu.yokudlela.yokudlela.domain.entity.Table;
import hu.yokudlela.yokudlela.exception.BusinessException;
import hu.yokudlela.yokudlela.repository.ReservationRepository;
import hu.yokudlela.yokudlela.repository.TableRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReservationService {
    private final TableRepository tableRepository;
    private final ReservationRepository reservationRepository;
    private final ModelMapper mapper;

    public ReservationIdResponse bookTables(ReservationRequest pData) {
        List<Table> tableEntities = tableRepository.findByIsAvailable(true);
        List<Reservation> reservations = reservationRepository.findByBeginBetweenOrEndBetween(pData.getBegin(), pData.getEnd(), pData.getBegin(), pData.getEnd());
        reservations.forEach(
                reservation -> {
                    reservation.getTables().forEach(tableEntities::remove);
                });
        if (tableEntities.isEmpty()) {
            throw new BusinessException("error.reservation.nofreetables");
        }
        List<Table> res;
        res = oneTableEqualOrMoreCapacity(tableEntities, pData.getPerson());
        if (res.isEmpty()) {
            res = multipleTables(tableEntities, pData.getPerson());
        }
        int capacitySum = (int) res.stream().mapToLong(Table::getCapacity).sum();
        if (capacitySum >= pData.getPerson()) {
            return mapper.map(saveReservation(pData, res), ReservationIdResponse.class);
        } else {
            throw new BusinessException("error.reservation.nofreetables");
        }
    }

    public List<ReservationResponse> findByBeginBetweenOrEndBetween(LocalDateTime beginStart, LocalDateTime beginEnd, LocalDateTime endStart, LocalDateTime endEnd) {
        return reservationRepository.findByBeginBetweenOrEndBetween(beginStart, beginEnd, endStart, endEnd)
                .stream()
                .map(e -> mapper.map(e, ReservationResponse.class))
                .collect(Collectors.toList());
    }

    private List<Table> oneTableEqualOrMoreCapacity(List<Table> tableEntities, byte pCapacity) {
        List<Table> list = tableEntities.stream()
                .filter(table -> table.getCapacity() >= pCapacity)
                .toList()
                .stream().sorted(Comparator.comparingLong(Table::getCapacity))
                .toList();

        return (list.isEmpty()) ? list : list.subList(0, 1);
    }

    private List<Table> multipleTables(List<Table> tableEntities, byte pCapacity) {
        List<Table> list = tableEntities.stream()
                .sorted(Comparator.comparingLong(Table::getCapacity).reversed())
                .toList();
        AtomicInteger capacityOfTables = new AtomicInteger(0);
        return list.stream().takeWhile(table -> ((capacityOfTables.getAndAdd((int) table.getCapacity())) <= pCapacity)).toList();

    }

    private ReservationResponse saveReservation(ReservationRequest pData, List<Table> tableEntities) {
        Reservation entity = mapper.map(pData, Reservation.class);
        entity.setTables(tableEntities);
        return mapper.map(reservationRepository.save(entity), ReservationResponse.class);
    }

    public void deleteById(String id) {
        reservationRepository.deleteById(id);
    }
}
