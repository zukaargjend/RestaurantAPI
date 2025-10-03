package com.example.restaurantproject.mapper;

import com.example.restaurantproject.dto.rerstaurant.RestaurantDto;
import com.example.restaurantproject.dto.rerstaurant.RestaurantRequest;
import com.example.restaurantproject.dto.rerstaurant.RestaurantResponseNameDto;
import com.example.restaurantproject.dto.rerstaurant.RestaurantUpdateRequest;
import com.example.restaurantproject.entity.Restaurant;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface RestaurantMapper {

    Restaurant toEntity(RestaurantRequest restaurantRequest);

    @Mapping(source = "dishes", target = "dishDtos")
    RestaurantDto toDto(Restaurant restaurant);

    Restaurant toEntity(RestaurantResponseNameDto restaurantResponseNameDto);

    RestaurantResponseNameDto restaurantToDto(Restaurant restaurant);

    List<RestaurantResponseNameDto> restaurantsToDo(List<Restaurant> restaurants);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromDto(RestaurantUpdateRequest restaurantUpdateRequest, @MappingTarget Restaurant restaurant);

}
