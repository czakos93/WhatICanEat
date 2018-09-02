package pl.dawidbronczak.spring.WhatICanEat.reposiotry;

import java.util.List;
import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;
import pl.dawidbronczak.spring.WhatICanEat.model.Ingredient;

public interface IngredientRepository extends MongoRepository<Ingredient, String> {
	
	List<Ingredient> findByNameLikeIgnoreCase(String name);
	
	Optional<Ingredient> findByName(String name);
}
