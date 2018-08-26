package pl.dawidbronczak.spring.WhatICanEat.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pl.dawidbronczak.spring.WhatICanEat.model.Ingredient;
import pl.dawidbronczak.spring.WhatICanEat.model.Recipe;
import pl.dawidbronczak.spring.WhatICanEat.service.IngredientService;
import pl.dawidbronczak.spring.WhatICanEat.service.RecipeService;

@RestController
@RequestMapping("/api")
public class RecipeRestController {
	
	@Autowired
	RecipeService recipeService;
	
	@Autowired
	IngredientService ingredienService;

	@PostMapping("/recipes/search")
	public ResponseEntity<List<Recipe>> findRecipeByIngredients(@RequestBody List<Ingredient> ingredients){
		List<Recipe> recipes = recipeService.findAllByIngredients(ingredients);
		return ResponseEntity.ok(recipes);
	}
	
	@PostMapping("/recipes")
	public ResponseEntity<Recipe> addRecipe(@RequestBody Recipe recipe) throws URISyntaxException {
		URI location = new URI("/api/recipes/"+recipe.getName().replace(" ", "%20"));
		recipeService.insert(recipe);
		return ResponseEntity.created(location).build();
	}
	
	@GetMapping("/recipes")
	public List<Recipe> getAllRecipes() {
		return recipeService.findAll();
	}
	
	@GetMapping("/recipes/{name}")
	public Recipe getRecipeByName(@PathVariable String name) {
		return recipeService.findByName(name);
	}
}
