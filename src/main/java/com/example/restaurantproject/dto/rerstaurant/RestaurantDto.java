package com.example.restaurantproject.dto.rerstaurant;

import com.example.restaurantproject.dto.AddressDto;
import com.example.restaurantproject.dto.ContactDto;
import com.example.restaurantproject.dto.dish.DishDto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class RestaurantDto {

    private String name;
    private String description;
    private String category;
    private boolean hasDelivery;
    private String contact;
    private AddressDto addressDto;
    private List<DishDto> dishDtos;

}
