package hu.yokudlela.yokudlela.service;

import hu.yokudlela.yokudlela.repository.MenuItemRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class MenuItemService {
    private final MenuItemRepository menuItemRepository;
    private final ModelMapper mapper;
}
