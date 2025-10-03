package com.example.restaurantproject.dto.rerstaurant;

import com.example.restaurantproject.dto.AddressDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RestaurantUpdateRequest {

    private String name;

    private String description;

    private String category;

    private boolean hasDelivery;

    private String contact;

    private AddressDto addressDto;

}
