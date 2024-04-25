package hu.yokudlela.yokudlela.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TableResponse {
    private long id;
    private String name;
    private long capacity;
}
