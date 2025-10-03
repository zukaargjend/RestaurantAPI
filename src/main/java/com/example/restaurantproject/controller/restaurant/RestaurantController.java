package com.example.restaurantproject.controller.restaurant;

import com.example.restaurantproject.dto.rerstaurant.RestaurantDto;
import com.example.restaurantproject.dto.rerstaurant.RestaurantRequest;
import com.example.restaurantproject.dto.rerstaurant.RestaurantResponseNameDto;
import com.example.restaurantproject.dto.rerstaurant.RestaurantUpdateRequest;
import com.example.restaurantproject.entity.Restaurant;
import com.example.restaurantproject.exception.EntityAlreadyExistsException;
import com.example.restaurantproject.service.restaurant.RestaurantService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/restaurant")
public class RestaurantController {

    private final RestaurantService restaurantService;

    public RestaurantController(final RestaurantService restaurantService){
        this.restaurantService = restaurantService;
    }

    @PostMapping
    public RestaurantDto create(@RequestBody @NotNull @Valid RestaurantRequest restaurantRequest) throws EntityAlreadyExistsException {
        return restaurantService.createRestaurant(restaurantRequest);
    }

    @GetMapping
    public List<RestaurantResponseNameDto> findAllRestaurantsByName(@RequestParam(value = "name", required = false) String name){
        return restaurantService.getAllRestaurants(name);
    }

    @GetMapping("find-by-id/{id}")
    public RestaurantDto getById(@PathVariable Long id){
        return restaurantService.getRestaurantById(id);
    }

    @PutMapping("update-by-id/{id}")
    public RestaurantDto updateRestaurant(@PathVariable Long id, @RequestBody RestaurantUpdateRequest restaurantUpdateRequest) {
        return restaurantService.update(id, restaurantUpdateRequest);
    }
}
