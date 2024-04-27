package hu.yokudlela.yokudlela.service;

import hu.yokudlela.yokudlela.domain.dto.menuitem.MenuItemRequest;
import hu.yokudlela.yokudlela.domain.entity.MenuItem;
import hu.yokudlela.yokudlela.repository.MenuItemRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class MenuItemService {
    private final ModelMapper mapper;
    private final MenuItemRepository menuItemRepository;

    public List<MenuItem> getAll() {
        List<MenuItem> items = new ArrayList<>();
        menuItemRepository.findAll().forEach(items::add);
        return items;
    }

    public MenuItem create(MenuItemRequest menuItemRequest) {
        return menuItemRepository.save(mapper.map(menuItemRequest, MenuItem.class));
    }

    public void delete(Long id) {
        menuItemRepository.deleteById(id);
    }

    public List<MenuItem> findByMaxPrice(Double max) {
        return menuItemRepository.findByPriceLessThanEqual(max);
    }
}
