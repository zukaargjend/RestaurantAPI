package com.example.restaurantproject.dao;

import com.example.restaurantproject.dto.rerstaurant.RestaurantDto;
import com.example.restaurantproject.dto.rerstaurant.RestaurantResponseNameDto;
import com.example.restaurantproject.entity.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {

    boolean existsByNameIgnoreCase(String name);

    @Query(value = " SELECT * FROM restaurant WHERE (:name IS NULL OR name ILIKE CONCAT ('%', :name, '%')) ",
            nativeQuery = true)
    List<Restaurant> findAllAndByNameIgnoreCase(@RequestParam(value = "name", required = false) String name);

}
