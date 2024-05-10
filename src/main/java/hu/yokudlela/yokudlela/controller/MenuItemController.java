package hu.yokudlela.yokudlela.controller;

import hu.yokudlela.yokudlela.domain.dto.menuitem.MenuItemIdRequest;
import hu.yokudlela.yokudlela.domain.dto.menuitem.MenuItemRequest;
import hu.yokudlela.yokudlela.domain.dto.table.TableIdRequest;
import hu.yokudlela.yokudlela.domain.dto.table.TableRequest;
import hu.yokudlela.yokudlela.domain.dto.table.TableResponse;
import hu.yokudlela.yokudlela.domain.entity.MenuItem;
import hu.yokudlela.yokudlela.domain.validation.table.TableIsAvailableIsFalseGroup;
import hu.yokudlela.yokudlela.domain.validation.table.TableIsAvailableIsTrueGroup;
import hu.yokudlela.yokudlela.service.MenuItemService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("menu")
@RequestMapping("/menu-items")
@RequiredArgsConstructor
@CrossOrigin
public class MenuItemController {
    private final MenuItemService menuItemService;
    @PostMapping
    public void save(@Valid @RequestBody MenuItemRequest menuItemRequest){
        menuItemService.create(menuItemRequest);
    }

    @DeleteMapping
    public void delete(@Valid @RequestBody MenuItemIdRequest id) {
        menuItemService.delete(id.getId());
    }

    @GetMapping
    public List<MenuItem> getAll(){
        return menuItemService.getAll();
    }

    @GetMapping("/max-price")
    public List<MenuItem> getByMaxPrice(@RequestParam Double maxPrice){
        return menuItemService.findByMaxPrice(maxPrice);
    }
}
