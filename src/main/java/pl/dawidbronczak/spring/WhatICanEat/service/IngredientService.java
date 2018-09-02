package pl.dawidbronczak.spring.WhatICanEat.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.dawidbronczak.spring.WhatICanEat.model.Ingredient;
import pl.dawidbronczak.spring.WhatICanEat.reposiotry.IngredientRepository;

@Service
public class IngredientService {

	@Autowired
	IngredientRepository ingredientRepo;
	
	public List<Ingredient> findIngredientsContains(String term) {
		return ingredientRepo.findByNameLikeIgnoreCase(term);
	}
	
	public Ingredient insert(Ingredient ingredient) {
		return ingredientRepo.insert(ingredient);
	}
	
	public Optional<Ingredient> findByName(String name) {
		return ingredientRepo.findByName(name);
	}
	
	public List<Ingredient> findAll() {
		return ingredientRepo.findAll();
	}

	public boolean isExist(Ingredient ingredient) {
		return 	ingredientRepo.existsById(ingredient.getName());
	}

	
}
