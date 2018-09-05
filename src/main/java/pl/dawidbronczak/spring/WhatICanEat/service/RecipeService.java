package pl.dawidbronczak.spring.WhatICanEat.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import pl.dawidbronczak.spring.WhatICanEat.model.Ingredient;
import pl.dawidbronczak.spring.WhatICanEat.model.Recipe;
import pl.dawidbronczak.spring.WhatICanEat.reposiotry.RecipeRepository;

@Service
public class RecipeService {

	@Autowired
	RecipeRepository recipeRepo;
	
	@Autowired
	MongoTemplate mongoTemplate;
	
	public Recipe insert(Recipe recipe) {
		return recipeRepo.insert(recipe);
	}
	
	public List<Recipe> findAllByIngredients(List<Ingredient> ingredients) {
		return mongoTemplate.find(Query.query(Criteria.where("ingredients").all(ingredients)), Recipe.class);
	}

	public List<Recipe> findAll() {
		return recipeRepo.findAll(); 
	}

	public Optional<Recipe> findById(String name) {
		return recipeRepo.findById(name);
	}

	public boolean isExist(String name) {
		return recipeRepo.existsById(name);
	}
}
