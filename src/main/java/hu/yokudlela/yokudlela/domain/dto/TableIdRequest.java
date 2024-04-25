package hu.yokudlela.yokudlela.domain.dto;

import hu.yokudlela.yokudlela.domain.validation.table.TableAvailable;
import hu.yokudlela.yokudlela.domain.validation.table.TableIdExists;
import hu.yokudlela.yokudlela.domain.validation.table.TableIsAvailableIsFalseGroup;
import hu.yokudlela.yokudlela.domain.validation.table.TableIsAvailableIsTrueGroup;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TableIdRequest {

    @TableIdExists(message = "error.table.notexist")
    @TableAvailable(value="true", message = "error.table.available.false", groups = {TableIsAvailableIsTrueGroup.class})
    @TableAvailable(value="false", message = "error.table.available.true", groups = {TableIsAvailableIsFalseGroup.class})
    private long id;
}
