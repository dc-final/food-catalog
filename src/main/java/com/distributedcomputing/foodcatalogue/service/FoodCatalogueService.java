package com.distributedcomputing.foodcatalogue.service;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.distributedcomputing.foodcatalogue.dto.FoodCataloguePage;
import com.distributedcomputing.foodcatalogue.dto.Restaurant;
import com.distributedcomputing.foodcatalogue.entity.FoodItem;
import com.distributedcomputing.foodcatalogue.repo.FoodItemRepo;

@Service
public class FoodCatalogueService {
	@Autowired
	public RestTemplate restTemplate;
	@Autowired
	FoodItemRepo foodItemRepo;
	

	JdbcTemplate jdbcTemplate;
	
	@Autowired
	public FoodCatalogueService(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}
	

	public FoodItem saveFoodItem(FoodItem foodItem) {
		// TODO Auto-generated method stub
		return foodItemRepo.save(foodItem);
	}

	public FoodCataloguePage fetchFoodCataloguePageDetails(Integer restaurantId) {
		
		List<FoodItem> foodItemList = fetchFoodItemList(restaurantId);
		Restaurant restaurant = fetchRestaurantDetailsFromRestaurantMs(restaurantId);
		System.out.println(restaurant.getCity());
		return createFoodCataloguePage(foodItemList, restaurant);
		
	}

	private FoodCataloguePage createFoodCataloguePage(List<FoodItem> foodItemList, Restaurant restaurant) {
		FoodCataloguePage foodCataloguePage = new FoodCataloguePage();
		foodCataloguePage.setFoodItemsList(foodItemList);
		foodCataloguePage.setRestaurant(restaurant);
		return foodCataloguePage;
		
	}

	private Restaurant fetchRestaurantDetailsFromRestaurantMs(Integer restaurantId) {
		return restTemplate.getForObject("http://RESTAURANT-SERVICE/restaurant/getRestaurant/"+restaurantId, Restaurant.class);
	}
	
	private List<FoodItem> fetchFoodItemList(Integer restaurantId) {
		String sql = "SELECT * FROM food_item WHERE restaurant_id = ?";
        return jdbcTemplate.query(sql, new Object[]{restaurantId}, (resultSet, rowNum) ->
                new FoodItem(
                        resultSet.getInt("id"),
                        resultSet.getBoolean("is_veg"),
                        resultSet.getString("item_description"),
                        resultSet.getString("item_name"),
                        resultSet.getInt("price"),
                        resultSet.getInt("restaurant_id"),
                        resultSet.getInt("quantity")
                )
        );
		//return foodItemRepo.findByRestaurantId(restaurantId);
	}

}
