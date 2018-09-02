package pl.dawidbronczak.spring.WhatICanEat.assemblers;

import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

import pl.dawidbronczak.spring.WhatICanEat.controller.IngredientRestController;
import pl.dawidbronczak.spring.WhatICanEat.model.Ingredient;

@Component
public class IngredientResourceAssembler implements ResourceAssembler<Ingredient, Resource<Ingredient>> {

	@Override
	public Resource<Ingredient> toResource(Ingredient ingredient) {
			return new Resource<>(ingredient, 
					linkTo(methodOn(IngredientRestController.class).getIngredient(ingredient.getName())).withSelfRel(),
					linkTo(methodOn(IngredientRestController.class).getIngredients(null)).withRel("ingredients"));
			
	}

}
