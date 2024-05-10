package hu.yokudlela.yokudlela.controller;

import hu.yokudlela.yokudlela.domain.dto.table.TableIdRequest;
import hu.yokudlela.yokudlela.domain.dto.table.TableRequest;
import hu.yokudlela.yokudlela.domain.dto.table.TableResponse;
import hu.yokudlela.yokudlela.domain.validation.table.TableIsAvailableIsTrueGroup;
import hu.yokudlela.yokudlela.domain.validation.table.TableIsAvailableIsFalseGroup;
import hu.yokudlela.yokudlela.service.TableService;
import io.swagger.v3.oas.annotations.Operation;
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

    @Operation(summary = "Save Table", description = "Save table to database")
    @PostMapping
    public void save(@Valid @RequestBody TableRequest table){
        tableService.save(table);
    }

    @Operation(summary = "Enable Table", description = "Enable table to use")
    @Validated(TableIsAvailableIsFalseGroup.class)
    @PatchMapping("/enable")
    public TableResponse enable(@Valid @RequestBody TableIdRequest pId){
        return tableService.enable(pId.getId());
    }

    @Operation(summary = "Disable Table", description = "Disable table to use")
    @Validated(TableIsAvailableIsTrueGroup.class)
    @PatchMapping("/disable")
    public TableResponse disable(@Valid @RequestBody TableIdRequest pId){
        return tableService.disable(pId.getId());
    }

    @Operation(summary = "Delete Table", description = "Delete table from database")
    @DeleteMapping
    public void delete(@Valid @RequestBody TableIdRequest pdata){
        tableService.delete(pdata.getId());
    }

    @Operation(summary = "Get Tables that cannot be used", description = "Get tables that cannot be used")
    @GetMapping("/cannotbeused")
    public List<TableResponse> getNotUse(){
        return tableService.getNotAvailable();
    }

    @Operation(summary = "Get Tables that can be used", description = "Get tables that can be used")
    @GetMapping("/usable")
    public List<TableResponse> getUse(){
        return tableService.getAvailable();
    }

}