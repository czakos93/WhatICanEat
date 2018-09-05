package pl.dawidbronczak.spring.WhatICanEat.reposiotry;

import org.springframework.data.mongodb.repository.MongoRepository;
import pl.dawidbronczak.spring.WhatICanEat.model.Recipe;

public interface RecipeRepository extends MongoRepository<Recipe, String> {
	
}
