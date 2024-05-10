package hu.yokudlela.yokudlela;

import hu.yokudlela.yokudlela.repository.MenuItemRepository;
import hu.yokudlela.yokudlela.repository.TableRepository;
import hu.yokudlela.yokudlela.domain.entity.Table;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TableDataLoader implements CommandLineRunner {
    @Autowired
    TableRepository tableRepository;

    @Override
    public void run(String... args) throws Exception {
        if (tableRepository.count() != 0)
            return;

        var seed = List.of(
                Table.builder().name("Terasz 1").capacity(8).isAvailable(true).build(),
                Table.builder().name("Terasz 2").capacity(6).isAvailable(true).build(),
                Table.builder().name("Terasz 3").capacity(4).isAvailable(true).build(),
                Table.builder().name("Beltér 1").capacity(4).isAvailable(true).build(),
                Table.builder().name("Beltér 2").capacity(2).isAvailable(true).build(),
                Table.builder().name("Beltér 3").capacity(4).isAvailable(true).build()
        );
        tableRepository.saveAll(seed);
    }
}
