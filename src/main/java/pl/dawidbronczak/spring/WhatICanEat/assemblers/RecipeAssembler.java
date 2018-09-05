package pl.dawidbronczak.spring.WhatICanEat.assemblers;

import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;

import pl.dawidbronczak.spring.WhatICanEat.controller.RecipeRestController;
import pl.dawidbronczak.spring.WhatICanEat.model.Ingredient;
import pl.dawidbronczak.spring.WhatICanEat.model.Recipe;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;
@Component
public class RecipeAssembler implements ResourceAssembler<Recipe, Resource<Recipe>>{

	@Override
	public Resource<Recipe> toResource(Recipe recipe) {
		return new Resource<>(recipe, 
				linkTo(methodOn(RecipeRestController.class).getRecipe(recipe.getName())).withSelfRel(),
				linkTo(methodOn(RecipeRestController.class).getRecipes()).withRel("recipes"));
	}

}
