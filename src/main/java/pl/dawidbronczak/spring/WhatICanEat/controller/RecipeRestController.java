package pl.dawidbronczak.spring.WhatICanEat.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pl.dawidbronczak.spring.WhatICanEat.assemblers.RecipeAssembler;
import pl.dawidbronczak.spring.WhatICanEat.exception.ResourceAlreadyExistException;
import pl.dawidbronczak.spring.WhatICanEat.exception.ResourceNotFoundException;
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
	
	@Autowired
	RecipeAssembler recipeAssembler;

	@PostMapping("/recipes/search")
	public ResponseEntity<List<Recipe>> findRecipeByIngredients(@RequestBody List<Ingredient> ingredients){
		List<Recipe> recipes = recipeService.findAllByIngredients(ingredients);
		return ResponseEntity.ok(recipes);
	}
	
	@PostMapping("/recipes")
	public ResponseEntity<Resource<Recipe>> addRecipe(@RequestBody Recipe recipe) throws URISyntaxException {
		if(recipeService.isExist(recipe.getName())){
			throw new ResourceAlreadyExistException(Recipe.class, recipe.getName());
		}
		Resource<Recipe> resource = recipeAssembler.toResource(recipeService.insert(recipe));
		return ResponseEntity.created(new URI(resource.getId().expand().getHref())).build();
	}
	
	@GetMapping("/recipes")
	public Resources<Resource<Recipe>> getRecipes() {
		List<Resource<Recipe>> recipes = recipeService.findAll().stream()
				.map(recipeAssembler::toResource)
				.collect(Collectors.toList());
		return new Resources<>(recipes,
				linkTo(methodOn(RecipeRestController.class).getRecipes()).withSelfRel());
	}
	
	@GetMapping("/recipes/{name}")
	public Resource<Recipe> getRecipe(@PathVariable String name) {
		Recipe recipe = recipeService.findById(name)
				.orElseThrow(() -> new ResourceNotFoundException(Recipe.class, name));
		return recipeAssembler.toResource(recipe);
	}

}
