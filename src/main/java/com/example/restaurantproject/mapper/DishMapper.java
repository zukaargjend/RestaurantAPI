package com.example.restaurantproject.mapper;

import com.example.restaurantproject.dto.dish.DishDto;
import com.example.restaurantproject.entity.Dish;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface DishMapper {

    Dish toEntity(DishDto dishDto);

    DishDto toDto(Dish dish);

    List<Dish> toEntityList(List<DishDto> dishDtos);

    List<DishDto> toDtoList(List<Dish> dishes);

}
