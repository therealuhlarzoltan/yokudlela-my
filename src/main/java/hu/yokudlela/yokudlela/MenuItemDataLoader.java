package hu.yokudlela.yokudlela;

import hu.yokudlela.yokudlela.domain.entity.MenuItem;
import hu.yokudlela.yokudlela.domain.entity.Table;
import hu.yokudlela.yokudlela.repository.MenuItemRepository;
import hu.yokudlela.yokudlela.repository.TableRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MenuItemDataLoader implements CommandLineRunner {
    @Autowired
    MenuItemRepository menuItemRepository;
    @Override
    public void run(String... args) throws Exception {
        if (menuItemRepository.count() != 0)
            return;

        var seed = List.of(
                MenuItem.builder().name("Uphutu").price(2990).build(),
                MenuItem.builder().name("Amadumbe").price(3290).build(),
                MenuItem.builder().name("Ujeqe").price(3790).build(),
                MenuItem.builder().name("Amasi").price(2500).build(),
                MenuItem.builder().name("Dombolo").price(4890).build(),
                MenuItem.builder().name("Isijingi").price(5660).build()
        );
    }
}
