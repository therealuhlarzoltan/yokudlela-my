package hu.yokudlela.yokudlela.mapper;

import hu.yokudlela.yokudlela.converter.TableNameConverter;
import hu.yokudlela.yokudlela.domain.dto.menuitem.MenuItemRequest;
import hu.yokudlela.yokudlela.domain.dto.reservation.ReservationResponse;
import hu.yokudlela.yokudlela.domain.dto.table.TableResponse;
import hu.yokudlela.yokudlela.domain.entity.MenuItem;
import hu.yokudlela.yokudlela.domain.entity.Reservation;
import hu.yokudlela.yokudlela.domain.entity.Table;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfig {
    @Bean
    public ModelMapper modelMapper(){
        ModelMapper mapper = new ModelMapper();
        mapper.addConverter(new TableNameConverter());
        mapper.addConverter(new TableNameConverter());
        TypeMap<Reservation, ReservationResponse> propertyMapper = mapper.createTypeMap(Reservation.class, ReservationResponse.class);
        propertyMapper.addMapping(Reservation::getTables, ReservationResponse::setTableNames);
        TypeMap<Table, TableResponse> tablePropertyMapper = mapper.createTypeMap(Table.class, TableResponse.class);
        TypeMap<MenuItemRequest, MenuItem> menuItemPropertyMapper = mapper.createTypeMap(MenuItemRequest.class, MenuItem.class);
        return mapper;
    }
}
