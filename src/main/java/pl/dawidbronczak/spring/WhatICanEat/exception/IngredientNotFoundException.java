package pl.dawidbronczak.spring.WhatICanEat.exception;

public class IngredientNotFoundException extends RuntimeException {

	public IngredientNotFoundException(String name) {
		super("Ingredient with name: "+name+" not found");
	}

}
