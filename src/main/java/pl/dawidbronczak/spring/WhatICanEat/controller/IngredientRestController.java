package pl.dawidbronczak.spring.WhatICanEat.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.dawidbronczak.spring.WhatICanEat.model.Ingredient;
import pl.dawidbronczak.spring.WhatICanEat.service.IngredientService;

@RestController
@RequestMapping("/api")
public class IngredientRestController {

	@Autowired
	IngredientService ingredientService;
	
	@GetMapping("/ingredients/autocomplete")
	public List<Ingredient> getAutocompletSuggestions(@RequestParam("term") String term) {
		return ingredientService.findIngredientsContains(term);
	}
	
	@GetMapping("/ingredients")
	public List<Ingredient> getAllIngredients() {
		return ingredientService.findAll();
	}
		
	@GetMapping("/ingredients/{name}")
	public Ingredient getIngredient(@PathVariable String name){
		return ingredientService.findByName(name);
	}
	
	@PostMapping("/ingredients")
	public ResponseEntity<Ingredient> addIngredient(@RequestBody Ingredient ingredient) throws URISyntaxException{
		if(!ingredientService.isExist(ingredient)){
			URI location = new URI("/api/ingredient/"+ingredient.getName().replace(" ","%20"));
			ingredientService.insert(ingredient);
			return ResponseEntity.created(location).build();
		}
		return ResponseEntity.status(HttpStatus.CONFLICT).build();
	}
	
	
		
}
