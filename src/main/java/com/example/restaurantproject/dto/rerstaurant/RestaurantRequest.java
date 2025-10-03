package com.example.restaurantproject.dto.rerstaurant;

import com.example.restaurantproject.dto.AddressDto;
import com.example.restaurantproject.dto.dish.DishDto;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class RestaurantRequest {

    @NotBlank(message = "Name is required!")
    private String name;

    private String description;

    private String category;

    private boolean hasDelivery;

    private String contact;

    private AddressDto addressDto;

}
