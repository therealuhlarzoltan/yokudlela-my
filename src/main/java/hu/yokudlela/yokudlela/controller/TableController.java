package hu.yokudlela.yokudlela.controller;

import hu.yokudlela.yokudlela.domain.dto.TableIdRequest;
import hu.yokudlela.yokudlela.domain.dto.TableRequest;
import hu.yokudlela.yokudlela.domain.dto.TableResponse;
import hu.yokudlela.yokudlela.domain.entity.Table;
import hu.yokudlela.yokudlela.domain.validation.table.TableIsAvailableIsTrueGroup;
import hu.yokudlela.yokudlela.domain.validation.table.TableIsAvailableIsFalseGroup;
import hu.yokudlela.yokudlela.repository.TableRepository;
import hu.yokudlela.yokudlela.service.TableService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("table")
@RequestMapping("/table")
@RequiredArgsConstructor
@Validated
@CrossOrigin
public class TableController {
    private final TableService tableService;
    @PostMapping
    public void save(@RequestBody TableRequest table){
        tableService.save(table);
    }

    @Validated(TableIsAvailableIsFalseGroup.class)
    @PatchMapping("/enable")
    public TableResponse enable(@Valid @RequestBody TableIdRequest pId){
        return tableService.enable(pId.getId());
    }

    @Validated(TableIsAvailableIsTrueGroup.class)
    @PatchMapping("/disable")
    public TableResponse disable(@Valid @RequestBody TableIdRequest pId){
        return tableService.disable(pId.getId());
    }

    @DeleteMapping
    public void delete(TableIdRequest pdata){
        tableService.delete(pdata.getId());
    }

    @GetMapping("/cannotbeused")
    public List<TableResponse> getNotUse(){
        return tableService.getNotAvailable();
    }

    @GetMapping("/usable")
    public List<TableResponse> getUse(){
        return tableService.getAvailable();
    }

}