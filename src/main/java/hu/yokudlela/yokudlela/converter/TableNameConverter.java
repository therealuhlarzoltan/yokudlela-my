package hu.yokudlela.yokudlela.converter;

import hu.yokudlela.yokudlela.domain.entity.Table;
import org.modelmapper.Converter;
import org.modelmapper.spi.MappingContext;

public class TableNameConverter implements Converter<Table, String> {
    @Override
    public String convert(MappingContext<Table, String> mappingContext) {
        if (mappingContext.getSource() == null) {
            return "";
        }
        return mappingContext.getSource().getName();
    }
}
