package pl.dawidbronczak.spring.WhatICanEat.exception;

public class ResourceNotFoundException extends RuntimeException {

	public ResourceNotFoundException(Class<?> resourceClass, String name) {
		super(resourceClass.getSimpleName()+" with name: "+name+" not found!");
	}

}
