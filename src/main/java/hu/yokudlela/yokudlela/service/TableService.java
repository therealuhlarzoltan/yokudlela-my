package hu.yokudlela.yokudlela.service;

import hu.yokudlela.yokudlela.domain.dto.TableRequest;
import hu.yokudlela.yokudlela.domain.dto.TableResponse;
import hu.yokudlela.yokudlela.domain.entity.Table;
import hu.yokudlela.yokudlela.repository.TableRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class TableService {
    private final TableRepository tableRepository;
    private final ModelMapper mapper;

    public TableResponse save(TableRequest tableRequest) {
        return mapper.map(tableRepository.save(mapper.map(tableRequest, Table.class)), TableResponse.class);
    }

    public List<TableResponse> getAvailable() {
        var entities = tableRepository.findByIsAvailable(true);
        return entities.stream().map(e -> mapper.map(e, TableResponse.class)).collect(Collectors.toList());
    }

    public List<TableResponse> getNotAvailable() {
        var entities = tableRepository.findByIsAvailable(false);
        return entities.stream().map(e -> mapper.map(e, TableResponse.class)).collect(Collectors.toList());
    }

    public void delete(Long id) {
        tableRepository.deleteById(id);
    }

    public TableResponse enable(Long id) {
        Table table = tableRepository.findById(id).get();
        table.setAvailable(Boolean.TRUE);
        return mapper.map(tableRepository.save(table), TableResponse.class);
    }

    public TableResponse disable(Long id) {
        Table table = tableRepository.findById(id).get();
        table.setAvailable(Boolean.FALSE);
        return mapper.map(tableRepository.save(table), TableResponse.class);
    }
}
