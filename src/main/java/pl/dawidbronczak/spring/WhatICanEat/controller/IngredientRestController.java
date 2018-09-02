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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import pl.dawidbronczak.spring.WhatICanEat.assemblers.IngredientResourceAssembler;
import pl.dawidbronczak.spring.WhatICanEat.exception.IngredientExistException;
import pl.dawidbronczak.spring.WhatICanEat.exception.IngredientNotFoundException;
import pl.dawidbronczak.spring.WhatICanEat.model.Ingredient;
import pl.dawidbronczak.spring.WhatICanEat.service.IngredientService;

@RestController
@RequestMapping("/api")
public class IngredientRestController {

	@Autowired
	IngredientService ingredientService;
	
	@Autowired
	IngredientResourceAssembler ingredientAssembler;
	
	
	@GetMapping("/ingredients/autocomplete")
	public List<Ingredient> getAutocompletSuggestions(@RequestParam("term") String term) {
		return ingredientService.findIngredientsContains(term);
	}
	
	@GetMapping("/ingredients")
	public Resources<Resource<Ingredient>> getIngredients(@RequestParam(name = "term", required = false) String term) {
		List<Ingredient> ingredients;
		if(term == null) {
			ingredients = ingredientService.findAll();
		} 
		else {
			ingredients = ingredientService.findIngredientsContains(term);
		}
		List<Resource<Ingredient>> resources = ingredients.stream()
				.map(ingredientAssembler::toResource)
				.collect(Collectors.toList());
		return new Resources<>(resources,
				linkTo(methodOn(IngredientRestController.class).getIngredients(term)).withSelfRel());
		
	}
		
	@GetMapping("/ingredients/{name}")
	public Resource<Ingredient> getIngredient(@PathVariable String name){
		Ingredient ingredient = ingredientService.findByName(name)
				.orElseThrow(() -> new IngredientNotFoundException(name));
		return ingredientAssembler.toResource(ingredient);
	}
	
	@PostMapping("/ingredients")
	public ResponseEntity<Resource<Ingredient>> addIngredient(@RequestBody Ingredient ingredient) throws URISyntaxException{
		if(ingredientService.isExist(ingredient)) {
			throw new IngredientExistException(ingredient.getName());
		}
		Resource<Ingredient> resource = ingredientAssembler.toResource(ingredientService.insert(ingredient));
		return ResponseEntity.created(new URI(resource.getId().expand().getHref())).body(resource);
	}
	
	
		
}
