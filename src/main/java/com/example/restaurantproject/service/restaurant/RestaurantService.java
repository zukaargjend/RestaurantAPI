package com.example.restaurantproject.service.restaurant;

import com.example.restaurantproject.dao.RestaurantRepository;
import com.example.restaurantproject.dto.AddressDto;
import com.example.restaurantproject.dto.rerstaurant.RestaurantDto;
import com.example.restaurantproject.dto.rerstaurant.RestaurantRequest;
import com.example.restaurantproject.dto.rerstaurant.RestaurantResponseNameDto;
import com.example.restaurantproject.dto.rerstaurant.RestaurantUpdateRequest;
import com.example.restaurantproject.entity.Restaurant;
import com.example.restaurantproject.exception.EntityAlreadyExistsException;
import com.example.restaurantproject.exception.EntityNotFoundException;
import com.example.restaurantproject.mapper.RestaurantMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;

    private final RestaurantMapper restaurantMapper;

    public RestaurantService(final RestaurantRepository restaurantRepository, final RestaurantMapper restaurantMapper){
        this.restaurantRepository = restaurantRepository;
        this.restaurantMapper = restaurantMapper;
    }

    public RestaurantDto createRestaurant(final RestaurantRequest restaurantRequest) throws EntityAlreadyExistsException {
        final Restaurant restaurant = restaurantMapper.toEntity(restaurantRequest);
        if(existsRestaurant(restaurant)){
            throw new EntityAlreadyExistsException("Restaurant with name: " + restaurantRequest.getName() + " already exists! " );
        }

        final Restaurant restaurantInDb = restaurantRepository.save(restaurant);

        return restaurantMapper.toDto(restaurantInDb);
    }

    public List<RestaurantResponseNameDto> getAllRestaurants(final String name) throws EntityNotFoundException {
        final List<Restaurant> restaurants = restaurantRepository.findAllAndByNameIgnoreCase(name);

        if(restaurants.isEmpty()){
            throw new EntityNotFoundException("Restaurant not found!");
        }
        return restaurantMapper.restaurantsToDo(restaurants);
    }

    public RestaurantDto getRestaurantById(final Long id) throws EntityNotFoundException {
        final Restaurant restaurant = restaurantRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Restaurant not found!"));

        return restaurantMapper.toDto(restaurant);
    }

    public RestaurantDto update(final Long id, final RestaurantUpdateRequest updateRequest) throws EntityNotFoundException {
        final Restaurant restaurantInDB = restaurantRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Restaurant not found!"));

        this.restaurantMapper.updateEntityFromDto(updateRequest, restaurantInDB);
        final Restaurant updatedRestaurant = restaurantRepository.save(restaurantInDB);

        return restaurantMapper.toDto(updatedRestaurant);
    }

    public void delete(final Long id) throws EntityNotFoundException {
        final Restaurant restaurantInDB = this.restaurantRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Restaurant not found!"));

        restaurantRepository.delete(restaurantInDB);
    }

    private boolean existsRestaurant(Restaurant restaurant){
        return restaurantRepository.existsByNameIgnoreCase(restaurant.getName());
    }
}
