package pl.dawidbronczak.spring.WhatICanEat.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.dawidbronczak.spring.WhatICanEat.model.Ingredient;
import pl.dawidbronczak.spring.WhatICanEat.reposiotry.IngredientRepository;

@Service
public class IngredientService {

	@Autowired
	IngredientRepository ingredientRepo;
	
	public List<Ingredient> findIngredientsContains(String term) {
		return ingredientRepo.findByNameLike(term);
	}
	
	public Ingredient insert(Ingredient ingredient) {
		return ingredientRepo.insert(ingredient);
	}
	
	public Ingredient findByName(String name) {
		return ingredientRepo.findByName(name);
	}
	
	public List<Ingredient> findAll() {
		return ingredientRepo.findAll();
	}

	
}
