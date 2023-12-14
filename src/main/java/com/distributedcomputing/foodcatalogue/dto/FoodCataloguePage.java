package com.distributedcomputing.foodcatalogue.dto;

import java.util.List;

import com.distributedcomputing.foodcatalogue.entity.FoodItem;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class FoodCataloguePage {
	
	private List<FoodItem> foodItemsList;
	private Restaurant restaurant;
	
	public List<FoodItem> getFoodItemsList() {
		return foodItemsList;
	}
	public void setFoodItemsList(List<FoodItem> foodItemsList) {
		this.foodItemsList = foodItemsList;
	}
	public Restaurant getRestaurant() {
		return restaurant;
	}
	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}
	

}
