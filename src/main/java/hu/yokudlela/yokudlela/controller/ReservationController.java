package hu.yokudlela.yokudlela.controller;

import hu.yokudlela.yokudlela.domain.dto.reservation.*;
import hu.yokudlela.yokudlela.service.ReservationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("reservation")
@RequestMapping("/reservation")
@RequiredArgsConstructor
@Validated
@CrossOrigin
public class ReservationController {
    private final ReservationService reservationService;

    @GetMapping(path = "/byTimeIntervall")
    public List<ReservationResponse> get(@Valid @RequestBody TimeIntervalRequest pTime) {
        return reservationService.findByBeginBetweenOrEndBetween(pTime.getBegin(),pTime.getEnd(),pTime.getBegin(),pTime.getEnd());
    }

    @PostMapping
    public ReservationIdResponse add(@Valid @RequestBody ReservationRequest pRes) {
        return reservationService.bookTables(pRes);
    }

    @DeleteMapping
    public void delete(@Valid @RequestBody ReservationIdRequest pId){
        reservationService.deleteById(pId.getId());
    }


}
