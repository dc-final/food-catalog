package com.distributedcomputing.foodcatalogue.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.distributedcomputing.foodcatalogue.dto.FoodCataloguePage;
import com.distributedcomputing.foodcatalogue.entity.FoodItem;
import com.distributedcomputing.foodcatalogue.service.FoodCatalogueService;

@RestController
@RequestMapping("/foodCatalogue")
@CrossOrigin
public class FoodCatalogueController {
	@Autowired
	FoodCatalogueService foodCatalogueService;
	
	@PostMapping("/addFoodItem")
	public ResponseEntity<FoodItem> saveFoodItem(@RequestBody FoodItem foodItem){
		FoodItem saved = foodCatalogueService.saveFoodItem(foodItem);
		return new ResponseEntity<>(saved, HttpStatus.CREATED);
	}
	@GetMapping("/getRestaurantAndItemById/{restaurantId}")
	public ResponseEntity<FoodCataloguePage> fetchDetailsWithMenu(@PathVariable Integer restaurantId){
		FoodCataloguePage foodCataloguePage = foodCatalogueService.fetchFoodCataloguePageDetails(restaurantId);
		return new ResponseEntity<>(foodCataloguePage, HttpStatus.OK);
	}

}
