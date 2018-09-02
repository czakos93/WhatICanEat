package pl.dawidbronczak.spring.WhatICanEat.exception;

public class IngredientExistException extends RuntimeException {
	public IngredientExistException(String message) {
		super("Ingredient with name: "+message+" alredy exist");
	}
}
